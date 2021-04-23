package com.davish.nsscemag.models;


import java.util.List;


public class BigData {

    String type;
    List<Article> list;

    public BigData(String type, List<Article> list) {
        this.type = type;
        this.list = list;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Article> getList() {
        return list;
    }

    public String getType() {
        return type;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }
}
