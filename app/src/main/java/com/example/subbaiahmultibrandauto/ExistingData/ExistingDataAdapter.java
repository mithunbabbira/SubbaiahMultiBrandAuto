package com.example.subbaiahmultibrandauto.ExistingData;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subbaiahmultibrandauto.R;
import com.example.subbaiahmultibrandauto.entities.Data;

import java.util.List;

public class ExistingDataAdapter  extends RecyclerView.Adapter<ExistingDataAdapter.ViewHolder>{

    private Context context ;
    private  List<Data> dataList ;


    public ExistingDataAdapter (Context context, List<Data> dataList) {
        this.context = context  ;
        this.dataList = dataList;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_existing_data, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText(dataList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date =  itemView.findViewById(R.id.date);



//            itemView.setOnClickListener(this);
//            itemView.setOnCreateContextMenuListener(this);
        }

    }
}
