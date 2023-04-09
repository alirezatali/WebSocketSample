package com.tali.websocketsample.view.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.tali.websocketsample.R;
import com.tali.websocketsample.models.BaseModel;
import com.tali.websocketsample.view.common.BaseCallBack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<Data extends BaseModel, DB extends ViewDataBinding,
        CallBack extends BaseCallBack<Data>, Holder extends BaseViewHolder<DB, Data, CallBack>>
        extends RecyclerView.Adapter<Holder> {
    CallBack clickCallBack;

    abstract int getLayoutRes();

    List<Data> mData;
    private int lastPosition = -1;
    private boolean hasAnimation = true;

    public BaseAdapter(CallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
        mData = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onSetData(List<Data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<Data> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void changeData(Data item, int index) {
        mData.set(index, item);
        notifyItemChanged(index);
    }

    public List<Data> getData() {
        return this.mData;
    }

    @Override
    public int getItemCount() {
        if (this.mData != null)
            return this.mData.size();
        return 0;
    }

    public void setHasAnimation(boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.onBind(mData.get(position), clickCallBack);
        if (hasAnimation)
            setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.item_animation_fall_down);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
