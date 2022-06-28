package com.example.onlineshopping;

class class_orderDetails {
    public int OrdID;
    public int ProID;
    public int Quantity;
    public double TotalPrice;
    public String ProName;
    public double Price;

    public class_orderDetails(int ordid, int productid, int quantity,double totalprice, String productname, double price) {
        this.OrdID = ordid;
        this.ProID = productid;
        this.Quantity = quantity;
        this.TotalPrice = totalprice;
        this.Price = price;
        this.ProName = productname;
    }

    public int getOrdID() {
        return OrdID;
    }

    public void setOrdID(int ordID) {
        OrdID = ordID;
    }

    public int getProID() {
        return ProID;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}