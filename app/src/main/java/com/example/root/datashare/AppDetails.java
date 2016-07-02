package com.example.root.datashare;

/**
 * Created by root on 1/7/16.
 */
public class AppDetails {
    protected String mName;
    protected String mAppLocation;
    protected String mLink;
    protected int iconResourceId;
    protected boolean isCheckBoxSelected ;

    public AppDetails() {}

    public AppDetails(String name, int iconResourceId) {
        this.mName = name;
        this.iconResourceId = iconResourceId;
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

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getAppLocation() {
        return mAppLocation;
    }

    public String getLink() {
        return mLink;
    }

    public String getAppName() {
        return mName;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setCheckBoxSelected(boolean checkBoxSelected) {
        isCheckBoxSelected = checkBoxSelected;
    }

    public boolean getCheckBoxSelected() {
        return isCheckBoxSelected;
    }

}
