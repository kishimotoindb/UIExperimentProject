package com.fearlessbear.uiexperimentproject.window;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fearlessbear.uiexperimentproject.R;

/*
 *  2019-04-28 17:23:37.086 2492-2492/com.fearlessbear.uiexperimentproject D/DialogWindow: run: activity finish
    2019-04-28 17:23:37.586 2492-2492/com.fearlessbear.uiexperimentproject E/WindowManager: android.view.WindowLeaked: Activity com.fearlessbear.uiexperimentproject.window.DialogWindowRelationship has leaked window DecorView@fdae6fd[DialogWindowRelationship] that was originally added here
        at android.view.ViewRootImpl.<init>(ViewRootImpl.java:548)
        at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:346)
        at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:94)
        at android.app.Dialog.show(Dialog.java:329)
        at android.support.v7.app.AlertDialog$Builder.show(AlertDialog.java:955)
        at com.fearlessbear.uiexperimentproject.window.DialogWindowRelationship.onCreate(DialogWindowRelationship.java:35)
        at android.app.Activity.performCreate(Activity.java:7224)
        at android.app.Activity.performCreate(Activity.java:7213)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1272)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2926)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3081)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1831)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:207)
        at android.app.ActivityThread.main(ActivityThread.java:6809)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:876)
 */
public class DialogWindowRelationship extends AppCompatActivity {

    private static final String TAG = "DialogWindow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_window_relationshop);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("dialog")
                .setMessage("message")
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.d(TAG, "onCancel: ");
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Log.d(TAG, "onDismiss: ");
                    }
                })
                .show();
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Log.d(TAG, "run: activity finish");
            }
        }, 5000);
    }
}
