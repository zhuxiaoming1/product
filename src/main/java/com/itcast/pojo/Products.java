package com.itcast.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaoming
 * @create 2019-04-17-15:12
 */
public class Products {

    private int pid;
    private String pname;
    private double  price;
    private String flag;
    private int category_id;

    public Products() {
    }

    public Products(int pid, String pname, double price, String flag, int category_id) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.flag = flag;
        this.category_id = category_id;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Products{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", flag='" + flag + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
