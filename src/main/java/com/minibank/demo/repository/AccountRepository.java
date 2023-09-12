package com.minibank.demo.repository;

import com.minibank.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long>{
    Optional<Account> findByUserId(Long userId);
}
