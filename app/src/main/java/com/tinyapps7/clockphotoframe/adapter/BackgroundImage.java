package com.tinyapps7.clockphotoframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tinyapps7.clockphotoframe.PhotoImageActivity;
import com.tinyapps7.clockphotoframe.R;

/**
 * Created by Blurpixel on 4/21/2017.
 */

public class BackgroundImage extends RecyclerView.Adapter<BackgroundImage.Views> {
    int[] img;
    int selectedPosition = 0;
    Context context;
    LayoutInflater layoutInflater;
    private ItemClickListener_new1 mClickListener1;
    public BackgroundImage(int[] img, Context context) {
        this.img = img;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Views onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.frameview_new, parent, false);
        return new Views(view);
    }

    @Override
    public void onBindViewHolder(Views holder, int position) {
        holder.imageView.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }




    public class Views extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public Views(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.bg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener1 != null) mClickListener1.onItemClick1(v, getAdapterPosition());

            selectedPosition=getAdapterPosition();
        }
    }


    // allows clicks events to be caught
    public void setClickListener_new(ItemClickListener_new1 itemClickListener1) {
        this.mClickListener1 = itemClickListener1;
    }


    public interface ItemClickListener_new1 {
        void onItemClick1(View view, int position);


    }

}
