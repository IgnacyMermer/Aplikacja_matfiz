package com.e.matfiziak.inne;

import java.util.List;

public class Odpowiedzi {
    String dodatkoweInf;
    public String getDodatkoweInf(){
        return this.dodatkoweInf;
    }
    String id;
    public String getId(){
        return this.id;
    }
    String sender;
    public String getSender(){
        return this.sender;
    }
    String odpowiedz;
    public String getOdpowiedz(){
        return this.odpowiedz;
    }
    String username;
    public String getUsername(){
        return this.username;
    }
    String imageURL;
    public String getImageURL(){
        return this.imageURL;
    }
    String iloscLike;
    public String getIloscLike(){
        return this.iloscLike;
    }
    String idPost;
    public String getIdPost(){
        return this.idPost;
    }
    List<String> listaLajkujacych;
    public List<String> getListaLajkujacych() {
        return this.listaLajkujacych;
    }
    int iloscZdjec;
    public int getIloscZdjec(){
        return this.iloscZdjec;
    }
    public Odpowiedzi(String dodatkoweInf, String id, String sender, String odpowiedz, String username, String imageURL, String iloscLike, String idPost,List<String> listaLajkujacych, int iloscZdjec){
        this.dodatkoweInf = dodatkoweInf;
        this.id = id;
        this.sender = sender;
        this.odpowiedz = odpowiedz;
        this.username = username;
        this.imageURL = imageURL;
        this.iloscLike = iloscLike;
        this.idPost = idPost;
        this.listaLajkujacych = listaLajkujacych;
        this.iloscZdjec = iloscZdjec;
    }
    public Odpowiedzi(){}
}