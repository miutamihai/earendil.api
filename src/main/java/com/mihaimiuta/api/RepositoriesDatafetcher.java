package com.mihaimiuta.api;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.sql.SQLException;
import java.util.List;

@DgsComponent
public class RepositoriesDatafetcher {

    @DgsQuery
    public List<Repository> repositories() {
        List<Repository> result = new java.util.ArrayList<>(List.of());

        try {
            var connection = DbDriver.getInstance().connection;
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("select * from repositories");

            while(resultSet.next()) {
                result.add(new Repository(resultSet.getString("id"), resultSet.getString("name")));
            }
            statement.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
