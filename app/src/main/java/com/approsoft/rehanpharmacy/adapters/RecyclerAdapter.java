package com.approsoft.rehanpharmacy.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.approsoft.rehanpharmacy.models.ListItem;
import com.approsoft.rehanpharmacy.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Approsoft on 1/9/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<ListItem> itemsList;
    Context context;
    int[] colorsArray;

    public RecyclerAdapter(ArrayList<ListItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
        TypedArray ta = context.getResources().obtainTypedArray(R.array.flatColors);
        colorsArray = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colorsArray[i] = ta.getColor(i, 0);
        }
        ta.recycle();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = itemsList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvItemName.setText(item.getItemName());
        myViewHolder.tvCompanyName.setText(item.getCompanyName());
        Random r = new Random();
        int randomInt = r.nextInt(8);
        int bgColor = colorsArray[randomInt];
        myViewHolder.tvItemThumb.setBackgroundColor(bgColor);
        myViewHolder.tvItemThumb.setText(String.valueOf(item.getItemName().charAt(0)));

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemName, tvItemThumb, tvCompanyName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemThumb = itemView.findViewById(R.id.tvItemThumb);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
        }
    }

    public void setFilter(ArrayList<ListItem> newList){
        itemsList = new ArrayList<>();
        itemsList.addAll(newList);
        notifyDataSetChanged();
    }
}
