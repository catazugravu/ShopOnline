package com.golui.shopping.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.golui.shopping.R;
import com.golui.shopping.models.NavDrawerExpandedMenuChildModel;
import com.golui.shopping.models.NavDrawerExpandedMenuParentModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by RAVI on 7/27/2017.
 */

public class NavDrawerExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<NavDrawerExpandedMenuParentModel> mNavDrawerParentModelList; // header titles

    ExpandableListView expandList;

    public NavDrawerExpandableListAdapter(Context context, List<NavDrawerExpandedMenuParentModel> listDataHeader, ExpandableListView mView) {
        this.mContext = context;
        this.mNavDrawerParentModelList = listDataHeader;
        this.expandList = mView;
    }

    @Override
    public int getGroupCount() {
        int i = mNavDrawerParentModelList.size();
        return this.mNavDrawerParentModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return mNavDrawerParentModelList.get(groupPosition).getExpandedMenuChildModelList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mNavDrawerParentModelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return mNavDrawerParentModelList.get(groupPosition).getExpandedMenuChildModelList().get(groupPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        NavDrawerExpandedMenuParentModel navDrawerExpandedMenuParentModel = (NavDrawerExpandedMenuParentModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listheader, null);
        }
        android.support.v7.widget.AppCompatImageView iconimage = (android.support.v7.widget.AppCompatImageView) convertView.findViewById(R.id.iconimage);
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.submenu);
        iconimage.setSelected(isExpanded);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(navDrawerExpandedMenuParentModel.getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final NavDrawerExpandedMenuChildModel navDrawerExpandedMenuChildModel = (NavDrawerExpandedMenuChildModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_submenu, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(navDrawerExpandedMenuChildModel.getTitle());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
