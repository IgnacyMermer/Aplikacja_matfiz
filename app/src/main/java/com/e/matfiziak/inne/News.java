package com.e.matfiziak.inne;
public class News {
    String id;
    public String getId() {
        return id;
    }
    String tekst;
    public String getTekst() {
        return tekst;
    }
    String link;
    public String getLink() {
        return link;
    }
    String zrodlo;
    public String getZrodlo() {
        return zrodlo;
    }
    public News(String id, String tekst, String link){
        this.id = id;
        this.tekst= tekst;
        this.link = link;
    }
    public News(){}
}