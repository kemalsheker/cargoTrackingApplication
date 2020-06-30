package com.example.drn20;

public class User {

    private String isim;
    private  String email;
    private String telephone;
    private boolean notification;


    public User(){
    }

    public User(String isim, String email , String telephone , boolean notification) {
        this.isim = isim;
        this.email = email;
        this.telephone = telephone;
        this.notification = notification;
    }

    public String getEmail() {
        return email;
    }

    public String getIsim() {
        return isim;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone(){ return telephone;  }

    public  void setTelephone(String telephone){ this.telephone = telephone;}
}
