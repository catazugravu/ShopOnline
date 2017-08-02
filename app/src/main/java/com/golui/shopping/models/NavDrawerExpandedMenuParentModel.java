package com.golui.shopping.models;

import java.util.List;

/**
 * Created by RAVI on 7/27/2017.
 */

public class NavDrawerExpandedMenuParentModel {
    private String title = "";
    private List<NavDrawerExpandedMenuChildModel> expandedMenuChildModelList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NavDrawerExpandedMenuChildModel> getExpandedMenuChildModelList() {
        return expandedMenuChildModelList;
    }

    public void setExpandedMenuChildModelList(List<NavDrawerExpandedMenuChildModel> expandedMenuChildModelList) {
        this.expandedMenuChildModelList = expandedMenuChildModelList;
    }
}
