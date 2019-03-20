package com.example.zeeshanaslam.fyp;

public class ProfileClass {

    String fullName;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    String email;
    String number;
    String loc;
    public ProfileClass()
    {

    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getLoc() {
        return loc;
    }

    public ProfileClass(String fullName, String email, String number, String loc) {
        this.fullName = fullName;
        this.email = email;
        this.number = number;
        this.loc = loc;
    }

}
