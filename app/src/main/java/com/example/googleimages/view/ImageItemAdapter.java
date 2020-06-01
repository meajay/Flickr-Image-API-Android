package com.example.googleimages.view;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleimages.R;
import com.example.googleimages.model.PhotoDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemViewHolder> {

    private List<PhotoDetail> itemList ;

    public ImageItemAdapter(List<PhotoDetail> imageList) {
        itemList = imageList;
    }


    @NonNull
    @Override
    public ImageItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ImageItemViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageItemViewHolder holder, int position) {
        holder.setData(holder, itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ImageItemViewHolder extends RecyclerView.ViewHolder {
        public ImageItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(ImageItemViewHolder holder, final PhotoDetail imageItem) {
            try {

                Picasso.get().load(imageItem.getUrl()).placeholder(
                        itemView.getContext().getDrawable(R.drawable.ic_placeholder)).into(
                        (ImageView) holder.itemView.findViewById(R.id.imageView));

                if (imageItem.getTitle() != null) {
                    ((TextView) holder.itemView.findViewById(R.id.textview)).setText(
                            imageItem.getTitle());
                }
            } catch (Exception ex) {
                Log.e("ADAPTER", ex.getMessage());
            }

        }
    }
}
