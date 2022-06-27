package com.mihaimiuta.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApiApplication {
    private static void createTables() {
        try {
            var connection = DbDriver.getInstance().connection;
            var statement = connection.createStatement();
            statement.executeQuery("""
                create table if not exists repositories(
                    id serial primary key,
                    name varchar(255) not null unique ,
                    token varchar(255) not null
                );

                create table if not exists runs(
                    id serial primary key ,
                    creation_date timestamp default now(),
                    status varchar(255),
                    initiator varchar(255),
                    repository_id integer not null,
                    constraint repository_id_fk foreign key (repository_id) references repositories (id)
                );
            """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        createTables();

        SpringApplication.run(ApiApplication.class, args);
    }

}
