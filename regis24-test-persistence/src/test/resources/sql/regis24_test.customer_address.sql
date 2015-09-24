-- Table: regis24_test.customer_address

-- DROP TABLE regis24_test.customer_address;

CREATE TABLE regis24_test.customer_address
(
  id numeric NOT NULL,
  address character varying(255),
  date date NOT NULL,
  customer_id numeric,
  CONSTRAINT customer_address_pkey PRIMARY KEY (id),
  CONSTRAINT fk41b20493dee01d29 FOREIGN KEY (customer_id)
      REFERENCES regis24_test.customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT customer_address_date_key UNIQUE (date)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE regis24_test.customer_address
  OWNER TO postgres;
