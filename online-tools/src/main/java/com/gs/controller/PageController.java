package com.gs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 20:15ZztTokenInterceptor
 **/
@Controller
@RequestMapping("tool")
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @ResponseBody
    @GetMapping("/pdf2png")
    public String pdf2Png(HttpServletRequest request, HttpServletResponse response){
//        CookieUtils.verifyToken(request, response);

        return "success";
    }

}
