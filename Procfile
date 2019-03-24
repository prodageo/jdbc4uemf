web: sh target/bin/webapp
release: ./mvnw flyway:baseline -Dflyway.initSql="DROP TABLE IF EXISTS flyway_schema_history" && ./mvnw flyway:migrate -Dflyway.initSql="" 
