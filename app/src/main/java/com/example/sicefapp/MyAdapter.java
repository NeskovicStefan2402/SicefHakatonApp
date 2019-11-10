package com.example.sicefapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import javax.xml.datatype.Duration;

public class MyAdapter extends BaseAdapter {
    Context context;
    private final LinkedList<String> values;
    private final LinkedList<Integer> images;

    public MyAdapter(Context context, LinkedList<String> values, LinkedList<Integer> images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.stavka, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.aNametxt);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.appIconIV);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(values.get(position));
        viewHolder.icon.setImageResource(images.get(position));

        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        ImageView icon;

    }

}