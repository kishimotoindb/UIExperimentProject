package com.fearlessbear.uiexperimentproject.motionEventDispatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fearlessbear.uiexperimentproject.R;

/**
 * 上下两个重叠在一起的View，如果均可以点击，那么只会上面的View的点击事件会被处理，而下面的不会。这里不确定是
 * 因为touchTarget只有上面的view，所以只处理上面的view；还是touchTarget有俩，但是因为上面的view处理了事件，
 * 所以事件不会再下发到bottom的View。
 */
public class TouchTarget1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_target1_actiivty);

        final View bottom = findViewById(R.id.tvBottom);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchTarget1Activity.this, "bottom", Toast.LENGTH_LONG).show();
            }
        });

        final View top = findViewById(R.id.tvTop);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.setBackgroundResource(android.R.color.black);
            }
        });
    }
}
