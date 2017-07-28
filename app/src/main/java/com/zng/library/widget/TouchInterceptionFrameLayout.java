package com.zng.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
/**
 * A layout that delegates interception of touch motion events.
 * This layout is provided to move the container of Scrollable views using scroll position.
 * Please note that this class overrides or uses touch events API such as onTouchEvent,
 * onInterceptTouchEvent and dispatchTouchEvent,
 * so be careful when you handle touches with this layout.
 */
public class TouchInterceptionFrameLayout extends FrameLayout {

    /**
     * Callbacks for TouchInterceptionFrameLayout.
     */
    public interface TouchInterceptionListener {
    	/**
         * Determines whether the layout should intercept this event.
         *	判断布局是否要拦截该事件
         * @param ev     motion event 触摸事件
         * @param moving true if this event is ACTION_MOVE type 若触摸事件是ACTION_MOVE则参数为true
         * @param diffX  difference between previous X and current X, if moving is true
         * 				 x轴方向触摸事件运动的距离差值
         * @param diffY  difference between previous Y and current Y, if moving is true
         * 				 y轴方向上触摸事件运动距离的差值
         * @return true if the layout should intercept 若是该布局应该拦截触摸事件则返回true
         */
        boolean shouldInterceptTouchEvent(MotionEvent ev, boolean moving, float diffX, float diffY);

        /**
         * Called if the down motion event is intercepted by this layout.
         * 向下滑动事件被拦截则调用此方法
         * @param ev motion event
         */
        void onDownMotionEvent(MotionEvent ev);

        /**
         * Called if the move motion event is intercepted by this layout.
         *触摸事件被拦截时调用此方法
         * @param ev    motion event
         * @param diffX difference between previous X and current X
         * @param diffY difference between previous Y and current Y
         */
        void onMoveMotionEvent(MotionEvent ev, float diffX, float diffY);

        /**
         * Called if the up (or cancel) motion event is intercepted by this layout.
         *   向上或者取消触摸事件时调用此方法
         * @param ev motion event
         */
        void onUpOrCancelMotionEvent(MotionEvent ev);
    }

    private boolean mIntercepting;
    private boolean mDownMotionEventPended;
    private boolean mBeganFromDownMotionEvent;
    private boolean mChildrenEventsCanceled;
    private PointF mInitialPoint;
    private MotionEvent mPendingDownMotionEvent;
    private TouchInterceptionListener mTouchInterceptionListener;

    public TouchInterceptionFrameLayout(Context context) {
        super(context);
    }

