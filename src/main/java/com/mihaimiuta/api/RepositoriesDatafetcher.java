package com.mihaimiuta.api;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.mihaimiuta.api.database.Repositories;

import java.util.List;

@DgsComponent
public class RepositoriesDatafetcher {

    @DgsQuery
    public List<Repository> repositories() {
        return new Repositories().get();
    }
}
