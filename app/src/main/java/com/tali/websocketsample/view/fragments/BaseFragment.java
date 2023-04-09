package com.tali.websocketsample.view.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tali.websocketsample.utils.AutoClearedValue;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<VM extends ViewModel, DB extends ViewDataBinding> extends Fragment implements LifecycleOwner {


    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public VM viewModel;

    public abstract Class<VM> getViewModel();

    public final AutoClearedValue<DB> autoClearedValue = new AutoClearedValue<>(this);
    public DB dataBinding = autoClearedValue.get();


    @LayoutRes
    public abstract int getLayoutRes();

    @NotNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(getViewModel());
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        dataBinding.setLifecycleOwner(this.getViewLifecycleOwner());

        return dataBinding.getRoot();
    }


    public void Toaster(String message) {

        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.BOTTOM, 0, 120);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }

}
