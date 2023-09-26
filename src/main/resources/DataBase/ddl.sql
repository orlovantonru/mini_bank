-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id_user int4 NOT NULL,
                              "name" varchar NULL,
                              last_name varchar NULL,
                              surname varchar NULL,
                              username varchar NULL,
                              email varchar NULL,
                              "password" varchar NULL,
                              CONSTRAINT users_pk PRIMARY KEY (id_user)
);
CREATE INDEX users_id_user_idx ON public.users USING btree (id_user);

-- public.accounts definition

-- Drop table

-- DROP TABLE public.accounts;

CREATE TABLE public.accounts (
                                 idaccount int4 NOT NULL,
                                 numaccount varchar NOT NULL,
                                 dateaccount date NULL,
                                 iduser int4 NULL,
                                 CONSTRAINT accounts_pk PRIMARY KEY (idaccount)
);
CREATE INDEX accounts_idaccount_idx ON public.accounts USING btree (idaccount);


-- public.accounts foreign keys

ALTER TABLE public.accounts ADD CONSTRAINT accounts_fk FOREIGN KEY (iduser) REFERENCES public.users(id_user);


INSERT INTO public.users (id_user,"name",last_name,surname,username,email,"password")
VALUES
(1,'Иван','Иванов','Петрович','iw','iw@mail.ru','234'),
(2,'Сергей','Иванов','Петрович','as','as@mail.ru','567');

insert into public.accounts (idaccount, numaccount, dateaccount, iduser)
values (1,'40817810000000000012', '2023-09-09', 1);

insert into public.accounts (idaccount, numaccount, dateaccount, iduser)
values (2,'40817810000000000013', '2023-09-09', 1);

insert into public.accounts (idaccount, numaccount, dateaccount, iduser)
values (3,'20202810000000000013', '2023-09-09', 1);


CREATE TABLE public.deposits (
                             iddeposit int4 NOT NULL,
                              idaccount int4 NOT NULL,
                             dateopen date NULL,
                             dateclose date NULL,
                             percent numeric NULL,
                              CONSTRAINT iddeposit_pk PRIMARY KEY (iddeposit)
);

insert into public.deposits (iddeposit, dateopen, idaccount, percent)
values (1, '2023-09-09', 1, 10.2);

CREATE TABLE public.transactions (
                                idtransaction int4 NOT NULL,
                                 idaccountcredit int4 NOT NULL,
                                idaccountdebit int4 NOT NULL,
                                 dateopen date NULL,
                                 sum numeric NULL,
                                 CONSTRAINT idtransaction_pk PRIMARY KEY (idtransaction)
);

insert into  public.transactions (idtransaction, idaccountcredit, idaccountdebit, dateopen, sum)
values (1,1,3, '2023-09-09', 10000);
insert into  public.transactions (idtransaction, idaccountcredit, idaccountdebit, dateopen, sum)
values (2,1,3, '2023-09-09', 20000);

insert into  public.transactions (idtransaction, idaccountcredit, idaccountdebit, dateopen, sum)
values (3,3,1, '2023-09-09', 25000);

COMMIT;
select * from transactions;

SELECT A.NUMACCOUNT, A.DATEACCOUNT, D.dateopen,  D.percent, Sum(TC.sum), Sum(TD.sum) as Saldo
FROM public.accounts A, public.deposits D, public.transactions TC, public.transactions TD
                                   where A.idaccount = D.idaccount
                                       and D.dateclose is null
                                     and A.idaccount = TC.idaccountcredit
                                    and A.idaccount = TD.idaccountdebit
group by A.NUMACCOUNT, A.DATEACCOUNT, D.dateopen,  D.percent;

SELECT A.NUMACCOUNT, A.DATEACCOUNT, D.dateopen,  D.percent,
       (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) oborot_debit,
       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) oborot_credit,
       (select sum(TC.sum) from transactions TC where TC.idaccountcredit = A.idaccount) - (select sum(TD.sum) from transactions TD  where TD.idaccountdebit = A.idaccount ) saldo
FROM public.accounts A, public.deposits D
where A.idaccount = D.idaccount
  and D.dateclose is null
