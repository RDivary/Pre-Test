package com.divary.pretesthomecredit.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseModel {

    @Column(updatable = false)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
