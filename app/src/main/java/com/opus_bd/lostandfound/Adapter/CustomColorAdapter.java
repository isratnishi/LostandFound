package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;

import java.util.List;

public class CustomColorAdapter extends BaseAdapter {
    Context context;
  List<String> ColorList;
  List<String> ColorCode;
    LayoutInflater inflter;

    public CustomColorAdapter(Context context, List<String> iconList, List<String> names) {
        this.context = context;
        this.ColorList = iconList;
        this.ColorCode = names;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return ColorList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setBackgroundColor(Integer.parseInt(ColorCode.get(i)));
/*        icon.setImageResource(flags[i]);*/
        /*try{
            Glide.with(context).load("http://103.134.88.13:1022/"+iconList.get(i)).into(icon);
        }
        catch (Exception e){}*/
        names.setText(ColorList.get(i));
        return view;
    }
}