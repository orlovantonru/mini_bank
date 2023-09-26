package com.minibank.demo.repository;

import com.minibank.demo.model.Deposit;
import com.minibank.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
