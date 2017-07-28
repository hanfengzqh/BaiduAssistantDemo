package com.example.slidedemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slidedemo.R;
import com.example.slidedemo.base.BaseCustomAdapter;




public class RecoListViewAdapter extends BaseCustomAdapter<String> {
	public RecoListViewAdapter(List<String> data, Context mContext) {
		super(data, mContext);
	}

	private HeadOnClickListener headOnClickListener;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHelper viewHelper;
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recom_popular_listview_item,null);
            viewHelper=new ViewHelper(convertView);
            convertView.setTag(viewHelper);
        }else{
            viewHelper = (ViewHelper) convertView.getTag();
        }
        return convertView;
    }

    class ViewHelper implements View.OnClickListener{

        TextView tvTitle;
        TextView tvDescription;
        
        TextView tvDownload;

        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        public ViewHelper(View convertView){
        }

        @Override
        public void onClick(View v) {
            headOnClickListener.onClick(v,position);
        }
    }

    public void setHeadOnClickListener(HeadOnClickListener headOnClickListener){
        this.headOnClickListener = headOnClickListener;
    }

    public interface HeadOnClickListener{
        public void onClick(View view,int position);
    }

}
