package com.fearlessbear.uiexperimentproject.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fearlessbear.uiexperimentproject.R;

/**
 * 1. GridLayout设置横向item数量之后，会自动计算每个item宽度的最大值，这个最大值和LinearLayout中使用
 *    weight属性得到的值一样。计算过程应该就是拿到RecyclerView宽度，然后求平均值。
 *    也就是说，如果item的宽度设置的大于了这个最大值，也不会出现前面的item将后面的item挤出屏幕的情况，
 *    item可以显示的最大宽度就是GridLayout计算好的最大宽度。
 */

public class GridLayoutItemNumActivity extends AppCompatActivity {
    RecyclerView rv;

    int[] colorResList = {
            android.R.color.holo_red_light,
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_purple,
            android.R.color.black,
            android.R.color.darker_gray,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_item_num);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new GridLayoutManager(this, 6));
        rv.setAdapter(new RecyclerImageAdapter(3));
    }

    public class RecyclerImageAdapter extends RecyclerView.Adapter {
        int itemCount;

        public RecyclerImageAdapter(int itemCount) {
            super();
            this.itemCount = itemCount;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = View.inflate(parent.getContext(), R.layout.recycler_image_item, null);

            return new RecyclerView.ViewHolder(itemView) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView image = holder.itemView.findViewById(R.id.image);
            ViewGroup.LayoutParams lp = image.getLayoutParams();
            lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, getResources().getDisplayMetrics());
            lp.height = lp.width;
            image.setLayoutParams(lp);
            image.setImageResource(colorResList[position % colorResList.length]);
        }

        @Override
        public int getItemCount() {
            return itemCount;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
