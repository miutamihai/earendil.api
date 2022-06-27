package com.mihaimiuta.api;

import com.mihaimiuta.api.database.Repositories;
import com.mihaimiuta.api.webhook.WebHook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class HookController {
    private void cloneRepo(WebHook payload) throws Exception {
        String branch = payload.getPullRequest().getHead().getRef();
        String githubUrl = payload.getRepository().getCloneUrl();
        String command = String.format("git clone -b %s %s", branch, githubUrl);

        Process process = Runtime.getRuntime().exec(command);
        if (process.waitFor() != 0) {
            throw new Exception("Cloning repo failed");
        }
    }

    private String runEarendil(WebHook payload) throws Exception {
        String directory = payload.getRepository().getName();

        String command = String.format("./earendil run %s/earendil/steps.json", directory);
        Process process = Runtime.getRuntime().exec(command);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();

        if (process.waitFor() != 0) {
            throw new Exception("Running earendil failed");
        }

        return result;
    }

    public String provideResponse(WebHook payload) {
        String repoToken = new Repositories().getToken(payload.getRepository().getFullName());
        new Status().update(payload, repoToken, "pending");


        return "YEEY";

    }

    @PostMapping(value = "/trigger")
    public ResponseEntity<String> trigger(@RequestBody WebHook payload) {
        try {
            var earendilEnabled = Boolean.parseBoolean(System.getenv("earendilEnabled"));
            cloneRepo(payload);
            if (earendilEnabled) {
                return ResponseEntity.ok(runEarendil(payload));
            }
            else {
                return ResponseEntity.ok(provideResponse(payload));
            }
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
}
