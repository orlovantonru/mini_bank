package com.minibank.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount")
    private Long id;
    @Column(name = "numaccount")
    private String accountNumber;
    @Column(name = "dateaccount")
    private Date accountDate;
    @Column(name = "iduser")
    private Long userId;
}
