package com.mihaimiuta.api;

import com.mihaimiuta.api.webhook.WebHook;
import org.springframework.web.bind.annotation.*;

@RestController
public class HookController {
    @PostMapping(value="/trigger")
    public String trigger(@RequestBody WebHook payload) {
        System.out.print(payload);

        return payload.getAction();
    }
}
