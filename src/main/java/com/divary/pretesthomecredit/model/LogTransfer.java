package com.divary.pretesthomecredit.model;

import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_transfer")
public class LogTransfer {

    @Id
    @GeneratedValue(generator = "user_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    private LogTransferEnum type;

    private long amount;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"logTransfers"})
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogTransferEnum getType() {
        return type;
    }

    public void setType(LogTransferEnum type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LogTransfer{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
