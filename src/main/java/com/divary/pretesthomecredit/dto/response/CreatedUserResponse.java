package com.divary.pretesthomecredit.dto.response;

public class CreatedUserResponse {

    private String id;

    public CreatedUserResponse build() {
        return this;
    }

    public String getId() {
        return id;
    }

    public CreatedUserResponse setId(String id) {
        this.id = id;
        return this;
    }
}
