package com.github.jrebel.core.controller;

import com.github.jrebel.core.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class IndexController {

    @RequestMapping({"/", ""})
    @SneakyThrows
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
//        response.sendRedirect("//www.jpy.wang/page/jrebel.html");
        return new ModelAndView("index", activateInfo(request));
    }

    @GetMapping("/get")
    @ResponseBody
    @SneakyThrows
    public Map<String, Object> index(HttpServletRequest request) {
        return activateInfo(request);
    }

    private Map<String, Object> activateInfo(HttpServletRequest request){
        String licenseUrl = System.getenv().getOrDefault("licenseUrl", request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()));
        String protocol = System.getenv().getOrDefault("protocol", "http://");
        return new HashMap<>() {{
            put("licenseUrl", licenseUrl);
            put("protocol", protocol);
            put("uuid", UUID.randomUUID().toString());
            put("mail", "%s@qq.com".formatted(RandomUtil.next(10000, 999999999)));
        }};
    }

}
