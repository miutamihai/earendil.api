package com.mihaimiuta.api;

import com.mihaimiuta.api.webhook.WebHook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import java.util.HashMap;
import java.util.Map;

public class Status {
    public void update(WebHook webHook, String token, String status) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Accept", "application/vnd.github.v3+json");
        httpHeaders.add("Authorization", String.format("token %s", token));

        String url = String.format("https://api.github.com/repos/%s/statuses/%s", webHook.getRepository().getFullName(), webHook.getPullRequest().getHead().getSha());

        Map<String, String> body = new HashMap<>();
        body.put("state", status);
        body.put("target_url", "some_url");
        body.put("description", String.format("The build is %s", status));
        body.put("context", "some context idk");


        HttpEntity<?> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    }
}
