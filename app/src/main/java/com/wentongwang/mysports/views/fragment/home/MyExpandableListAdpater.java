package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CircleImageView;
import com.wentongwang.mysports.model.module.SportsFirstClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 可展开列表的适配器FR
 * Created by Wentong WANG on 2016/11/3.
 */
public class MyExpandableListAdpater extends BaseExpandableListAdapter {

    private List<SportsFirstClass> sportTypes;
    private Context mContext;

    public MyExpandableListAdpater(Context mContext) {
        this.mContext = mContext;
        this.sportTypes = new ArrayList<>();
    }

    public MyExpandableListAdpater(Context mContext, List<SportsFirstClass> sportTypes) {
        this.sportTypes = sportTypes;
        this.mContext = mContext;
    }

    public void setSportTypes(List<SportsFirstClass> sportTypes) {
        this.sportTypes = sportTypes;
    }

    /**
     * 条目数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return sportTypes.size();
    }

    /**
     * 每条目里有几项内容
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return sportTypes.get(groupPosition).getSports().size();
    }

    /**
     * 获取条目
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return sportTypes.get(groupPosition);
    }

    /**
     * 获取某个条目中的某项
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sportTypes.get(groupPosition).getSports().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    /**
     * 返回条目布局
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_first_class, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }


        return convertView;
    }

    /**
     * 返回条目中每一项的布局
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_second_class, parent, false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        @BindView(R.id.sport_image)
        ImageView sport_image;
        @BindView(R.id.sport_type)
        TextView sport_type;
        @BindView(R.id.event_total_count)
        TextView event_total_count;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.creator_head)
        CircleImageView creator_head;
        @BindView(R.id.creator_name)
        TextView creator_name;
        @BindView(R.id.event_start_time)
        TextView event_start_time;
        @BindView(R.id.event_end_time)
        TextView event_end_time;
        @BindView(R.id.event_place)
        TextView event_place;
        @BindView(R.id.event_number_vs_total)
        TextView event_number_vs_total;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
