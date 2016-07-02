package com.example.root.datashare;

import android.graphics.drawable.Drawable;

/**
 * Created by root on 1/7/16.
 */
public class AppDetails {
    protected String mName;
    protected String mAppLocation;
    protected String mLink;
    //protected int iconResourceId;
    protected boolean isCheckBoxSelected ;
    protected Drawable iconDrawableId;
    public AppDetails() {}

    public AppDetails(String name, Drawable iconDrawableId, String AppLocation) {
        this.mName = name;
        this.iconDrawableId = iconDrawableId;
        this.mAppLocation = AppLocation;
    }

    public void setAppName(String mName) {
        this.mName = mName;
    }

    public void setAppLocation(String mAppLocation) {
        this.mAppLocation = mAppLocation;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    /*public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }*/

    public String getAppLocation() {
        return mAppLocation;
    }

    public String getLink() {
        return mLink;
    }

    public String getAppName() {
        return mName;
    }

    public void setCheckBoxSelected(boolean checkBoxSelected) {
        this.isCheckBoxSelected = checkBoxSelected;
    }

    public boolean getCheckBoxSelected() {
        return isCheckBoxSelected;
    }

    public Drawable getIconDrawableId() {
        return iconDrawableId;
    }

    public void setIconDrawableId(Drawable iconDrawableId) {
        this.iconDrawableId = iconDrawableId;
    }

    /*public int getIconResourceId() {
        return iconResourceId;
    }*/

}
