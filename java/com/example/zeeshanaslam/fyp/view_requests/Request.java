package com.example.zeeshanaslam.fyp.view_requests;

import java.util.ArrayList;

public class Request {
    public Request(String uid,String notes,String days, ArrayList<String> itm, String mainName,int status,String price ) {
        Days = days;
        this.uid=uid;
        this.status = status;
        this.mainName = mainName;
        this.itm = itm;
        this.price=price;
        this.notes=notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String price;
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;
    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    String Days;

    public Request()
    {

    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    int status;


    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    String mainName;

    public ArrayList<String> getItm() {
        return itm;
    }

    public void setItm(ArrayList<String> itm) {
        this.itm = itm;
    }

    private ArrayList<String> itm;








}
