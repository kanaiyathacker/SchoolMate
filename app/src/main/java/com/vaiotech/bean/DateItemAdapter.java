package com.vaiotech.bean;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vaiotech.myschool.R;

import java.util.List;

/**
 * Created by kanaiyathacker on 30/10/2014.
 */
public class DateItemAdapter extends ArrayAdapter<Item> {

    Context context;
    Typeface font;

    public DateItemAdapter(Context context, int resourceId,
                       List<Item> items) {
        super(context, resourceId, items);
        this.context = context;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Calibri.ttf");
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
        TextView txtDate;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Item rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.date_list_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtDate = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(rowItem.getDescription());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.txtDate.setText(rowItem.getDate());

        holder.txtDesc.setTypeface(font);
        holder.txtTitle.setTypeface(font);
        holder.txtDate.setTypeface(font);

        convertView.setBackgroundColor(position % 2 == 0 ?Color.parseColor("#ffffff") : Color.parseColor("#d7d7d7"));
        return convertView;
    }
}
