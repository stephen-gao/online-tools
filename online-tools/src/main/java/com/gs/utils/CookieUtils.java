package com.gs.utils;

import com.gs.controller.PageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 20:27
 **/
public class CookieUtils {
    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    private static final String tokenKey = "token";

    public static void addToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (tokenKey.equals(c.getName())) {
                return;
            }
        }
        Cookie cookie = new Cookie(tokenKey, UUID.randomUUID().toString());
        response.addCookie(cookie);
    }

    public static String verifyToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (tokenKey.equals(c.getName())) {
                logger.info("获取token:{}", c.getValue());
                return c.getValue();
            }
        }
        String tokenValue = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(tokenKey, tokenValue);
        response.addCookie(cookie);
        logger.info("添加token:{}", tokenValue);
        return tokenValue;
    }
}
