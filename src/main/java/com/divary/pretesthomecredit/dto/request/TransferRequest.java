package com.divary.pretesthomecredit.dto.request;

public class TransferRequest {

    private String accountNumberReceive;

    private long amount;

    public String getAccountNumberReceive() {
        return accountNumberReceive;
    }

    public void setAccountNumberReceive(String accountNumberReceive) {
        this.accountNumberReceive = accountNumberReceive;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
