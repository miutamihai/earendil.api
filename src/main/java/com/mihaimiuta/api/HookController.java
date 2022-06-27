package com.mihaimiuta.api;

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

    private void runEarendil(WebHook payload) throws Exception {
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
        System.out.println(result);

        if (process.waitFor() != 0) {
            throw new Exception("Running earendil failed");
        }
    }

    @PostMapping(value = "/trigger")
    public ResponseEntity<String> trigger(@RequestBody WebHook payload) {
        try {
            cloneRepo(payload);
            runEarendil(payload);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
        return ResponseEntity.ok(payload.getPullRequest().getUser().getLogin());
    }
}
