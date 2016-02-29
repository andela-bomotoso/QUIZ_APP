package com.example.bukola_omotoso.quiz_app;

/**
 * Created by bukola_omotoso on 24/02/16.
 */
public interface AuthCallback {

    void onSuccess();
    void onCancel();
    void onFailure(Exception e);
    void onError(String errorMessage);
}