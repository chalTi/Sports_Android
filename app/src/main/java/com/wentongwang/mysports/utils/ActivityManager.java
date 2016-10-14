package com.wentongwang.mysports.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * util manage all activities
 * Created by Wentong WANG on 2016/10/14.
 */
public class ActivityManager {

    //list for each activity
    private List<Activity> mList = new ArrayList<>();

    private static ActivityManager instance;
    //private constracteur
    private ActivityManager(){}
    //just can creat one instance
    public synchronized static ActivityManager getInstance(){
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }
    // add Activity in the mList
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    //close all Activities
    public void exit() {
        try {
            for (Activity activity:mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    //finsh an activity and pop this activity from the mList
    public void exit(Activity activity) {
        mList.remove(activity);
        activity.finish();
    }
}
