package com.mihaimiuta.api.webhook;

public class Head {
    private final String ref;
    private final String sha;

    public Head(String _ref, String _sha) {
        this.ref = _ref;
        this.sha = _sha;
    }

    public String getRef() {
        return ref;
    }

    public String getSha() {
        return sha;
    }
}
