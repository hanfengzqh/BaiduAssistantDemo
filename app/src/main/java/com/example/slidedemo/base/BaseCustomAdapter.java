package com.example.slidedemo.base;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

import com.zng.library.loadmore.CollectionUtil;

/**
 * 所有BaseAdapter的直接父类
 */
public abstract class BaseCustomAdapter<T> extends BaseAdapter{

    protected List<T> data;
    protected Context mContext;
    
    public BaseCustomAdapter(List<T> data,Context mContext){
        this.data = data;
        this.mContext = mContext;
    }

    /**
     * 获取所有数据
     */
    public List<T> getData(){
        return data;
    }

    /**
     * 设置数据
     */
    public void setData(List<T> data){
        if(!CollectionUtil.listIsNull(this.data)){
            this.data.clear();
            this.data = null;
        }
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 添加集合数据，新集合添加到当前集合最尾部
     */
    public void addAll(List<T> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 将集合添加指定位置
     */
    public void addAll(int location, List<T> data){
        this.data.addAll(location, data);
        notifyDataSetChanged();
    }

    public void add(T t){
        this.data.add(t);
        notifyDataSetChanged();
    }

    public void add(int location, T t){
        this.data.add(location, t);
        notifyDataSetChanged();
    }

    public void remove(int location){
        this.data.remove(location);
        notifyDataSetChanged();
    }

    public void remove(T t){
        this.data.remove(t);
        notifyDataSetChanged();
    }

    public void removeAll(List<T> data){
        this.data.removeAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public T getItem(int position){
        return data.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }
}