package com.example.slidedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.slidedemo.R;
import com.example.slidedemo.base.BaseCustomAdapter;
import com.example.slidedemo.ui.MainActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecoListViewAdapter extends BaseCustomAdapter<String> {

    public RecoListViewAdapter(List<String> data, Context mContext) {
        super(data, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recom_popular_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mDownloadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).startDownloadAnimation(viewHolder.mImageView, null);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.ll_recommend_listview_item_app_download)
        LinearLayout mDownloadView;
        @Bind(R.id.iv_app_icon)
        ImageView mImageView;//图标

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}