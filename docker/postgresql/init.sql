CREATE DATABASE double_partners;

\c double_partners

CREATE OR REPLACE FUNCTION json_intext(text) RETURNS json AS $$ SELECT json_in($1::cstring); $$ LANGUAGE SQL IMMUTABLE;

CREATE OR REPLACE FUNCTION json_invarchar(varchar) RETURNS json AS $$ SELECT json_in($1::cstring); $$ LANGUAGE SQL IMMUTABLE;

CREATE CAST (text AS json) WITH FUNCTION json_intext(text) AS IMPLICIT;

CREATE CAST (varchar AS json) WITH FUNCTION json_invarchar(varchar) AS IMPLICIT;

CREATE OR REPLACE FUNCTION jsonb_intext(text) RETURNS jsonb AS $$ SELECT jsonb_in($1::cstring); $$ LANGUAGE SQL IMMUTABLE;

CREATE OR REPLACE FUNCTION jsonb_invarchar(varchar) RETURNS jsonb AS $$ SELECT jsonb_in($1::cstring); $$ LANGUAGE SQL IMMUTABLE;

CREATE CAST (text AS jsonb) WITH FUNCTION jsonb_intext(text) AS IMPLICIT;

CREATE CAST (varchar AS jsonb) WITH FUNCTION jsonb_invarchar(varchar) AS IMPLICIT;

CREATE EXTENSION unaccent;

CREATE OR REPLACE FUNCTION unaccent(regdictionary, text) RETURNS text LANGUAGE c STABLE STRICT AS '$libdir/unaccent', $function$unaccent_dict$function$;

CREATE TABLE state (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE ticket (
  id  BIGSERIAL NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  created_date TIMESTAMP  NULL DEFAULT now(),
  update_date TIMESTAMP  NULL DEFAULT now(),
  state_id bigint NOT NULL,
  CONSTRAINT ticket_pkey PRIMARY KEY (id),
  CONSTRAINT ticket_status_fk FOREIGN KEY (state_id) REFERENCES state(id) ON DELETE CASCADE
);

INSERT INTO state ("name") VALUES('abierto');
INSERT INTO state ("name") VALUES('cerrado');

\q
