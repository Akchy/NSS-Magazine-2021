package com.davish.nsscemag.models;

public class Article {

    String author;
    String dept;
    String sem;
    String title;
    String desc;

    public Article() {
    }

    public Article(String author, String dept, String sem, String title, String desc) {
        this.author = author;
        this.dept = dept;
        this.sem = sem;
        this.title = title;
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}




