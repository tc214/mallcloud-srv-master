package org.tc.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task/debug")
public class TestController {


    @RequestMapping("/index")
    public String index() {
        String services = "[debugName: index]";
        return services;
    }

    @RequestMapping("/get")
    public String get() {
        String services = "[debugName: get]";
        return services;
    }


}
