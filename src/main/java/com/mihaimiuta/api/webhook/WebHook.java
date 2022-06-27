package com.mihaimiuta.api.webhook;

public class WebHook {
    private final String action;

    private final PullRequest pullRequest;

    private final Repository repository;

    public WebHook(String _action, PullRequest _pullRequest, Repository _repository) {
        this.action = _action;
        this.pullRequest = _pullRequest;
        this.repository = _repository;
    }

    public String getAction() {
        return action;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public Repository getRepository() {
        return repository;
    }
}
