package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author： SongJunBao
 * @Date： 2022/9/9 9:32
 * @Package: com.example.controller
 * @Description： 测试session
 * @Version： 1.0
 */
@RestController
@RequestMapping("session")
@Slf4j
public class SessionTestController extends HttpServlet {
    @PostMapping("save")
    public void saveSession(HttpServletRequest request, HttpServletResponse resp) {
        HttpSession session = request.getSession();
        session.setAttribute("key", "test message");
        log.info("save success ");
        System.out.println(session.toString());
    }

    @GetMapping("get")
    public void getSession(
            HttpServletRequest request, HttpServletResponse resp) {
        String attribute = (String) request.getSession().getAttribute("key");
        System.out.println(attribute);
    }
}
