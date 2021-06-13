package com.divary.pretesthomecredit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User extends BaseModel {

    @Id
    @GeneratedValue(generator = "user_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private long balance;

    @Column(updatable = false, length = 8, unique = true, nullable = false)
    private String accountNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"user"})
    private List<LogTransfer> logTransfers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<LogTransfer> getLogTransfers() {
        return logTransfers;
    }

    public void setLogTransfers(List<LogTransfer> logTransfers) {
        this.logTransfers = logTransfers;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", logTransfers=" + logTransfers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return balance == user.balance && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(accountNumber, user.accountNumber) && Objects.equals(logTransfers, user.logTransfers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, balance, accountNumber, logTransfers);
    }
}
