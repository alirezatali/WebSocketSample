package com.tali.websocketsample.view.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.tali.websocketsample.models.BaseModel;
import com.tali.websocketsample.view.common.BaseCallBack;

public abstract class BaseViewHolder<DB extends ViewDataBinding, Data extends BaseModel, CallBack extends BaseCallBack<Data>> extends RecyclerView.ViewHolder {

    DB binding;

    public BaseViewHolder(DB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    abstract void onBind(Data data, CallBack callback);

}
