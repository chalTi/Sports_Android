package com.wentongwang.mysports.views.custome;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.widget.adapters.AbstractWheelTextAdapter;
import com.wentongwang.mysports.widget.views.OnWheelChangedListener;
import com.wentongwang.mysports.widget.views.OnWheelScrollListener;
import com.wentongwang.mysports.widget.views.WheelView;

import java.util.ArrayList;
import java.util.Calendar;



/**
 * Created by Wentong WANG on 2016/7/29.
 */
public class ChangeTimeDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private WheelView wvHour;
    private WheelView wvMin;
    private WheelView wvSecond;
    private View secondUnit;

    private View vChangeBirth;
    private View vChangeBirthChild;
    private TextView btnSure;
    private TextView btnCancel;

    private ArrayList<String> arry_years = new ArrayList<String>();
    private ArrayList<String> arry_months = new ArrayList<String>();
    private ArrayList<String> arry_days = new ArrayList<String>();
    private ArrayList<String> array_hours = new ArrayList<String>();
    private ArrayList<String> array_mins = new ArrayList<String>();
    private ArrayList<String> array_seconds = new ArrayList<String>();

    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;
    private CalendarTextAdapter mHourAdapter;
    private CalendarTextAdapter mMinAdapter;
    private CalendarTextAdapter mSecondAdapter;

    private int currentYear = getYear();
    private int currentMonth = 1;
    private int currentDay = 1;
    private int currentHour = 1;
    private int currentMin = 0;
    private int currentSecond = 0;

    private int maxTextSize = 24;
    private int minTextSize = 14;

    private String selectYear;
    private String selectMonth;
    private String selectDay;
    private String selectHour;
    private String selectMin;
    private String selectSecond;

    private boolean useSecond = false;
    private OnBirthListener onBirthListener;
    private OnTimeHasSecondListener onTimeHasSecondListener;

    public ChangeTimeDialog(Context context, boolean b) {
        super(context, R.style.ShareDialog);
        this.context = context;
        this.setCanceledOnTouchOutside(b);
    }

    /**
     * 可以开启精确到秒的构造函数
     * @param context
     * @param b
     * @param useSecond 是否开启精确到秒
     */
    public ChangeTimeDialog(Context context, boolean b, boolean useSecond) {
        super(context, R.style.ShareDialog);
        this.context = context;
        this.setCanceledOnTouchOutside(b);
        this.useSecond = useSecond;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_time);
        wvYear = (WheelView) findViewById(R.id.wv_birth_year);
        wvMonth = (WheelView) findViewById(R.id.wv_birth_month);
        wvDay = (WheelView) findViewById(R.id.wv_birth_day);
        wvHour = (WheelView) findViewById(R.id.wv_birth_hour);
        wvMin = (WheelView) findViewById(R.id.wv_birth_min);
        wvSecond = (WheelView) findViewById(R.id.wv_birth_second);
        secondUnit = findViewById(R.id.wv_second_unit);

        vChangeBirth = findViewById(R.id.ly_myinfo_changebirth);
        vChangeBirthChild = findViewById(R.id.ly_myinfo_changebirth_child);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

        vChangeBirth.setOnClickListener(this);
        vChangeBirthChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        initYears();
        mYearAdapter = new CalendarTextAdapter(context, arry_years, setYear(currentYear), maxTextSize, minTextSize);
        wvYear.setVisibleItems(5);
        wvYear.setViewAdapter(mYearAdapter);
        wvYear.setCurrentItem(setYear(currentYear));

        initMonths();
        mMonthAdapter = new CalendarTextAdapter(context, arry_months, setMonth(currentMonth), maxTextSize, minTextSize);
        wvMonth.setVisibleItems(5);
        wvMonth.setViewAdapter(mMonthAdapter);
        wvMonth.setCurrentItem(setMonth(currentMonth));

        initDays();
        mDaydapter = new CalendarTextAdapter(context, arry_days, setDay(currentDay), maxTextSize, minTextSize);
        wvDay.setVisibleItems(5);
        wvDay.setViewAdapter(mDaydapter);
