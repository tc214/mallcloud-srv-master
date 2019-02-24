package org.tc.provider.controller;


import org.tc.provider.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/usc/auth")
public class ActivateController {

    @Resource
    private UserService uscUserService;


}
