package com.example.msale.classes.Users;

import java.io.Serializable;

public class Users implements Serializable {
    private String username;
    private String phoneNumber;
    private String password;
    private long cash;
    private boolean bool;
    public Users() {
    }

    public Users(String username, String phoneNumber, String password, long cash, boolean bool) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.cash = cash;
        this.bool = bool;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }
}
