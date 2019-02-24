package org.tc.provider.controller;

import org.tc.provider.mail.EmailSendServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/usc/debug")
public class TestController {

    @Resource
    EmailSendServiceImpl sendMailServiceImpl;


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

    @RequestMapping("/test")
    public String test() {
        System.out.println("index-start");
        String services = "[testName: test]";
        Set<String> to = new HashSet<String>();
        to.add("yezitan@yeah.net");
//        to.add("30730162@qq.com");
        sendMailServiceImpl.sendSimpleMail("足球比赛通知", "你好，为了让我们的团队变得越来越强大，" +
                "越来越有凝聚力，我们决定周六在公司体育馆举办团体足球比赛，请按时参加。", to);
        return services;
    }

}
