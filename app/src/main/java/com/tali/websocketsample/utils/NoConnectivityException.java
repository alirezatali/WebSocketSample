package com.tali.websocketsample.utils;

import android.content.Context;

import androidx.annotation.Nullable;
import com.tali.websocketsample.R;

import java.io.IOException;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class NoConnectivityException extends IOException {
    @ApplicationContext
    Context context;

    @Nullable
    @Override
    public String getMessage() {
        return context.getString(R.string.connection_error) ;
    }
}
