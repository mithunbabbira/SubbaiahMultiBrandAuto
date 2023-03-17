package com.example.subbaiahmultibrandauto.ExistingData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.subbaiahmultibrandauto.AddNewVehicle;
import com.example.subbaiahmultibrandauto.MainActivity;
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

         setStartingData();





    }

    private void setStartingData() {

        activityExistingVehicleDataListBinding.vehicleNoTv.setText(dataList.get(0).getVehicleNo());
        activityExistingVehicleDataListBinding.vehicleModelTv.setText(dataList.get(0).getVehicleModel());
        activityExistingVehicleDataListBinding.phoneNoTv.setText(dataList.get(0).getPhoneNo());
        activityExistingVehicleDataListBinding.addMoreBtn.setOnClickListener(view -> {
            Intent myIntent = new Intent(ExistingVehicleDataList.this, AddNewVehicle.class);
            myIntent.putExtra("newVehicleNumber",dataList.get(0).getVehicleNo());
            myIntent.putExtra("vehicleModel",dataList.get(0).getVehicleModel());
            startActivity(myIntent);

        });




        activityExistingVehicleDataListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ExistingDataAdapter adapter = new ExistingDataAdapter(this ,dataList );
        activityExistingVehicleDataListBinding.recyclerView.setAdapter(adapter);



    }
}