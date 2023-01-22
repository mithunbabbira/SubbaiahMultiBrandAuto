package com.example.subbaiahmultibrandauto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subbaiahmultibrandauto.Listener;
import com.example.subbaiahmultibrandauto.R;

import java.util.List;

public class IndividualRepairAdapter extends RecyclerView.Adapter<IndividualRepairAdapter.ViewHolder>{

    private Context context ;
    private List<String> items ;
    private ViewHolder viewHolder = null;
    private Listener listener;

    public IndividualRepairAdapter(Context context, List<String> items, Listener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.repair_item_adapter,parent ,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndividualRepairAdapter.ViewHolder ViewHolder, int position) {
        ViewHolder.itemTv.setText(items.get(position));

        viewHolder.close.setOnClickListener(view -> {
            listener.deletePosition(position);
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView  itemTv;
         ImageView close ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTv = itemView.findViewById(R.id.itemTv);
            close = itemView.findViewById(R.id.close);
        }
    }


    public void updateItem(List<String> data) {
        this.items = data ;
        notifyDataSetChanged();
    }
}
