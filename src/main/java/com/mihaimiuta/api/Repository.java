package com.mihaimiuta.api;

public class Repository {
    private final String id;

    private final String name;

    public Repository(String _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
