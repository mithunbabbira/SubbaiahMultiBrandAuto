package com.example.subbaiahmultibrandauto.entities;

import java.io.Serializable;
import java.util.List;

public class Data  implements Serializable {
    private String vehicleNo;
    private String phoneNo;
    private String repairDetails;
    private String vehicleModel;
    private int mileage;
    private String date;
    private String upcomingRepair;
    private List<String> currentRepairList;

    public String getUpcomingRepair() {
        return upcomingRepair;
    }

    public void setUpcomingRepair(String upcomingRepair) {
        this.upcomingRepair = upcomingRepair;
    }



    public List<String> getCurrentRepairList() {
        return currentRepairList;
    }

    public void setCurrentRepairList(List<String> currentRepairList) {
        this.currentRepairList = currentRepairList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRepairDetails() {
        return repairDetails;
    }

    public void setRepairDetails(String repairDetails) {
        this.repairDetails = repairDetails;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }




}
