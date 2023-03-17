package com.example.subbaiahmultibrandauto.ExistingData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.subbaiahmultibrandauto.R;
import com.example.subbaiahmultibrandauto.databinding.ActivityExistingVehicleDataListBinding;
import com.example.subbaiahmultibrandauto.entities.Data;

import java.util.List;

public class ExistingVehicleDataList extends AppCompatActivity {
    private ActivityExistingVehicleDataListBinding activityExistingVehicleDataListBinding;
    private  List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityExistingVehicleDataListBinding = DataBindingUtil.setContentView(this, R.layout.activity_existing_vehicle_data_list);

         dataList = (List<Data>) getIntent().getSerializableExtra("dataList");




    }
}