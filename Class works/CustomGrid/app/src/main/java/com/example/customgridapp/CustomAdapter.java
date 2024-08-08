package com.example.customgridapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import android.view.LayoutInflater;


public class CustomAdapter extends BaseAdapter {
    private Context context;
    private final String[] items;
    private final int[] ImageIds;
     public CustomAdapter(Context context,String[] items,int[]ImageIds){
         this.context=context;
         this.items=items;
         this.ImageIds=ImageIds;
     }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridViewItem;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridViewItem=inflater.inflate(R.layout.grid_item,viewGroup,false);
        }
        else{
            gridViewItem=view;
        }

        TextView textView=gridViewItem.findViewById(R.id.TextView);
        ImageView imageView=gridViewItem.findViewById(R.id.ImageView);

        textView.setText(items[i]);
        imageView.setImageResource(ImageIds[i]);

        return gridViewItem;
    }
}
