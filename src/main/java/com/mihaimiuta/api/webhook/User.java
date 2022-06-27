package com.mihaimiuta.api.webhook;

public class User {
    private final String login;

    private final String url;

    public User(String _login, String _url) {
        this.login = _login;
        this.url = _url;
    }

    public String getLogin() {
        return login;
    }

    public String getUrl() {
        return url;
    }
}
