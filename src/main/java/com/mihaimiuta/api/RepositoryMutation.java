package com.mihaimiuta.api;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;

import java.sql.SQLException;

@DgsComponent
public class RepositoryMutation {
    @DgsMutation
    public Repository addRepository(@InputArgument String name, @InputArgument String token) {
        try {
            Repository newValue = null;

            var connection = DbDriver.getInstance().connection;
            var insertQuery = "insert into repositories(name, token) values(?, ?)";
            var insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, name);
            insertStatement.setString(2, token);
            insertStatement.execute();
            insertStatement.close();
            var selectQuery = "select * from repositories where name = ?";
            var selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            var resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                newValue = new Repository(resultSet.getString("id"), resultSet.getString("name"));
            }

            selectStatement.close();

            return newValue;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
