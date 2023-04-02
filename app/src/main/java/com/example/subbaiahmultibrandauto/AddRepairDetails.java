package com.example.subbaiahmultibrandauto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.subbaiahmultibrandauto.ExistingData.ExistingVehicleDataList;
import com.example.subbaiahmultibrandauto.adapter.IndividualRepairAdapter;
import com.example.subbaiahmultibrandauto.databinding.ActivityAddRepairDetailsBinding;
import com.example.subbaiahmultibrandauto.entities.Data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddRepairDetails extends AppCompatActivity {
    private IndividualRepairAdapter individualRepairAdapter ;
    private List<String> currentRepairList = new ArrayList<>();
    private ActivityAddRepairDetailsBinding activityAddRepairDetailsBinding;
    private Listener listener ;
    private Data data ;
    private Gson gson = new Gson();
    private Dialog dialog ;
    private FirebaseFirestore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddRepairDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_repair_details);
        mStore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        data = (Data) getIntent().getSerializableExtra("data");
        //yes

        listener = new Listener() {
            @Override
            public void deletePosition(int position) {
                currentRepairList.remove(position);
                individualRepairAdapter.updateItem(currentRepairList);
            }
        };
        initialisation();
        activityAddRepairDetailsBinding.repairDataSubBtn.setOnClickListener(view -> {
            if(!activityAddRepairDetailsBinding.repairData.getText().toString().isEmpty()){
                addData(activityAddRepairDetailsBinding.repairData.getText().toString());
                activityAddRepairDetailsBinding.repairData.setText("");
            }
        });

        activityAddRepairDetailsBinding.upcomingRepairDataSubBtn.setOnClickListener(view -> {
            if(!activityAddRepairDetailsBinding.upcomingRepair.getText().toString().isEmpty()){
                data.setPendingRepair(activityAddRepairDetailsBinding.upcomingRepair.getText().toString());
            }
        });

        activityAddRepairDetailsBinding.submit.setOnClickListener(view -> {
            showConfirmDialog();
        });

        activityAddRepairDetailsBinding.resetPending.setOnClickListener(view -> {
            activityAddRepairDetailsBinding.upcomingRepair.setText("");
        });
    }

    private void showConfirmDialog() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.submit_comfirm_dialog_box);

        Button cancel = dialog.findViewById(R.id.cancelBtn);
        Button confirm = dialog.findViewById(R.id.confirmBtn);

        cancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        confirm.setOnClickListener(view -> {


            if(currentRepairList!=null&& !currentRepairList.isEmpty()){
                data.setCurrentRepairList(currentRepairList);
                activityAddRepairDetailsBinding.rlLoader.setVisibility(View.VISIBLE);
                addDataToFireStore();
                dialog.dismiss();

            }else{

                Toast.makeText(AddRepairDetails.this ,"Repair list can not be empty", Toast.LENGTH_SHORT).show();

            }

        });

        dialog.show();
    }

    private void addDataToFireStore() {

        String docID = String.valueOf(System.currentTimeMillis());
        mStore.collection("VehicleNo").document(data.getVehicleNo()).collection("data").document(docID).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(AddRepairDetails.this ,"success", Toast.LENGTH_SHORT).show();
                activityAddRepairDetailsBinding.rlLoader.setVisibility(View.GONE);
                gotoHomePage();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddRepairDetails.this ,"fail", Toast.LENGTH_SHORT).show();
                activityAddRepairDetailsBinding.rlLoader.setVisibility(View.GONE);
                gotoHomePage();

            }
        });
    }

    private void gotoHomePage() {
        Intent myIntent = new Intent(AddRepairDetails.this, MainActivity.class);
        startActivity(myIntent);
        finish();

    }

    private void initialisation() {
        activityAddRepairDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        individualRepairAdapter = new IndividualRepairAdapter(this, currentRepairList,listener);
        activityAddRepairDetailsBinding.recyclerView.setAdapter(individualRepairAdapter);
    }

    private void addData(String text) {
        currentRepairList.add(text);
        individualRepairAdapter.updateItem(currentRepairList);
    }


}