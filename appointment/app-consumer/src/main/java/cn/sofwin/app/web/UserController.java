package cn.sofwin.app.web;

import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("index")
    public ModelAndView toindex(){
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }

    @RequestMapping("login")
    public ModelAndView tologin(){
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }

    @RequestMapping("yzlogin")
    public String yzlogin(HttpSession session,User user1){
        User user = userService.findByUsernameAndPwd(user1);
        if(user!=null){
            //说明用户可以登录成功
            session.setAttribute("user",user);
            return "redirect:index";
        }else{
            //说明用户用户名或密码错误
            return "redirect:login";
        }
    }
}
