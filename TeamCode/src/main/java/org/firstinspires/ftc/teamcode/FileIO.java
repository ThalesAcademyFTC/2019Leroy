package org.firstinspires.ftc.teamcode;

import android.app.Application;
import android.content.Context;

/**
 * Created by dcrenshaw on 1/27/19.
 *
 * This is a simple experiment to enable file I/O on this thing.
 */

public class FileIO extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
