package com.example.bukola_omotoso.quiz_app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

/**
 * Created by bukola_omotoso on 23/02/16.
 */
public class QuizApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        Firebase.setAndroidContext(this);
    }
}
