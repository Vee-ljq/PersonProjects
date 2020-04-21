package cn.sofwin.app.web;

import cn.sofwin.app.service.imp.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/test/message")
    public String testMessage(String phone, HttpSession session){
        //随机生成6位验证码
        int newcode = (int) ((Math.random() * 9 + 1) * 100000);
        String code="{\"code\":\""+newcode+"\"}";//要发的内容，比如验证码等
        boolean b = MessageService.sendSms(phone, code, MessageService.muban);
        System.out.println("验证码："+newcode);
        session.setAttribute("yzm",newcode);
        if(b){
            return "ok";
        }else{
            return "no";
        }

    }

}
