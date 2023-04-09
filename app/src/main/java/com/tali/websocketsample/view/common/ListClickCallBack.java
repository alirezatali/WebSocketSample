package com.tali.websocketsample.view.common;

public interface ListClickCallBack<T> extends BaseCallBack<T> {
    void clickCallBack(T item);
}
