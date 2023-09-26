package com.minibank.demo.repository;

import com.minibank.demo.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
 //   void OpenDeposit(Long userId, Date dateOpen, Float percent, Float Sum);
}


