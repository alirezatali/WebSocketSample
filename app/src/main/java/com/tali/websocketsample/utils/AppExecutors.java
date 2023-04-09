package com.tali.websocketsample.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import kotlin.jvm.internal.Intrinsics;

public class AppExecutors {
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    @Inject
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new MainThreadExecutor());

    }

    public final Executor diskIO() {
        return this.diskIO;
    }

    public final Executor networkIO() {
        return this.networkIO;
    }

    public final Executor mainThread() {
        return this.mainThread;
    }

    private static final class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.myLooper());


        public MainThreadExecutor() {
        }

        @Override
        public void execute(Runnable command) {
            Intrinsics.checkParameterIsNotNull(command, "command");
            this.mainThreadHandler.post(command);
        }
    }
}