//        wvDay.setCurrentItem(currentDay - 1);
        wvDay.setCurrentItem(setDay(currentDay));

        initHours();
        mHourAdapter = new CalendarTextAdapter(context, array_hours, setHour(currentHour), maxTextSize, minTextSize);
        wvHour.setVisibleItems(5);
        wvHour.setViewAdapter(mHourAdapter);
//        wvHour.setCurrentItem(currentHour);
        wvHour.setCurrentItem(setHour(currentHour));

        initMin();
        mMinAdapter = new CalendarTextAdapter(context, array_mins, setMin(currentMin), maxTextSize, minTextSize);
        wvMin.setVisibleItems(5);
        wvMin.setViewAdapter(mMinAdapter);
//        wvMin.setCurrentItem(currentMin);
        wvMin.setCurrentItem(setMin(currentMin));

        if (useSecond) {
            initSecond();
            mSecondAdapter = new CalendarTextAdapter(context, array_seconds, setSecond(currentSecond), maxTextSize, minTextSize);
            wvSecond.setVisibleItems(5);
            wvSecond.setViewAdapter(mSecondAdapter);
            wvSecond.setCurrentItem(setSecond(currentSecond));

            wvSecond.addChangingListener(new OnWheelChangedListener() {

                @Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    String currentText = (String) mSecondAdapter.getItemText(wheel.getCurrentItem());
                    setTextviewSize(currentText, mSecondAdapter);
                    selectSecond = currentText;

                }
            });

            wvSecond.addScrollingListener(new OnWheelScrollListener() {

                @Override
                public void onScrollingStarted(WheelView wheel) {
                }

                @Override
                public void onScrollingFinished(WheelView wheel) {
                    String currentText = (String) mSecondAdapter.getItemText(wheel.getCurrentItem());
                    setTextviewSize(currentText, mSecondAdapter);
                }
            });
        } else {
            wvSecond.setVisibility(View.GONE);
            secondUnit.setVisibility(View.GONE);
        }


        wvYear.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                selectYear = currentText;
                setTextviewSize(currentText, mYearAdapter);
                currentYear = Integer.parseInt(currentText);
                initMonths();
                mMonthAdapter = new CalendarTextAdapter(context, arry_months, 0, maxTextSize, minTextSize);
                wvMonth.setVisibleItems(5);
                wvMonth.setViewAdapter(mMonthAdapter);
                if (getYear() == currentYear)
                    wvMonth.setCurrentItem(setMonth(currentMonth));
                else
                    wvMonth.setCurrentItem(0);

                wvMonth.notifyChangingListeners(0, 0);

            }
        });

        wvYear.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        wvMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);

                currentMonth = Integer.parseInt(currentText);
                initDays();
                mDaydapter = new CalendarTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
                wvDay.setVisibleItems(5);
                wvDay.setViewAdapter(mDaydapter);
                if (getYear() == currentYear && getMonth() == currentMonth)
                    wvDay.setCurrentItem(setDay(currentDay));
                else
                    wvDay.setCurrentItem(0);

                wvDay.notifyChangingListeners(0, 0);
            }
        });

        wvMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;


                currentDay = Integer.parseInt(currentText);
                initHours();
                mHourAdapter = new CalendarTextAdapter(context, array_hours, 0, maxTextSize, minTextSize);
                wvHour.setVisibleItems(5);
                wvHour.setViewAdapter(mHourAdapter);
                if (getYear() == currentYear && getMonth() == currentMonth && getDay() == currentDay)
                    wvHour.setCurrentItem(setHour(currentHour));
                else
                    wvHour.setCurrentItem(0);

                wvHour.notifyChangingListeners(0, 0);

            }
        });

        wvDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });

        wvHour.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mHourAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mHourAdapter);
                selectHour = currentText;

                currentHour = Integer.parseInt(currentText);
                initMin();
                mMinAdapter = new CalendarTextAdapter(context, array_mins, 0, maxTextSize, minTextSize);
                wvMin.setVisibleItems(5);
                wvMin.setViewAdapter(mMinAdapter);
                if (getYear() == currentYear && getMonth() == currentMonth && getDay() == currentDay && getHour() == currentHour)
                    wvMin.setCurrentItem(setMin(currentMin));
                else
                    wvMin.setCurrentItem(0);

                wvMin.notifyChangingListeners(0, 0);
            }
        });

        wvHour.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mHourAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mHourAdapter);
            }
        });

        wvMin.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mMinAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMinAdapter);
                selectMin = currentText;

                currentMin = Integer.parseInt(currentText);

                if (useSecond) {
                    initSecond();
                    mSecondAdapter = new CalendarTextAdapter(context, array_seconds, 0, maxTextSize, minTextSize);
                    wvSecond.setVisibleItems(5);
                    wvSecond.setViewAdapter(mSecondAdapter);
                    if (getYear() == currentYear && getMonth() == currentMonth && getDay() == currentDay && getHour() == currentHour && getMin() == currentMin){
                        wvSecond.setCurrentItem(setSecond(getSecond()));
                    }
                    else{
                        wvSecond.setCurrentItem(0);
                    }
                    wvSecond.notifyChangingListeners(0, 0);
                }
            }
        });

        wvMin.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mMinAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMinAdapter);
            }
        });
    }

    public void initYears() {
        for (int i = getYear(); i <= getYear() + 10; i++) {
            arry_years.add(i + "");
        }
    }

    public void initMonths() {
        arry_months.clear();
        if (currentYear == getYear()) {
            for (int i = getMonth(); i <= 12; i++) {
                arry_months.add(i + "");
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                arry_months.add(i + "");
            }
        }
    }

    public void initDays() {
        arry_days.clear();
        if (currentYear == getYear() && currentMonth == getMonth()) {
            for (int i = getDay(); i <= calDays(currentYear, currentMonth); i++) {
                arry_days.add(i + "");
            }
        } else {
            for (int i = 1; i <= calDays(currentYear, currentMonth); i++) {
                arry_days.add(i + "");
            }
        }
    }

    public void initHours() {
        array_hours.clear();
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay()) {
            for (int i = getHour(); i < 24; i++) {
                array_hours.add(i + "");
            }
        } else {
            for (int i = 0; i < 24; i++) {
                array_hours.add(i + "");
            }
        }
    }

    public void initMin() {
        array_mins.clear();
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay() && currentHour == getHour()) {
            for (int i = getMin(); i < 60; i++) {
                array_mins.add(i + "");
            }
        } else {
            for (int i = 0; i < 60; i++) {
                array_mins.add(i + "");
            }
        }
    }

    public void initSecond() {
        array_seconds.clear();
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay() && currentHour == getHour() && currentMin == getMin()) {
            for (int i = getSecond(); i < 60; i++) {
                array_seconds.add(i + "");
            }
        } else {
            for (int i = 0; i < 60; i++) {
                array_seconds.add(i + "");
            }
        }
    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.dialog_item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    public void setBirthdayListener(ChangeTimeDialog.OnBirthListener onBirthListener) {
        this.onBirthListener = onBirthListener;
    }

    public void setTimeHasSecondListener(ChangeTimeDialog.OnTimeHasSecondListener onTimeHasSecondListener) {
        this.onTimeHasSecondListener = onTimeHasSecondListener;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSure) {
            if (onBirthListener != null) {
                onBirthListener.onClick(selectYear, selectMonth, selectDay, selectHour, selectMin);
            }

            if (onTimeHasSecondListener != null) {
                onTimeHasSecondListener.onClick(selectYear, selectMonth, selectDay, selectHour, selectMin, selectSecond);
            }

        } else if (v == btnSure) {

        } else if (v == vChangeBirthChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();

    }

    public interface OnBirthListener {
        public void onClick(String year, String month, String day, String hour, String min);
    }

    public interface OnTimeHasSecondListener {
        public void onClick(String year, String month, String day, String hour, String min, String second);
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    public int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    public int getHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int getMin() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);
    }

    public int getSecond() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.SECOND);
    }

    /**
     * 设置年月日时分
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day, int hour, int min) {
        selectYear = year + "";
        selectMonth = month + "";
        selectDay = day + "";
        selectHour = hour + "";
        selectMin = min + "";
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        this.currentHour = hour;
        this.currentMin = min;
    }

    /**
     * 设置年月日时分秒
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day, int hour, int min, int second) {
        selectYear = year + "";
        selectMonth = month + "";
        selectDay = day + "";
        selectHour = hour + "";
        selectMin = min + "";
        selectSecond = second + "";
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        this.currentHour = hour;
        this.currentMin = min;
        this.currentSecond = second;
    }

    /**
     * 设置年份
     *
     * @param year
     */
    public int setYear(int year) {
        int yearIndex = 0;
        for (int i = getYear(); i <= getYear() + 10; i++) {
            if (i == year) {
                return yearIndex;
            }
            yearIndex++;
        }
        return yearIndex;
    }

    /**
     * 设置月份
     *
     * @param
     * @param month
     * @return
     */
    public int setMonth(int month) {
        int monthIndex = 0;

        if (getYear() == currentYear) {
            for (int i = month; i <= 12; i++) {
                if (month == i) {
                    return monthIndex;
                } else {
                    monthIndex++;
                }
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                if (month == i) {
                    return monthIndex;
                } else {
                    monthIndex++;
                }
            }
        }
        return monthIndex;
    }

    public int setDay(int day) {
        int dayIndex = 0;
        int days = calDays(currentYear, currentMonth);
        if (currentYear == getYear() && currentMonth == getMonth()) {
            for (int i = getDay(); i <= days; i++) {
                if (day == i) {
                    return dayIndex;
                } else {
                    dayIndex++;
                }
            }
        } else {
            for (int i = 1; i <= days; i++) {
                if (day == i) {
                    return dayIndex;
                } else {
                    dayIndex++;
                }
            }
        }
        return dayIndex;
    }

    public int setHour(int hour) {
        int hourIndex = 0;
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay()) {
            for (int i = getHour(); i < 24; i++) {
                if (hour == i) {
                    return hourIndex;
                } else {
                    hourIndex++;
                }
            }
        } else {
            for (int i = 0; i < 24; i++) {
                if (hour == i) {
                    return hourIndex;
                } else {
                    hourIndex++;
                }
            }
        }
        return hourIndex;
    }

    public int setMin(int min) {
        int minIndex = 0;
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay() && currentMin == getMin()) {
            for (int i = getMin(); i < 60; i++) {
                if (min == i) {
                    return minIndex;
                } else {
                    minIndex++;
                }
            }
        } else {
            for (int i = 0; i < 60; i++) {
                if (min == i) {
                    return minIndex;
                } else {
                    minIndex++;
                }
            }
        }
        return minIndex;
    }

    public int setSecond(int second) {
        int secondIndex = 0;
        if (currentYear == getYear() && currentMonth == getMonth() && currentDay == getDay() && currentMin == getMin()) {
            for (int i = second; i < 60; i++) {
                if (second == i) {
                    return secondIndex;
                } else {
                    secondIndex++;
                }
            }
        } else {
            for (int i = 0; i < 60; i++) {
                if (second == i) {
                    return secondIndex;
                } else {
                    secondIndex++;
                }
            }
        }
        return secondIndex;
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param
     */
    public int calDays(int year, int month) {
        boolean leayyear = false;
        int day = 0;
        if (year % 4 == 0 && year % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    day = 31;
                    break;
                case 2:
                    if (leayyear) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    day = 30;
                    break;
            }
        }
        return day;
    }


}
