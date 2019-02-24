//package org.tc.provider.controller;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//
//@RestController
//public class SrvController {
//
//    private final Logger logger = LoggerFactory.getLogger(SrvController.class);
//
//
//    @Autowired
//    ConnectService connectService;
//
//    @GetMapping("/connect")
//    public String connect() {
//        logger.info("===<call connect>===");
//        return connectService.consumer();
//    }
//
//    @Service
//    class ConnectService {
//        @Autowired
//        RestTemplate restTemplate;
//
//        @HystrixCommand(fallbackMethod = "fallback")
//        public String consumer() {
//            return restTemplate.getForObject("http://user-service/dc", String.class);
//        }
//
//        public String fallback() {
//            return "service fallback";
//        }
//    }
//
//
//}
