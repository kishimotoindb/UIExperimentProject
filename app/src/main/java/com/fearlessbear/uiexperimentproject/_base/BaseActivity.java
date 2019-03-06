package com.fearlessbear.uiexperimentproject._base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.fearlessbear.uiexperimentproject.R;

public class BaseActivity extends Activity {
    protected ListView listView;
    protected String[] mTitle;
    protected Class[] mClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(mTitle, mClasses));
    }
}
