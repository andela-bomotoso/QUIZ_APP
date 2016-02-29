package com.example.bukola_omotoso.quiz_app;

import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by bukola_omotoso on 24/02/16.
 */
public interface EmailPasswordAuth {
    void signIn(String email, String password,AuthCallback callback);
    void signUp(String email, String password,AuthCallback callback);
    void signOut();

}
