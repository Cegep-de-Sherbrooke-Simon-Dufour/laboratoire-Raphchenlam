package com.example.lab_5_1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_5_1.R;
import com.example.lab_5_1.data.Item;

public class ItemAdapter extends ListAdapter<Item, ItemAdapter.ItemViewHolder> {

    RecyclerCallback<Item> itemRecyclerCallback;
    public ItemAdapter(RecyclerCallback<Item> itemRecyclerCallback) {
        super(new DiffUtil.ItemCallback<Item>() {

            @Override
            public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                return oldItem.itemId == newItem.itemId;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                return oldItem.nameOfItem.equals(newItem.nameOfItem);
            }
        });
        this.itemRecyclerCallback = itemRecyclerCallback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        Item item;
        TextView name;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemNameTextView);
            itemView.setOnClickListener( v -> {
                itemRecyclerCallback.returnValue(item);
            });
        }

        public void bind(Item item){
            this.item = item;
            name.setText(item.nameOfItem);
        }
    }
}
