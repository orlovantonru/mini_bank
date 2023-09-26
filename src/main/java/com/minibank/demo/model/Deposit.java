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
/*@NamedQueries({@NamedQuery(name = "deposits.FIND_OPENED_ALL", query = "SELECT A.NUMACCOUNT, A.DATEACCOUNT, D.dateopen,  D.percent,\n" +
        "       (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) oborot_debit,\n" +
        "       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) oborot_credit,\n" +
        "       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) - (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) saldo\n" +
        "FROM public.accounts A, public.deposits D\n" +
        "where A.idaccount = D.idaccount\n" +
        "  and D.dateclose is null"),
        @NamedQuery(name = "deposits.FIND_OPENED_BY_USERID", query = "SELECT A.NUMACCOUNT, A.DATEACCOUNT, D.dateopen,  D.percent,\n" +
                "       (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) oborot_debit,\n" +
                "       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) oborot_credit,\n" +
                "       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) - (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) saldo\n" +
                "FROM public.accounts A, public.deposits D\n" +
                "where A.idaccount = D.idaccount\n" +
                "  and D.dateclose is null" +
                " and A.idaccount = :id")})*/
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
