package com.wentongwang.mysports.views.activity.createvent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * creat event layout
 *
 * Created by Wentong WANG on 2016/9/9.
 */
public class CreatEventActivity extends BaseActivity implements CreatEventView {

    @BindView(R.id.event_type_container)
    protected GridView mGridView;

    private List<MyEvents> myEvents;
    private MyGridViewAdapter adapter;


    @Override
    protected boolean notitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_event_layout;
    }

    @Override
    protected void initDatasAndViews() {
        myEvents = new ArrayList<>();
        //Event从服务器获取比较好一点
        myEvents.add(new MyEvents(R.drawable.basketball, "basketball"));
        myEvents.add(new MyEvents(R.drawable.soccerball, "soccerball"));
        myEvents.add(new MyEvents(R.drawable.football, "football"));
        myEvents.add(new MyEvents(R.drawable.volleyball, "volleyball"));
        myEvents.add(new MyEvents(R.drawable.badminton, "badminton"));
        myEvents.add(new MyEvents(R.drawable.pingpang, "pingpang"));
        myEvents.add(new MyEvents(R.drawable.tennis, "tennis"));
        myEvents.add(new MyEvents(R.drawable.bicycle, "bicycle"));
        myEvents.add(new MyEvents(R.drawable.running, "running"));
        myEvents.add(new MyEvents(R.drawable.swimming, "swimming"));
        myEvents.add(new MyEvents(R.drawable.exercise, "exercise"));
        myEvents.add(new MyEvents(R.drawable.boxing, "boxing"));

        adapter = new MyGridViewAdapter();

        ButterKnife.bind(CreatEventActivity.this);

        mGridView.setAdapter(adapter);

    }

    @Override
    protected void initEvents() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < myEvents.size(); i++) {
                    myEvents.get(i).setIsSelected(false);
                }

                for (int i = 0; i < myEvents.size(); i++) {
                    if (i == position) {
                        if (myEvents.get(i).isSelected()) {
                            myEvents.get(i).setIsSelected(false);
                        } else {
                            myEvents.get(i).setIsSelected(true);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }


    private class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return myEvents.size();
        }

        @Override
        public Object getItem(int position) {
            return myEvents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {

                convertView = LayoutInflater.from(CreatEventActivity.this).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            MyEvents event = myEvents.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), event.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);

            if (event.isSelected()) {
                holder.selected.setVisibility(View.VISIBLE);
            } else {
                holder.selected.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }

        private class ViewHolder {
            ImageView event_icon;
            ImageView selected;

            public ViewHolder(View view){
                event_icon = (ImageView) view.findViewById(R.id.event_icon);
                selected = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }
    }

    /**
     * EventItem
     */
    private class MyEvents {

        //resource id of image
        private int event_image;
        //this item is selected
        private boolean isSelected = false;
        //sport's name
        private String event_name;


        public MyEvents() {

        }

        public MyEvents(int image, String name) {
            this.event_image = image;
            this.event_name = name;
        }

        public int getEvent_image() {
            return event_image;
        }

        public void setEvent_image(int event_image) {
            this.event_image = event_image;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getEvent_name() {
            return event_name;
        }

        public void setEvent_name(String event_name) {
            this.event_name = event_name;
        }
    }
}

