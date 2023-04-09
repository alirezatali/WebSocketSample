package com.tali.websocketsample.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tali.websocketsample.R;
import com.tali.websocketsample.databinding.ListFragmentBinding;
import com.tali.websocketsample.models.DetailsDto;
import com.tali.websocketsample.service.MyWebSocketListener;
import com.tali.websocketsample.utils.AppExecutors;
import com.tali.websocketsample.utils.AutoClearedValue;
import com.tali.websocketsample.view.adapter.DataListAdapter;
import com.tali.websocketsample.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

@AndroidEntryPoint
public class ListFragment extends BaseFragment<MainViewModel, ListFragmentBinding> {
    private WebSocket webSocket;
    @Inject
    OkHttpClient okHttpClient;
    @Inject
    AppExecutors appExecutors;
    @Inject
    @Named("baseUrl")
    Uri baseUrl;
    AutoClearedValue<DataListAdapter> adapterDelegate = new AutoClearedValue<>(this);
    DataListAdapter adapter = adapterDelegate.get();

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataListAdapter dataListAdapter = new DataListAdapter(item -> Toaster(item.getSymbol()));
        dataBinding.listData.setAdapter(dataListAdapter);
        adapter = dataListAdapter;

        MyWebSocketListener webSocketListener = new MyWebSocketListener(viewModel, appExecutors);
        webSocket = okHttpClient.newWebSocket(getRequest(), webSocketListener);
        viewModel.socketStatus.observe(getViewLifecycleOwner(), status -> {

        });
        viewModel.messages.observe(getViewLifecycleOwner(), this::setData);
    }

    private void setData(List<DetailsDto> result) {
        if (result != null && result.size() > 0) {
            adapter.onSetData(result);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webSocket != null) webSocket.close(1000, "Canceled.");
        okHttpClient.dispatcher().executorService().shutdown();
    }

    private Request getRequest() {
        String url = baseUrl + "/ws/!ticker@arr";
        return new Request.Builder().url(url).build();
    }
}
