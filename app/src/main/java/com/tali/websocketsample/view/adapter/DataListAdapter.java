package com.tali.websocketsample.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.tali.websocketsample.R;
import com.tali.websocketsample.databinding.DataListItemBinding;
import com.tali.websocketsample.models.DetailsDto;
import com.tali.websocketsample.view.common.ListClickCallBack;

public class DataListAdapter extends BaseAdapter<DetailsDto, DataListItemBinding,
        ListClickCallBack<DetailsDto>,
        DataListAdapter.DataListViewHolder>{

    public DataListAdapter(ListClickCallBack<DetailsDto> clickCallBack) {
        super(clickCallBack);
    }

    @Override
    int getLayoutRes() {
        return R.layout.data_list_item;
    }

    @NonNull
    @Override
    public DataListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutRes(),
                        parent, false);
        return new DataListAdapter.DataListViewHolder(binding);
    }

    static class DataListViewHolder extends BaseViewHolder<DataListItemBinding, DetailsDto, ListClickCallBack<DetailsDto>> {

        public DataListViewHolder(DataListItemBinding binding) {
            super(binding);

        }

        @Override
        void onBind(DetailsDto detailsDto, ListClickCallBack<DetailsDto> callback) {
            binding.setData(detailsDto);
            binding.executePendingBindings();
        }
    }
}