    public TouchInterceptionFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchInterceptionFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchInterceptionFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollInterceptionListener(TouchInterceptionListener listener) {
        mTouchInterceptionListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	
        if (mTouchInterceptionListener == null) {
            return false;
        }

        // In here, we must initialize touch state variables
        // and ask if we should intercept this event.
        // Whether we should intercept or not is kept for the later event handling.
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mInitialPoint = new PointF(ev.getX(), ev.getY());
                mPendingDownMotionEvent = MotionEvent.obtainNoHistory(ev);
                mDownMotionEventPended = true;
                mIntercepting = mTouchInterceptionListener.shouldInterceptTouchEvent(ev, false, 0, 0);
                mBeganFromDownMotionEvent = mIntercepting;
                mChildrenEventsCanceled = false;
                return mIntercepting;
            case MotionEvent.ACTION_MOVE:
                // ACTION_MOVE will be passed suddenly, so initialize to avoid exception.
                if (mInitialPoint == null) {
                    mInitialPoint = new PointF(ev.getX(), ev.getY());
                }

                // diffX and diffY are the origin of the motion, and should be difference
                // from the position of the ACTION_DOWN event occurred.
                float diffX = ev.getX() - mInitialPoint.x;
                float diffY = ev.getY() - mInitialPoint.y;
                mIntercepting = mTouchInterceptionListener.shouldInterceptTouchEvent(ev, true, diffX, diffY);
                return mIntercepting;
            
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mTouchInterceptionListener != null) {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    if (mIntercepting) {
                        mTouchInterceptionListener.onDownMotionEvent(ev);
                        duplicateTouchEventForChildren(ev);
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    // ACTION_MOVE will be passed suddenly, so initialize to avoid exception.
                    if (mInitialPoint == null) {
                        mInitialPoint = new PointF(ev.getX(), ev.getY());
                    }

                    // diffX and diffY are the origin of the motion, and should be difference
                    // from the position of the ACTION_DOWN event occurred.
                    float diffX = ev.getX() - mInitialPoint.x;
                    float diffY = ev.getY() - mInitialPoint.y;
                    mIntercepting = mTouchInterceptionListener.shouldInterceptTouchEvent(ev, true, diffX, diffY);
                    if (mIntercepting) {
                        // If this layout didn't receive ACTION_DOWN motion event,
                        // we should generate ACTION_DOWN event with current position.
                        if (!mBeganFromDownMotionEvent) {
                            mBeganFromDownMotionEvent = true;

                            MotionEvent event = MotionEvent.obtainNoHistory(mPendingDownMotionEvent);
                            event.setLocation(ev.getX(), ev.getY());
                            mTouchInterceptionListener.onDownMotionEvent(event);

                            mInitialPoint = new PointF(ev.getX(), ev.getY());
                            diffX = diffY = 0;
                        }

                        // Children's touches should be canceled
                        if (!mChildrenEventsCanceled) {
                            mChildrenEventsCanceled = true;
                            duplicateTouchEventForChildren(obtainMotionEvent(ev, MotionEvent.ACTION_CANCEL));
                        }

                        mTouchInterceptionListener.onMoveMotionEvent(ev, diffX, diffY);

                        // If next mIntercepting become false,
                        // then we should generate fake ACTION_DOWN event.
                        // Therefore we set pending flag to true as if this is a down motion event.
                        mDownMotionEventPended = true;

                        // Whether or not this event is consumed by the listener,
                        // assume it consumed because we declared to intercept the event.
                        return true;
                    } else {
                        if (mDownMotionEventPended) {
                            mDownMotionEventPended = false;
                            MotionEvent event = MotionEvent.obtainNoHistory(mPendingDownMotionEvent);
                            event.setLocation(ev.getX(), ev.getY());
                            duplicateTouchEventForChildren(ev, event);
                        } else {
                            duplicateTouchEventForChildren(ev);
                        }

                        // If next mIntercepting become true,
                        // then we should generate fake ACTION_DOWN event.
                        // Therefore we set beganFromDownMotionEvent flag to false
                        // as if we haven't received a down motion event.
                        mBeganFromDownMotionEvent = false;

                        // Reserve children's click cancellation here if they've already canceled
                        mChildrenEventsCanceled = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
//                	 if (mIntercepting) {
//                         mTouchInterceptionListener.onUpOrCancelMotionEvent(ev);
//                     }
                	// break;
                case MotionEvent.ACTION_CANCEL:
                    mBeganFromDownMotionEvent = false;
                    // 把这个if注释掉了 防止滑动第二项第三项的时候顶部toolbar不隐藏的问题
                   // if (mIntercepting) {
                        mTouchInterceptionListener.onUpOrCancelMotionEvent(ev);
                 //   }

                    // Children's touches should be canceled regardless of
                    // whether or not this layout intercepted the consecutive motion events.
                    if (!mChildrenEventsCanceled) {
                        mChildrenEventsCanceled = true;
                        if (mDownMotionEventPended) {
                            mDownMotionEventPended = false;
                            MotionEvent event = MotionEvent.obtainNoHistory(mPendingDownMotionEvent);
                            event.setLocation(ev.getX(), ev.getY());
                            duplicateTouchEventForChildren(ev, event);
                        } else {
                            duplicateTouchEventForChildren(ev);
                        }
                    }
                    return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    private MotionEvent obtainMotionEvent(MotionEvent base, int action) {
        MotionEvent ev = MotionEvent.obtainNoHistory(base);
        ev.setAction(action);
        return ev;
    }

    /**
     * Duplicate touch events to child views.
     * We want to dispatch a down motion event and the move events to
     * child views, but calling dispatchTouchEvent() causes StackOverflowError.
     * Therefore we do it manually.
     *
     * @param ev            motion event to be passed to children
     * @param pendingEvents pending events like ACTION_DOWN. This will be passed to the children before ev
     */
    private void duplicateTouchEventForChildren(MotionEvent ev, MotionEvent... pendingEvents) {
        if (ev == null) {
            return;
        }
        for (int i = getChildCount() - 1; 0 <= i; i--) {
            View childView = getChildAt(i);
            if (childView != null) {
                Rect childRect = new Rect();
                childView.getHitRect(childRect);
                MotionEvent event = MotionEvent.obtainNoHistory(ev);
                if (!childRect.contains((int) event.getX(), (int) event.getY())) {
                    continue;
                }
                float offsetX = -childView.getLeft();
                float offsetY = -childView.getTop();
                boolean consumed = false;
                if (pendingEvents != null) {
                    for (MotionEvent pe : pendingEvents) {
                        if (pe != null) {
                            MotionEvent peAdjusted = MotionEvent.obtainNoHistory(pe);
                            peAdjusted.offsetLocation(offsetX, offsetY);
                            consumed |= childView.dispatchTouchEvent(peAdjusted);
                        }
                    }
                }
                event.offsetLocation(offsetX, offsetY);
                consumed |= childView.dispatchTouchEvent(event);
                if (consumed) {
                    break;
                }
            }
        }
    }
}
