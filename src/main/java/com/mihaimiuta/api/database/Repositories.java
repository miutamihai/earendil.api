package com.mihaimiuta.api.database;

import com.mihaimiuta.api.DbDriver;
import com.mihaimiuta.api.Repository;
import com.mihaimiuta.api.RunsDatafetcher;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Repositories {
    public List<Repository> get() {
        List<Repository> result = new java.util.ArrayList<>(List.of());

        try {
            var connection = DbDriver.getInstance().connection;
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("select * from repositories");

            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var runs = new RunsDatafetcher().get(id);
                result.add(new Repository(id, name, "", runs));
            }

            statement.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // This should be a separate sql query
    public String getToken(String fullName) {
        List<Repository> result = new java.util.ArrayList<>(List.of());

        try {
            var connection = DbDriver.getInstance().connection;
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("select * from repositories");

            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var token = resultSet.getString("token");
                var runs = new RunsDatafetcher().get(id);
                result.add(new Repository(id, name, token, runs));
            }

            statement.close();

            return result.stream().filter(repository -> Objects.equals(repository.getName(), fullName)).findFirst().get().getToken();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
