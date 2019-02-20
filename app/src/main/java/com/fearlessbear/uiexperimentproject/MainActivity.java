package com.fearlessbear.uiexperimentproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fearlessbear.uiexperimentproject.recyclerView.RecyclerActivity;

public class MainActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter());
    }

    private static class MyAdapter extends BaseAdapter {
        static String[] data =
                {
                        "RecyclerView"
                };
        static Class[] classes =
                {
                        RecyclerActivity.class
                };

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
            }
            TextView text = convertView.findViewById(android.R.id.text1);
            text.setText(data[position]);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.getContext().startActivity(new Intent(parent.getContext(), classes[position]));
                }
            });
            return convertView;
        }
    }

}
