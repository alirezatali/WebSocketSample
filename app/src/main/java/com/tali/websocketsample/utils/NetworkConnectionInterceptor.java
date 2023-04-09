package com.tali.websocketsample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor{
    private final Context mContext;

    public NetworkConnectionInterceptor(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!isConnected()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network netInfo = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(netInfo);
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true;
        //for other device how are able to connect with Ethernet
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return true;
        //for check internet over Bluetooth
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH);
    }
}
