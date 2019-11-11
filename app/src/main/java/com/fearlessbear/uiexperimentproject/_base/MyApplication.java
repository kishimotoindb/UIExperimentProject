package com.fearlessbear.uiexperimentproject._base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by BigFaceBear on 2019.06.27
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new android.app.Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.e(TAG, "lifeCycle create " + activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e(TAG, "lifeCycle start " + activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e(TAG, "lifeCycle resume " + activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e(TAG, "lifeCycle pause " + activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e(TAG, "lifeCycle stop " + activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.e(TAG, "lifeCycle create " + activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e(TAG, "lifeCycle destroy " + activity);
            }
        });
    }
}
