package com.molly.entity;


public class Order extends Entity<Integer>{

    private int userid;
    private int driverid;
    private String journey;
    private double amount;

    public Order(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Order{" +
                "userid=" + userid +
                ", driverid=" + driverid +
                ", journey='" + journey + '\'' +
                ", amount=" + amount +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order(Integer id, String name, int userid, int driverid, String journey, double amount) {
        super(id, name);
        this.userid = userid;
        this.driverid = driverid;
        this.journey = journey;
        this.amount = amount;
    }
}
