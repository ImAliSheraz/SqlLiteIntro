package com.imalisheraz.sqlliteintro;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.RCVHolderClass> {

    ArrayList<ModelClass> objectModelClassList;

    public RCVAdapter(ArrayList<ModelClass> objectModelClassList) {
        this.objectModelClassList = objectModelClassList;
    }

    @NonNull
    @Override
    public RCVHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RCVHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RCVHolderClass holder, int position) {
        ModelClass objaectModelClass = objectModelClassList.get(position);
        holder.imageNameTV.setText(objaectModelClass.getImageName());
        holder.objectImageView.setImageBitmap(objaectModelClass.getImage());


    }

    @Override
    public int getItemCount() {
        return objectModelClassList.size();
    }

    public static class RCVHolderClass extends RecyclerView.ViewHolder
    {
        TextView imageNameTV;
        ImageView objectImageView;

        public RCVHolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTV=itemView.findViewById(R.id.imageDetailsTV);
            objectImageView=itemView.findViewById(R.id.singleimageIV);
        }
    }
}
