package com.fearlessbear.uiexperimentproject.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.fearlessbear.uiexperimentproject.R;

/*
 * EditText.getText()只有在构造的时候才有可能返回null
 */

public class InputTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_type);
        EditText et = (EditText) findViewById(R.id.text);
        String trim = et.getText().toString().trim();

    }
}
