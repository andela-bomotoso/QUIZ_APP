package com.example.bukola_omotoso.quiz_app;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class SignInActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private LinearLayout quizSignIn;
    private Context context;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        context = this;
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        quizSignIn = (LinearLayout)findViewById(R.id.quiz_signin);
        firebaseAuth = new FirebaseAuth(this);
    }

    public void signIn(View view)   {

        String emailAddress = email.getText().toString().trim();
        String pswd = password.getText().toString().trim();

        if(emailAddress.isEmpty())  {
            email.setError("Please enter an email address");
        }

        else if(pswd.isEmpty()) {
            password.setError("Please enter a valid password");
        }
        else    {
            signIn(emailAddress,pswd);
        }

    }

    public void signIn(String email, String password)  {
        Firebase firebase = Constants.FIREBASE_REF;
        firebase.authWithPassword(email,password,new Firebase.AuthResultHandler(){

            @Override
            public void onAuthenticated(AuthData authData)  {
                Snackbar.make(quizSignIn,"Sign  in successful",Snackbar.LENGTH_SHORT).show();
                Launcher.LauncherActivity(context,HomeActivity.class);

            }

            @Override
            public void onAuthenticationError(FirebaseError error)  {
                String errorMessage = FireBaseErrorHandler.getErrorMessage(error);
                Snackbar.make(quizSignIn, errorMessage, Snackbar.LENGTH_SHORT).show();

            }
        });
    }

}
