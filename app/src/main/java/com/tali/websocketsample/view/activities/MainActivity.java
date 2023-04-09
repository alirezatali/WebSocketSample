package com.tali.websocketsample.view.activities;

import androidx.fragment.app.ListFragment;

import android.os.Bundle;

import com.tali.websocketsample.R;
import com.tali.websocketsample.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListFragment fragment = new ListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, fragment, "List Fragment").commit();
    }
}