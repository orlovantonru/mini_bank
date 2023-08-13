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


INSERT INTO public.users (id_user,"name",last_name,surname,username,email,"password") VALUES
                                                                                          (1,'Иван','Иванов','Петрович','iw','iw@mail.ru','234'),
                                                                                          (2,'Сергей','Иванов','Петрович','as','as@mail.ru','567');