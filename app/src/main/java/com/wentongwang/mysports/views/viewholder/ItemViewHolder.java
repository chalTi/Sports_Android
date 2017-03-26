package com.wentongwang.mysports.views.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2017/3/26.
 */
public abstract class ItemViewHolder<Item> {

    protected View rootView;

    protected Item item;

    public ItemViewHolder(View view) {
        this.rootView = view;
        ButterKnife.bind(this, view);
    }

    public abstract void setItem(Item item);
}
