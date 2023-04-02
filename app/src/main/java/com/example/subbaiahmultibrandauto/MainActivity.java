package com.example.subbaiahmultibrandauto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.example.subbaiahmultibrandauto.ExistingData.ExistingVehicleDataList;
import com.example.subbaiahmultibrandauto.databinding.ActivityMainBinding;
import com.example.subbaiahmultibrandauto.entities.Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private FirebaseFirestore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = FirebaseFirestore.getInstance();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        activityMainBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMainBinding.rlLoader.setVisibility(View.VISIBLE);
                checkVehicleExistence(activityMainBinding.getVehicleNo.getText());
            }
        });
    }
    private void checkVehicleExistence(Editable text) {
        CollectionReference collectionReference = mStore.collection("VehicleNo").document(String.valueOf(text)).collection("data");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    activityMainBinding.rlLoader.setVisibility(View.GONE);
                    List<Data> dataList = task.getResult().toObjects(Data.class);

                    Collections.reverse(dataList);

                    Intent myIntent;
                    if(dataList.size()!=0){

                        myIntent = new Intent(MainActivity.this, ExistingVehicleDataList.class);
                        myIntent.putExtra("dataList", (Serializable) dataList);
                        startActivity(myIntent);
                    }else{
                         myIntent = new Intent(MainActivity.this, AddNewVehicle.class);
                        myIntent.putExtra("newVehicleNumber",String.valueOf(text));
                    }
                    startActivity(myIntent);
                }
            }
        }).addOnFailureListener(e -> {
            activityMainBinding.rlLoader.setVisibility(View.GONE);
        });
    }
}