package com.example.bukola_omotoso.quiz_app;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bukola_omotoso on 24/02/16.
 */
public class Launcher {

    public static void LauncherActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context,activity);
        context.startActivity(intent);
    }
}
