package com.example.rkjc.news_app_2;

public class NewsItem {

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public NewsItem(){}

    public NewsItem(String author,String title,String description,String url,String urlToImage,String publishedAt){
        this.author=author;
        this.title=title;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
