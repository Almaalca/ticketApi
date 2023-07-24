FROM postgres:12-alpine

# Los números en "init1" y "init2" es solo para forzar el orden en que se ejecutan las cosas
COPY ./init.sql /docker-entrypoint-initdb.d/init1.sql