package com.example.feedlyapp;

public class NewsItem {
    private String title;
    private String category;
    private String site_name;
    private int popularity_count;

    NewsItem(String title, String category, String site_name, int popularity_count){
        this.title=title;
        this.category=category;
        this.site_name=site_name;
        this.popularity_count=popularity_count;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSite_name() {
        return site_name;
    }

    public int getPopularity_count() {
        return popularity_count;
    }
}
