package com.example.subbaiahmultibrandauto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.subbaiahmultibrandauto.databinding.ActivityExistingVehicleDataListBinding;

public class ExistingVehicleDataList extends AppCompatActivity {
    ActivityExistingVehicleDataListBinding activityExistingVehicleDataListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityExistingVehicleDataListBinding = DataBindingUtil.setContentView(this,R.layout.activity_existing_vehicle_data_list);
    }
}