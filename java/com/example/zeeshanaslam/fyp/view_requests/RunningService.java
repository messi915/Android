package com.example.zeeshanaslam.fyp.view_requests;

public class RunningService {

    public RunningService(String employeeID, String workerID, String name, String price,String date) {
        this.employeeID = employeeID;
        this.userID = workerID;
        this.name = name;
        this.price = price;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public RunningService() {

    }
    String employeeID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getWorkerID() {
        return userID;
    }

    public void setWorkerID(String workerID) {
        this.userID = workerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String userID;
    String name;
    String price;
}
