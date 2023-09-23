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
@Table(name="deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddeposit")
    private Long id;
    @Column(name = "idaccount")
    private Long idAccount;
    @Column(name = "dateopen")
    private Date openDate;
    @Column(name = "dateclose")
    private Date closeDate;
    @Column(name = "percent")
    private Float percent;
}
