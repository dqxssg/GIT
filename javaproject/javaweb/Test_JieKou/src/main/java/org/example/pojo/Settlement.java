package org.example.pojo;

public class Settlement {
    int id;
    int waybillnumber;
    String productname;
    int carnumber;
    String deliverydate;
    String shipper;
    int weight;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaybillnumber() {
        return waybillnumber;
    }

    public void setWaybillnumber(int waybillnumber) {
        this.waybillnumber = waybillnumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(int carnumber) {
        this.carnumber = carnumber;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
