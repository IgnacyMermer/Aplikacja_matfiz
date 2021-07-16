package com.e.matfiziak.inne;
public class User {
    String id;
    String username, imageURL, status, search, klasa;
    public User(String id, String username, String imageUrl, String status, String search, String klasa) {
        this.id = id;
        this.username = username;
        this.imageURL = imageUrl;
        this.status = status;
        this.search = search;
        this.klasa = klasa;
    }
    public User(){}
    public String getImageUrl() {
        return imageURL;
    }
    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getSearch(){
        return search;
    }
    public void setSearch(String search){
        this.search = search;
    }
    public String getKlasa(){
        return this.klasa;
    }
}