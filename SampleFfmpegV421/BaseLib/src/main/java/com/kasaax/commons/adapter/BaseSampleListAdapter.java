package com.kasaax.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：简单list adapter基础类
 * Date：2019/7/5
 */
public abstract class BaseSampleListAdapter<T, VH extends ViewDataBinding> extends RecyclerView.Adapter<BaseBindingViewHolder<VH>> {
    protected Context mCtx;
    protected List<T> mList = new ArrayList<>();
    protected final LayoutInflater mLayoutInflater;
    protected OnItemClickListener<T> mItemClickListener;

    public BaseSampleListAdapter(Context _context, List<T> _list) {
        this.mCtx = _context;
        this.mList = _list;
        mLayoutInflater = LayoutInflater.from(mCtx);

        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public BaseBindingViewHolder<VH> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH _tVH = DataBindingUtil.inflate(mLayoutInflater, getLayoutId(viewType), parent, false);
        return new BaseBindingViewHolder<>(_tVH);
    }

    protected abstract int getLayoutId(int _viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<VH> holder, int position) {
        bindData(holder, mList.get(position), position);
    }

    protected abstract void bindData(BaseBindingViewHolder<VH> _holder, T _t, int _position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<T> _list) {
        this.mList = _list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener<T> _itemClickListener) {
        mItemClickListener = _itemClickListener;
    }

    public void update(List<T> _list) {
        mList.addAll(_list);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T _t, int pos);
    }
}
