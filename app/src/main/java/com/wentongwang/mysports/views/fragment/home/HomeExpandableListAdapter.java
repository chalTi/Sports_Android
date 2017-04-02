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
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.views.viewholder.HomeSportsItemViewHolder;
import com.wentongwang.mysports.views.viewholder.HomeSportsTypeViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 可展开列表的适配器FR
 * Created by Wentong WANG on 2016/11/3.
 */
public class HomeExpandableListAdapter extends BaseExpandableListAdapter {

    private List<SportsFirstClass> sportTypes;
    private Context mContext;

    public HomeExpandableListAdapter(Context mContext) {
        this.mContext = mContext;
        this.sportTypes = new ArrayList<>();
    }

    public void setSports(List<SportsFirstClass> sportTypes) {
        this.sportTypes = sportTypes;
        notifyDataSetChanged();
    }


    /**
     * 条目数量
     * @return
     */
    @Override
    public int getGroupCount() {
        return sportTypes != null ? sportTypes.size() : 0;
    }

    /**
     * 每条目里有几项内容
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return sportTypes.get(groupPosition).getSports()!= null ? sportTypes.get(groupPosition).getSports().size() : 0;
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
        HomeSportsTypeViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_first_class, parent, false);
            holder = new HomeSportsTypeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomeSportsTypeViewHolder) convertView.getTag();
        }

        holder.setItem(sportTypes.get(groupPosition));
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
        HomeSportsItemViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_second_class, parent, false);
            holder = new HomeSportsItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomeSportsItemViewHolder) convertView.getTag();
        }

        SportsSecondClass item = sportTypes.get(groupPosition).getSports().get(childPosition);
        holder.setItem(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
