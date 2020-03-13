package com.fearlessbear.uiexperimentproject.animation.object;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.fearlessbear.uiexperimentproject.R;

/*
 * LinearLayout，对其child做translate动画:
 * 索引小的View执行translationX跨过索引大的View时，是从索引大的view的下面穿过的。
 * 索引大的View是从索引小的view上面穿过的。
 */
public class TranslationAnimationActivity extends AppCompatActivity {

    private LinearLayout container;
    private LinearLayout container1;
    int i = 0;
    int j = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation_animation);

        container = (LinearLayout) findViewById(R.id.container);
        container.getChildAt(0).setZ(100f);
        container1 = (LinearLayout) findViewById(R.id.container1);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 3) {
                    i = 0;
                }
                View child = container.getChildAt(i++);
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "TranslationX", 500f);
                animator.setDuration(5000);
                animator.start();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j < 0) {
                    j = 3;
                }
                View child = container1.getChildAt(j--);
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "TranslationX", -500f);
                animator.setDuration(5000);
                animator.start();
            }
        });
    }
}
