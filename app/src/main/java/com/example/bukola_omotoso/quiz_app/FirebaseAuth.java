package com.example.bukola_omotoso.quiz_app;

import android.content.Context;
import android.support.design.widget.Snackbar;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by bukola_omotoso on 24/02/16.
 */
public class FirebaseAuth implements EmailPasswordAuth {

    private Context context;
    private Firebase firebase;

    public FirebaseAuth (Context context)   {
        this.context = context;
        firebase = Constants.FIREBASE_REF;
    }

    @Override
    public void signOut()   {

    }

    @Override
    public void signUp(String email, String password, final AuthCallback callback)    {
        firebase.createUser(email,password,new Firebase.ValueResultHandler<Map<String,Object>>(){

            @Override
            public void onError(FirebaseError firebaseError)    {

                callback.onError(FireBaseErrorHandler.getErrorMessage(firebaseError));
            }

            @Override
            public void onSuccess(Map<String,Object> result)    {
                String id = result.get("uid").toString();
                callback.onSuccess();
            }

        });
    }

    @Override
    public void signIn(String email, String password, final AuthCallback callback)    {
        firebase.authWithPassword(email,password,new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData)  {

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError)  {
                FireBaseErrorHandler.getErrorMessage(firebaseError);
            }

        });

    }

}
