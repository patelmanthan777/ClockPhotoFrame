package com.tinyapps7.clockphotoframe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tinyapps7.clockphotoframe.R;

/**
 * Created by Blurpixel on 4/20/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    int[] img;
    int selectedPosition = 0;
    Context context;
    LayoutInflater layoutInflater;
    private ItemClickListener mClickListener;
    public MyAdapter(int[] img, Context context) {
        this.img = img;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.frameview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.imageView.setImageResource(img[position]);

//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedPosition = position;
//                notifyDataSetChanged();
//                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        if (selectedPosition == position)
//            holder.itemView.setBackgroundResource(R.drawable.border_recycle);
        //else
        //  holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());

            selectedPosition=getAdapterPosition();
        }
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }

}
