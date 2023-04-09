package com.tali.websocketsample.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;


public class AutoClearedValue<T> implements ReadWriteProperty<T> {
    @NotNull
    private final Fragment fragment;
    private T value;

    @NotNull
    public final Fragment getFragment() {
        return this.fragment;
    }

    public AutoClearedValue(@NotNull Fragment fragment) {
        this.fragment = fragment;
        this.fragment.getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onCreate(@NonNull LifecycleOwner owner) {
                LiveData<LifecycleOwner> liveData = getFragment().getViewLifecycleOwnerLiveData();
                liveData.observe(fragment, lifecycleOwner -> lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() {
                    @Override
                    public void onDestroy(@NonNull LifecycleOwner owner) {
                        if (value instanceof DialogFragment) {
                            ((DialogFragment) value).dismiss();
                        }
                        value = null;
                    }
                }));
            }
        });
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

}
