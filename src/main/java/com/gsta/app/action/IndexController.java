package com.gsta.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页 . <br>
 * 
 * @author xh
 */
@Controller
public class IndexController {

    /**
     * 跳转到index.jsp
     * 
     * @return
     */
    @RequestMapping({ "/", "/login" })
    public String login() {
        System.out.println("登录");
        return "login";
    }


}
