package com.minibank.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
