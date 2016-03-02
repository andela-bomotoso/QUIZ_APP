package com.example.bukola_omotoso.quiz_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private LoginButton fbLoginButton;
    private CallbackManager callbackManager;
    private EditText fullName;
    private EditText email;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    private LinearLayout parentLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        fullName = (EditText)findViewById(R.id.full_name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        parentLayout = (LinearLayout)findViewById(R.id.quiz_layout);
        firebaseAuth = new FirebaseAuth(this);
        startAnimations();

    }

    private void startAnimations()  {
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.quiz_layout);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        ImageView imageView = (ImageView)findViewById(R.id.logo);
        imageView.clearAnimation();
        imageView.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linear2);
        linearLayout1.setVisibility(View.VISIBLE);
        linearLayout1.clearAnimation();
        linearLayout1.startAnimation(anim);
    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this != null) {
            AppEventsLogger.activateApp(this, "1567728580219956");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this != null)
        AppEventsLogger.deactivateApp(this,"1567728580219956");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void facebookLogin(View view) {

        fbLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        fbLoginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Snackbar.make(parentLayout,"Facebook login successful",Snackbar.LENGTH_SHORT);
                Launcher.LauncherActivity(context, HomeActivity.class);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void sigin(View view)    {

        Launcher.LauncherActivity(this,SignInActivity.class);
    }

    public void signUp(View view)   {

        String email_addresss = email.getText().toString().trim();
        String passwrd = password.getText().toString().trim();
        String name = fullName.getText().toString().trim();

        if(name.isEmpty())  {
            fullName.setError("Please enter your full name");
        }

        if(email_addresss.isEmpty())    {
            email.setError("Please enter an email address");
        }

        else if(passwrd.isEmpty())   {
            password.setError("Please enter your password");
        }

        else {
            signUp(name,email_addresss,passwrd);
        }
    }

    private void signUp(String fullName, String email, String password) {
        //emailPasswordAuth.signUp(email, password, new AuthCallback() {
        firebaseAuth.signUp(email, password, new AuthCallback() {

            @Override
            public void onSuccess() {
                Snackbar.make(parentLayout,"Account successfully created",Snackbar.LENGTH_SHORT).show();
                Launcher.LauncherActivity(context,SignInActivity.class);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFailure(Exception e) {
                Snackbar.make(parentLayout,e.toString(),Snackbar.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String errorMessage) {
                Snackbar.make(parentLayout,errorMessage,Snackbar.LENGTH_SHORT).show();

            }
        });
    }
}
