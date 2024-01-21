FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD admin
ENV POSTGRES_DB postgres
COPY docker/db_user_creation.sql /docker-entrypoint-initdb.d/
COPY docker/create_tables.sql /docker-entrypoint-initdb.d/
COPY docker/populate_tables.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["docker-entrypoint.sh"]
EXPOSE 5432
CMD ["postgres"]
