package com.example.subbaiahmultibrandauto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.subbaiahmultibrandauto.databinding.ActivityAddNewVehicleBinding;
import com.example.subbaiahmultibrandauto.entities.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddNewVehicle extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ActivityAddNewVehicleBinding activityAddNewVehicleBinding;
    private String newVehicleNo , vehicleModel;
    private String date;
    private  Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_vehicle);
        activityAddNewVehicleBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_vehicle);

        initialisation();

        Intent intent = getIntent();
        newVehicleNo = intent.getStringExtra("newVehicleNumber");
        vehicleModel = intent.getStringExtra("vehicleModel");

        activityAddNewVehicleBinding.vehicleMod.setText(vehicleModel!=null?vehicleModel:"");
        activityAddNewVehicleBinding.vehicleNum.setText(newVehicleNo);

        Data data = new Data();
        data.setVehicleNo(newVehicleNo);


        activityAddNewVehicleBinding.setDateBtn.setOnClickListener(view -> {
            showDatePickerDailog();
        });
        activityAddNewVehicleBinding.submitBtn.setOnClickListener(view -> {
            if (activityAddNewVehicleBinding.vehicleNum.getText().length() == 0) {
                activityAddNewVehicleBinding.vehicleNum.setError("please enter");
            } else if (activityAddNewVehicleBinding.vehicleMod.getText().length() == 0) {
                activityAddNewVehicleBinding.vehicleMod.setError("please enter");
            } else if (activityAddNewVehicleBinding.mileage.getText().length() == 0) {
                activityAddNewVehicleBinding.mileage.setError("please enter");
            } else if(activityAddNewVehicleBinding.dataSet.length()==0 ||activityAddNewVehicleBinding.dataSet.getText().equals("Please Set date")){
                activityAddNewVehicleBinding.dataSet.setText("Please Set date");
            }else{
                data.setVehicleNo(activityAddNewVehicleBinding.vehicleNum.getText().toString());
                data.setVehicleModel(activityAddNewVehicleBinding.vehicleMod.getText().toString());
                data.setMileage(Integer.parseInt(String.valueOf(activityAddNewVehicleBinding.mileage.getText())));
                data.setDate(date);
                startNewIntent(data);

            }

        });
    }

    private void initialisation() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        date = dateFormat.format(calendar.getTime());
        activityAddNewVehicleBinding.dataSet.setText(date);
    }

    private void startNewIntent(Data data) {
        Intent myIntent = new Intent(AddNewVehicle.this, AddRepairDetails.class);
        myIntent.putExtra("data", data);
        startActivity(myIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        gotoHomePage();
    }

    private void gotoHomePage() {
        Intent myIntent = new Intent(AddNewVehicle.this, MainActivity.class);
        startActivity(myIntent);
        finish();

    }

    private void showDatePickerDailog(){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Format the date as a string


        // Create a new DatePickerDialog with today's date as default
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, dayOfMonth);

        // Show the DatePickerDialog
        datePickerDialog.show();

    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        date = i2+"/"+i1+"/"+i;
        activityAddNewVehicleBinding.dataSet.setText(date);
    }
}