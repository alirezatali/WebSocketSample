package com.tali.websocketsample.di;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tali.websocketsample.utils.NetworkConnectionInterceptor;
import com.tali.websocketsample.utils.UnsafeOkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    OkHttpClient getHttpClient(@ApplicationContext Context context){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("Socket", message));
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        client.addInterceptor(new NetworkConnectionInterceptor(context));

        return client
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    Gson getGson(){
        return new GsonBuilder().create();
    }

    @Provides
    @Named("baseUrl")
    Uri getBaseUrl(){
        return new Uri.Builder()
                .scheme("wss")
                .authority("fstream.binance.com")
                .build();
    }

}
