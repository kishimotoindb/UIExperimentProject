package com.fearlessbear.uiexperimentproject.animation.tween;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.fearlessbear.uiexperimentproject.R;

public class TranslateAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private View mTranslateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_anim);

        mTranslateView = findViewById(R.id.translate);
        mTranslateView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.translate:
                goTranslate();
                break;
        }
    }

    private void goTranslate() {
        // translate动画，不论是哪种type，位置都是相对于view当前的位置作为基准。
        TranslateAnimation anim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2f);
        anim.setDuration(5000);
        anim.setRepeatCount(3);
        anim.setRepeatMode(Animation.RESTART);
        mTranslateView.startAnimation(anim);
    }
}
