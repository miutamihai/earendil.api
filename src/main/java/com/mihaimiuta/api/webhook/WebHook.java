package com.mihaimiuta.api.webhook;

public class WebHook {
    private final String action;

    private final PullRequest pullRequest;

    public WebHook(String _action, PullRequest _pullRequest) {
        this.action = _action;
        this.pullRequest = _pullRequest;
    }

    public String getAction() {
        return action;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }
}
