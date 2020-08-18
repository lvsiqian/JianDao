package com.example.last_22.Banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.last_22.R;

import java.util.ArrayList;

public class SimpleAdapetr  extends RecyclerView.Adapter<SimpleAdapetr.ViewHolder> {
    ArrayList<? extends BannerData> mdata;

    public SimpleAdapetr(ArrayList<? extends BannerData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position=position%mdata.size();
        if(position==0){
             holder.setdata(R.drawable.a);
        }else if(position==1){
            holder.setdata(R.drawable.b);
        }else if(position==2){
            holder.setdata(R.drawable.c);
        }else if(position==3){
            holder.setdata(R.drawable.v);
        }

    }

    @Override
    public int getItemCount() {
        return mdata==null?0:Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
        private  void setdata(int id){
            itemView.setBackgroundResource(id);
        }
    }
}
