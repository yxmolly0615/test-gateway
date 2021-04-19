package com.molly.entity;

public class User extends Entity<Integer>{
    private String location;
    private String phoneNo;

    @Override
    public String toString() {
        return "User{" +
                "location='" + location + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User(Integer id, String name, String location, String phoneNo) {
        super(id, name);
        this.location = location;
        this.phoneNo = phoneNo;
    }
}
