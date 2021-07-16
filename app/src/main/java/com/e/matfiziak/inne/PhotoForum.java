package com.e.matfiziak.inne;
public class PhotoForum{
    String id;
    public String getId(){
        return this.id;
    }
    String imageURL;
    public String getImageURL(){
        return imageURL;
    }
    String idPost;
    public String getIdPost(){
        return idPost;
    }
    String sender;
    public String getSender() {
        return sender;
    }
    public PhotoForum(String id, String imageURL, String idPost, String sender) {
        this.id = id;
        this.imageURL = imageURL;
        this.idPost = idPost;
        this.sender = sender;
    }
    public PhotoForum(){}
}