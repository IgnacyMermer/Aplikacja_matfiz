package com.e.matfiziak.inne;

public class Post {
    String dodatkoweInf;
    public String getDodatkoweInf(){
        return this.dodatkoweInf;
    }
    String dzial;
    public String getDzial(){
        return this.dzial;
    }
    String id;
    public String getId(){
        return this.id;
    }
    String klasa;
    public String getKlasa(){
        return this.klasa;
    }
    String numerZad;
    public String getNumerZad(){
        return this.numerZad;
    }
    String sender;
    public String getSender(){
        return this.sender;
    }
    String tresc;
    public String getTresc(){
        return this.tresc;
    }
    String tytul;
    public String getTytul(){
        return this.tytul;
    }
    String username;
    public String getUsername(){
        return this.username;
    }
    String imageURL;
    public String getImageURL(){
        return this.imageURL;
    }
    String liczbaKomentarzy;
    public String getLiczbaKomentarzy(){
        return this.liczbaKomentarzy;
    }
    public Post(String dodatkoweInf, String dzial, String id, String klasa, String numerZad, String sender, String tresc, String tytul, String username, String liczbaKomentarzy){
        this.dodatkoweInf = dodatkoweInf;
        this.dzial = dzial;
        this.id = id;
        this.klasa = klasa;
        this.numerZad = numerZad;
        this.sender = sender;
        this.tresc = tresc;
        this.tytul = tytul;
        this.username = username;
        this.liczbaKomentarzy = liczbaKomentarzy;
    }
    public Post(){}
}
