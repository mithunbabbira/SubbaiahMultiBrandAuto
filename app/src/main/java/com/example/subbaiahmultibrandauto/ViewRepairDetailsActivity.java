package com.example.subbaiahmultibrandauto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.subbaiahmultibrandauto.adapter.IndividualRepairAdapter;
import com.example.subbaiahmultibrandauto.databinding.ActivityViewRepairDetailsBinding;
import com.example.subbaiahmultibrandauto.entities.Data;

public class ViewRepairDetailsActivity extends AppCompatActivity {

    private Data data ;
    private ActivityViewRepairDetailsBinding viewRepairDetailsBinding;
    private IndividualRepairAdapter individualRepairAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewRepairDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_view_repair_details);
        data = (Data) getIntent().getSerializableExtra("data");
        initialisation();
    }

    @SuppressLint("SetTextI18n")
    private void initialisation() {
        viewRepairDetailsBinding.heading.setText("Repair done on = "+data.getDate());
        viewRepairDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        individualRepairAdapter = new IndividualRepairAdapter(this, data.getCurrentRepairList(),null);
        viewRepairDetailsBinding.recyclerView.setAdapter(individualRepairAdapter);
        viewRepairDetailsBinding.pendingHeading.setText("Pending repair on = "+data.getDate());
        viewRepairDetailsBinding.pendingRepair.setText(data.getPendingRepair());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}