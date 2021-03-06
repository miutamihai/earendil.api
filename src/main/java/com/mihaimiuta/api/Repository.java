package com.mihaimiuta.api;

import java.util.List;

public class Repository {
    private final String id;

    private final String name;

    private final String token;

    private final List<Run> runs;

    public Repository(String _id, String _name, String _token, List<Run> _runs) {
        this.id = _id;
        this.name = _name;
        this.token = _token;
        this.runs = _runs;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public List<Run> getRuns() {
        return runs;
    }
}
