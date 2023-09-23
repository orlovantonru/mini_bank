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
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaction")
    private Long id;
    @Column(name = "idaccountcredit")
    private Long idAccountCredit;
    @Column(name = "idaccountdebit")
    private Long idAccountDebit;
    @Column(name = "dateopen")
    private Date openDate;
    @Column(name = "sum")
    private Float sum;
}
