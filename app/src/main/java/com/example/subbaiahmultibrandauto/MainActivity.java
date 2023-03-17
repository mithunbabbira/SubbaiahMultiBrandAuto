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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private FirebaseFirestore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mStore = FirebaseFirestore.getInstance();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);





//        mStore.collection("VehicleNo").document("KA12M1703").set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(MainActivity.this ,"success", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this ,"EXCEPTION", Toast.LENGTH_SHORT).show();
//            }
//        });



        activityMainBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVehicleExistence(activityMainBinding.getVehicleNo.getText());
            }
        });
    }
    private void checkVehicleExistence(Editable text) {

        CollectionReference collectionReference = mStore.collection("VehicleNo").document(String.valueOf(text)).collection("data");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                if (task.isSuccessful()) {
//                            QuerySnapshot document = task.getResult();
//                                            document.toObjects()
//
//                                Data d = document.toObject(Data.class);
//                                Toast.makeText(MainActivity.this, "DocumentSnapshot data: " + document.getData(), Toast.LENGTH_SHORT).show();
//                                Log.d("te", "DocumentSnapshot data: " + document.getData());
//
//                                Toast.makeText(MainActivity.this, "DocumentSnapshot data: " + d.getUpcomingRepair(), Toast.LENGTH_SHORT).show();
//                            }}
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Data> dataList = task.getResult().toObjects(Data.class);
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

        });


//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                if(task.isSuccessful()){
//                    DocumentSnapshot document = task.getResult();
//                    if(document.exists()){
//
//
//                    }else{
////                        Data d = (Data) document.toObjects(Data.class);
//                        Data d = document.toObject(Data.class);
//                        Intent myIntent = new Intent(MainActivity.this, AddNewVehicle.class);
//                        myIntent.putExtra("newVehicleNumber",String.valueOf(text));
//                        startActivity(myIntent);
//
//                    }
//
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });



    }
}