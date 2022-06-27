package com.mihaimiuta.api;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HookController {
    @PostMapping(value="/trigger")
    public String trigger(@RequestBody Map<String, Object> payload) {
        JSONObject jsonObject = new JSONObject(payload);
        System.out.println(jsonObject);

        return jsonObject.toString();
    }
}
