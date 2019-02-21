package com.fearlessbear.uiexperimentproject.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fearlessbear.uiexperimentproject.R;

/*
 * 负一屏快捷卡片展开动画实验
 */
public class ExpandedFollowAnimActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_follow_anim);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new OuterAdapter());
        mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.expanded_follow_anim_list_header, mListView, false));
    }

    class OuterAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Integer getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        position != 0 ? android.R.layout.simple_list_item_1 : R.layout.expanded_follow_anim_expanded_view_item,
                        parent, false);
                if (convertView instanceof ExpandView) {
                    final ExpandView expandedView = (ExpandView) convertView;
                    expandedView.setListView(mListView);
                    ListView secList = convertView.findViewById(R.id.second_floor);
                    secList.setAdapter(new SecAdapter(convertView));
                }
            }
            if (position > 0) {
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(position + "");
            }
            return convertView;
        }
    }

    class SecAdapter extends BaseAdapter {
        View mExpandedView;
        int pos = 6;

        SecAdapter(View expandedView) {
            mExpandedView = expandedView;
        }

        @Override
        public int getCount() {
            return 40;
        }

        @Override
        public String getItem(int position) {
            return "二楼二楼二楼二楼二楼二楼";
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position == pos ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        position != pos ? android.R.layout.simple_list_item_2 : R.layout.expanded_follow_anim_sec_floor_target_item,
                        parent, false);
            }
            if (position != pos) {
                convertView.getLocationOnScreen(location);
                ((TextView) convertView.findViewById(android.R.id.text2)).setText(getItem(position) + " " + position);
            } else {
                final ViewTreeObserver observer = convertView.getViewTreeObserver();
                final View finalConvertView = convertView;
                observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        finalConvertView.getLocationOnScreen(location);
                        ((TextView) finalConvertView.findViewById(R.id.text)).setText("top is " + location[1]);
                        if (observer.isAlive()) {
                            observer.removeOnPreDrawListener(this);
                        }
                        Log.i("ExpandView", "mydebug second floor target top: " + finalConvertView.getTop());
                        return true;
                    }
                });
            }
            return convertView;
        }

        int[] location = new int[2];
    }
}
