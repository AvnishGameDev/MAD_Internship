package com.avnishgamedev.internshipapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShoppingListItem> items;

    public ShoppingListAdapter(Context context, ArrayList<ShoppingListItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        CheckBox checkbox;
        TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.support_shoppinglist_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkbox = convertView.findViewById(R.id.CB_Completed);
            viewHolder.name = convertView.findViewById(R.id.TXT_ItemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShoppingListItem currentItem = items.get(position);
        viewHolder.name.setText(currentItem.name);
        viewHolder.checkbox.setChecked(currentItem.isCompleted);

        viewHolder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentItem.isCompleted = isChecked;
        });

        return convertView;
    }
}
