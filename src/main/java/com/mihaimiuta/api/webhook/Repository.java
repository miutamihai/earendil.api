package com.mihaimiuta.api.webhook;

public class Repository {
    private final String name;

    private final String fullName;

    private final String cloneUrl;

    public Repository(String _name, String _fullName, String _cloneUrl) {
        this.name = _name;
        this.fullName = _fullName;
        this.cloneUrl = _cloneUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }
}
