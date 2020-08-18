package com.lvsiqianlib_banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpeBannerAdapter extends RecyclerView.Adapter<SimpeBannerAdapter.ViewHolder> {
    private ArrayList<?extends BannerData> mdata;
    private static int mCount =0;


    public SimpeBannerAdapter(ArrayList<? extends BannerData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public SimpeBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
       imageView.setTag(mCount);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpeBannerAdapter.ViewHolder holder, int position) {

        position=position % mdata.size();

        if(position==0){
            holder.setData(R.drawable.a);

        }else if(position==1){
            holder.setData(R.drawable.b);
        }else if(position==2){
            holder.setData(R.drawable.c);
        }else if(position==3){
            holder.setData(R.drawable.v);
        }
    }

    @Override
    public int getItemCount() {
        return mdata == null ? 0:Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        private  void setData(int id){
            itemView.setBackgroundResource(id);
        }
    }
}
