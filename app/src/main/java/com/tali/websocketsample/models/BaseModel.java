package com.tali.websocketsample.models;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class BaseModel {

    @Inject
    Gson gson;

    @NonNull
    @Override
    public String toString() {
        return gson.toJson(this);
    }

    public JSONObject toJsonObject() {
        try {
            return new JSONObject(toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
