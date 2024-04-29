package com.example.crudoperationmvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudoperationmvvm.OperationInterface;
import com.example.crudoperationmvvm.R;
import com.example.crudoperationmvvm.UpdateItem_Activity;
import com.example.crudoperationmvvm.database.ItemTable;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<ItemTable> allItems;
    OperationInterface operationInterface;

    public RecyclerViewAdapter(Context context, List<ItemTable> allItems, OperationInterface operationInterface) {
        this.context = context;
        this.allItems = allItems;
        this.operationInterface = operationInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<ItemTable> allItems) {
        this.allItems = allItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemviewlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        ItemTable itemTable = allItems.get(position);
        holder.itemname1.setText(itemTable.getTaskName());
        holder.itemdesc1.setText(itemTable.getTaskDesc());

        holder.deleteItem.setOnClickListener(v -> {
            int id = itemTable.getId();
            operationInterface.deleteItem(id);
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateItem_Activity.class);
            intent.putExtra("id", itemTable.getId());
            intent.putExtra("itemname", itemTable.getTaskName());
            intent.putExtra("itemdesc", itemTable.getTaskDesc());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        Log.d("RecyclerVirwItem", "Received item list size in recycler view: " + allItems.size());

        return allItems != null ? allItems.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemname1, itemdesc1;
        ImageButton deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname1 = itemView.findViewById(R.id.itemname1);
            itemdesc1 = itemView.findViewById(R.id.itemdesc1);
            deleteItem = itemView.findViewById(R.id.deleteItem);
        }
    }
}
