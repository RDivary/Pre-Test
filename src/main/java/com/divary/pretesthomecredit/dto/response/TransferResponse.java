package com.divary.pretesthomecredit.dto.response;

public class TransferResponse {

    private String from;

    private String to;

    private long amount;

    public String getFrom() {
        return from;
    }

    public TransferResponse setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TransferResponse setTo(String to) {
        this.to = to;
        return this;
    }

    public long getAmount() {
        return amount;
    }

    public TransferResponse setAmount(long amount) {
        this.amount = amount;
        return this;
    }
}
