package com.molly.entity;


public class Driver extends Entity<Integer>{

    private String location;
    private String carNo;

    public Driver(Integer id, String name) {
        super(id, name);
    }

    public Driver(Integer id, String name, String location, String carNo) {
        super(id, name);
        this.location = location;
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "location='" + location + '\'' +
                ", carNo='" + carNo + '\'' +
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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}
