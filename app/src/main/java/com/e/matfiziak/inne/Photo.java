package com.e.matfiziak.inne;

public class Photo {
    private String id;
    public String getId(){
        return this.id;
    }
    private String imageURL;
    public String getImageURL(){
        return this.imageURL;
    }
    private String idWiad;
    public String getIdWiad(){
        return this.idWiad;
    }
    String sender;
    public String getSender(){
        return this.sender;
    }
    public Photo(String id, String imageURL, String idWiad){
        this.id = id;
        this.imageURL = imageURL;
        this.idWiad = idWiad;
    }
    public Photo(){}
}
