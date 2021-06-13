package com.divary.pretesthomecredit.dto.request;

public class CreateUSerRequest {

    private String username;

    private long balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
