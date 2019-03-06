package com.fearlessbear.uiexperimentproject._base;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends android.widget.BaseAdapter {

    String[] data;
    Class[] classes;

    public MyAdapter(String[] data, Class[] classes) {
        this.data = data;
        this.classes = classes;
    }

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
