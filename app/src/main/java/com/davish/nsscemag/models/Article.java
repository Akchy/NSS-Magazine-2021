package com.davish.nsscemag.models;

public class Article {


    String id;
    String name;
    String title;
    String desc;

    public Article(String id, String name, String desc,
                   String title) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    public String getTitle() {
        return title;
    }


}




