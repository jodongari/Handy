DROP TABLE QR;

CREATE TABLE QR (
   hash VARCHAR(64)  PRIMARY KEY,
   store_seq bigint,
   table_name VARCHAR(10),
   CREATED_AT timestamp,
   UPDATED_AT timestamp
);
