package com.example.bukola_omotoso.quiz_app;

import com.firebase.client.FirebaseError;



import com.firebase.client.FirebaseError;

/**
 * Created by bukola_omotoso on 24/02/16.
 */
public class FireBaseErrorHandler {

    public static String getErrorMessage(FirebaseError firebaseError) {
        String errorMessage = "";
        switch (firebaseError.getCode())    {
            case FirebaseError.USER_DOES_NOT_EXIST:
                errorMessage = "The specified user does not exist";
                break;
            case FirebaseError.EMAIL_TAKEN:
                errorMessage = "The specified email already exists";
                break;
            case FirebaseError.INVALID_PASSWORD:
                errorMessage = "The specified email is invalid";
                break;
            case FirebaseError.INVALID_CREDENTIALS:
                errorMessage = "The specified credentials is invalid";
                break;
            case FirebaseError.NETWORK_ERROR:
                errorMessage = "Network Error";
                break;
            case FirebaseError.DISCONNECTED:
                errorMessage = "Network Disconnected";
                break;
            default:
                errorMessage = "Unknown Error, Try Again";
        }
        return errorMessage;
    }

}
