package com.divary.pretesthomecredit.repository;

import com.divary.pretesthomecredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAccountNumberEquals(String accountNumber);
}
