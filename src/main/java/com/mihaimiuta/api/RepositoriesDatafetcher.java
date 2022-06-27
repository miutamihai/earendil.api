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
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var runs = new RunsDatafetcher().get(id);
                result.add(new Repository(id, name, runs));
            }

            statement.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
