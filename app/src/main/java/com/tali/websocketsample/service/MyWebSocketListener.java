package com.tali.websocketsample.service;

import android.util.Log;

import com.tali.websocketsample.utils.AppExecutors;
import com.tali.websocketsample.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MyWebSocketListener extends WebSocketListener {
    private final MainViewModel viewModel;
    private final AppExecutors appExecutors;
    private static final String TAG = "SOCKET_LISTENER";

    public MyWebSocketListener(MainViewModel viewModel,AppExecutors appExecutors) {
        this.viewModel = viewModel;
        this.appExecutors = appExecutors;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {

        super.onOpen(webSocket, response);
        appExecutors.mainThread().execute(() -> this.viewModel.setStatus(true));
        webSocket.send("Android Device Connected");
        Log.d(TAG, "onOpen:");
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {

        super.onMessage(webSocket, text);
        appExecutors.mainThread().execute(() -> this.viewModel.addMessage(text));

        Log.d(TAG, "onMessage: " + text);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {

        super.onClosing(webSocket, code, reason);
        Log.d(TAG, "onClosing: " + code + ' ' + reason);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {

        super.onClosed(webSocket, code, reason);
        appExecutors.mainThread().execute(() -> this.viewModel.setStatus(false));
        Log.d(TAG, "onClosed: " + code + ' ' + reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        @Nullable String message = response != null ? response.message() : t.getMessage() != null ? t.getMessage() : "null";
        Log.d(TAG, "onFailure: " + message);
        super.onFailure(webSocket, t, response);
    }
}
