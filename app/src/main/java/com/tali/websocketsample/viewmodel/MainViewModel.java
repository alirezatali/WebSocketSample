package com.tali.websocketsample.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tali.websocketsample.models.DetailsDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private final Gson gson;
    @Inject
    public MainViewModel(Gson gson) {
        this.gson = gson;
    }

    private final MutableLiveData<Boolean> _socketStatus = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> socketStatus = _socketStatus;
    private final MutableLiveData<List<DetailsDto>> _messages  = new MutableLiveData<>();
    public LiveData<List<DetailsDto>> messages = _messages;

    public void addMessage(String message){
        List<DetailsDto> result = new ArrayList<>();
        try {
            Type listType = new TypeToken<List<DetailsDto>>(){}.getType();
            result = gson.fromJson(message,listType);
        }catch (Exception ex){
            Log.d("MainViewModel","Array Converting Error");
        }

        _messages.setValue(result);
    }

    public void setStatus(Boolean status){
        _socketStatus.setValue(status);
    }
}
