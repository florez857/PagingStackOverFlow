package com.example.android.pagingstackoverflow;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.pagingstackoverflow.Model.Item;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.getOwner().getDisplayName());
            Glide.with(mCtx)
                    .load(item.getOwner().getProfileImage())
                    .into(holder.imageView);
        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.getQuestionId() == newItem.getQuestionId();
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}