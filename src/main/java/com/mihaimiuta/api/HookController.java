package com.mihaimiuta.api;

import com.mihaimiuta.api.webhook.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HookController {
    @PostMapping(value="/trigger")
    public String trigger(@RequestBody WebHook payload) {
        return payload.getPullRequest().getUser().getLogin();
    }
}
