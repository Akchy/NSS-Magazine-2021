package com.davish.nsscemag.models;


import java.util.List;


public class BigData {

    String category;
    List<Article> ArticleList;

    public BigData(String category, List<Article> ArticleList) {
        this.category = category;
        this.ArticleList = ArticleList;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Article> getArticleList() {
        return ArticleList;
    }

    public String getCategory() {
        return category;
    }

    public void setArticleList(List<Article> articleList) {
        this.ArticleList = articleList;
    }
}
