-- Table: regis24_test.customer

-- DROP TABLE regis24_test.customer;

CREATE TABLE regis24_test.customer
(
  id numeric NOT NULL,
  name character varying(255),
  CONSTRAINT customer_pkey PRIMARY KEY ()
)
WITH (
  OIDS=FALSE
);
ALTER TABLE regis24_test.customer
  OWNER TO postgres;
