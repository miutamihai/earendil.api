package com.mihaimiuta.api.webhook;

public class PullRequest {
    private final String url;

    private final String title;

    private final Head head;

    private final User user;

    PullRequest(String _url, String _title, Head _head, User _user) {
        this.url = _url;
        this.title = _title;
        this.head = _head;
        this.user = _user;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Head getHead() {
        return head;
    }

    public User getUser() {
        return user;
    }
}
