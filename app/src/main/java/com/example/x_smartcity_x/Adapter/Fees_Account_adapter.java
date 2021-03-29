package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.AccountByUserId;
import com.example.x_smartcity_x.bean.AccountGroup;

import java.util.List;
import java.util.Map;

public class Fees_Account_adapter extends BaseExpandableListAdapter {


    private List<AccountGroup> accountGroups;

    private Map<AccountGroup, List<AccountByUserId>> map;

    public Fees_Account_adapter(List<AccountGroup> accountGroups, Map<AccountGroup, List<AccountByUserId>> map) {
        this.map = map;
        this.accountGroups = accountGroups;
    }

    @Override
    public int getGroupCount() {
        return accountGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(accountGroups.get(groupPosition)).size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_adapter, parent, false);
        TextView itemType;
        itemType = convertView.findViewById(R.id.item_type);
        itemType.setText(accountGroups.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_adapter, parent, false);
        TextView itemType;
        itemType = convertView.findViewById(R.id.item_type);
        itemType.setText(accountGroups.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
