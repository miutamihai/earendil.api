package com.mihaimiuta.api;

import java.sql.SQLException;
import java.util.List;

public class RunsDatafetcher {
    public List<Run> get(String repositoryId) {
        List<Run> result = new java.util.ArrayList<>(List.of());

        try {
            var connection = DbDriver.getInstance().connection;
            var query = "select * from runs where runs.repository_id = ?";
            var statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(repositoryId));
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var creationDate = resultSet.getDate("creation_date");
                var status = resultSet.getString("status");
                var initiator = resultSet.getString("initiator");
                result.add(new Run(id, creationDate, status, initiator));
            }

            statement.close();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
