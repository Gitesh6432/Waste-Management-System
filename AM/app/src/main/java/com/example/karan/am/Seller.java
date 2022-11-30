package com.example.karan.am;

public class Seller {
    private String wastetype,quantity,cost,user;

    public Seller(String wastetype,String user, String quantity, String cost) {
        this.wastetype = wastetype;
        this.quantity = quantity;
        this.cost = cost;
        this.user=user;
    }

    public String getUser() {        return user;    }

    public void setUser(String user) {        this.user = user;    }

    public String getWastetype() {
        return wastetype;
    }

    public void setWastetype(String wastetype) {
        this.wastetype = wastetype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
