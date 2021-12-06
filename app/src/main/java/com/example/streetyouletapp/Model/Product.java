package com.example.streetyouletapp.Model;

import java.util.ArrayList;

public class Product {
    private int id;
    private String status;
    private String link;
    private ArrayList<String> size;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<String> getSize() {
        return size;
    }

    public void setSize(ArrayList<String> size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", link='" + link + '\'' +
                ", size=" + size.toString() +
                '}';
    }
}
