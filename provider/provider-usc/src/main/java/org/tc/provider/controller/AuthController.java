package org.tc.provider.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usc")
public class AuthController {

    @RequestMapping("/user/info/mobile")
    public String getUserMobileNo() {
        String mobile = "13812346789";
        return mobile;
    }


}
