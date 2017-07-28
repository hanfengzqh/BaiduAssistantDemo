/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zng.library.loadmore;

/**
 * Constants that indicates the scroll state of the Scrollable widgets.
 */
public enum ScrollState {
    /**
     * Widget is stopped.
     * This state does not always mean that this widget have never been scrolled.
     * 小部件停止滚动
     * 这个状态并不总是意味着这个小部件从未被滚动过。
     */
    STOP,

    /**
     * 通过向上滑动，窗口小部件向上滚动
     * Widget is scrolled up by swiping it down.
     */
    UP,

    /**
     *通过向下滑动，窗口小部件向下滚动
     * Widget is scrolled down by swiping it up.
     */
    DOWN,
}
