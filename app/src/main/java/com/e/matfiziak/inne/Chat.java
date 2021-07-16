package com.e.matfiziak.inne;

import java.util.List;

public class Chat {
    public String getMessage() {
        return message;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    private String sender;
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    private String receiver;
    public String getSender() {
        return sender;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    int odpowiedz;
    public int getOdpowiedz(){
        return this.odpowiedz;
    }
    public void setOdpowiedz(int odpowiedz){
        this.odpowiedz = odpowiedz;
    }
    int position;
    public int getPosition(){
        return this.position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    String data;
    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }
    int iloscZdjec;
    public int getIloscZdjec(){
        return this.iloscZdjec;
    }
    int like;
    public int getLike(){
        return this.like;
    }
    public void setLike(int like){
        this.like = like;
    }
    private String message;
    private String id;
    public String getId() {
        return id;
    }
    private String zdjecie;
    public String getZdjecie(){
        return this.zdjecie;
    }
    public List<String> lista;
    public List<String> getLista(){
        return this.lista;
    }
    public Chat(String id,String sender, String receiver, String message, int odpowiedz, int position, String data, int like, List<String> lista){
        this.sender = sender;
        this.message = message;
        this.receiver = receiver;
        this.odpowiedz = odpowiedz;
        this.position = position;
        this.data = data;
        this.like = like;
        this.id = id;
        this.lista = lista;
    }
    public Chat(String id,String sender, String receiver, String message, int odpowiedz, int position, String data, int like){
        this.sender = sender;
        this.message = message;
        this.receiver = receiver;
        this.odpowiedz = odpowiedz;
        this.position = position;
        this.data = data;
        this.like = like;
        this.id = id;
    }
    public Chat(){}
}