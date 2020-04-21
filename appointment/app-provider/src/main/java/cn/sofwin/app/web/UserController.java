package cn.sofwin.app.web;

import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("app/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("index")
    public String toindex(){
        return "redirect:/index.html";
    }

    @RequestMapping("login")
    public String tologin(HttpSession session){

        if(session.getAttribute("user")!=null){
            //说明已经登录，再次点击就是显示到个人信息页面
            return "redirect:/uc.html";
        }else{
            return "redirect:/login.html";
        }

    }
    @RequestMapping("setting")
    public String tosetting(){
        return "redirect:/setting.html";
    }

    /**
     * 判断登录是否成功
     * @param session
     * @param user1
     * @return
     */
    @RequestMapping("yzlogin")
    public String yzlogin(String code,HttpSession session, User user1){
        //先判断用户输入的验证码是否成功
        String code1 = (String)session.getAttribute("code");
        if(code1.equals(code)){
            //说明验证码输入正确
            User user = userService.findByUsernameAndPwd(user1);
            if(user!=null){
                //说明用户可以登录成功
                session.setAttribute("user",user);
                return "redirect:/uc.html";
            }else{
                //说明用户用户名或密码错误
                return "redirect:/login.html";
            }
        }else{
            //说明验证码输入不正确
            return "redirect:/login.html";
        }

    }
    /**
     * 异步请求将放进session中的数据传给vue
     */
    @RequestMapping("sendUserToVue")
    @ResponseBody
    public User sendUserToVUE(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }
    /**
     * 用户修改昵称
     */
    @RequestMapping("updateNickname")
    public String updateNickname(HttpSession session,String nickname){
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = new HashMap<String,Object>();
        //通过user的id来修改用户的nickname
        map.put("id",user.getId());
        map.put("nickname",nickname);
        userService.updateByUserNickname(map);
        //修改完成之后，通过id在查询一下修改后的user，然后重新放到session中
        User user1 = userService.selectByUserId(user.getId());
        session.setAttribute("user",user1);
        return "redirect:/uc.html";
    }
    /**
     * 用户修改手机号
     */
    @RequestMapping("updatePhone")
    public String updatePhone(HttpSession session,String phone){
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = new HashMap<String,Object>();
        //通过user的id来修改用户的nickname
        map.put("id",user.getId());
        map.put("phone",phone);
        userService.updateByUserPhone(map);
        //修改完成之后，通过id在查询一下修改后的user，然后重新放到session中
        User user1 = userService.selectByUserId(user.getId());
        session.setAttribute("user",user1);
        return "redirect:/uc.html";
    }
    /**
     * 用户修改密码
     */
    @RequestMapping("updatePwd")
    public String updatePwd(String oldpwd,String newpwd,HttpSession session){
        //看输入的就密码和session中的密码是否一致
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("password",newpwd);
        userService.updateByUserPassword(map);
        //修改成功之后跳转到登录界面，重新登录
        return "redirect:/login.html";

    }
    /**
     * 修改用户的头像，然后获取到修改后的头像，更新到数据库中
     */
    @RequestMapping("set_avatar")
    @ResponseBody
    public Map<String,Object> set_avatar(HttpSession session, @RequestParam(value = "file",required = false)MultipartFile file){
        User user = (User) session.getAttribute("user");
        String pictureurl = "img/"+file.getOriginalFilename();
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("pictureurl",pictureurl);

        userService.updateByUserAvatar(map);
        //修改完成之后，通过id在查询一下修改后的user，然后重新放到session中
        User user1 = userService.selectByUserId(user.getId());
        session.setAttribute("user",user1);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("code",0);
        return map1;
    }

    /**
     * 跳转到注册用户页面
     */
    @RequestMapping("toregister")
    public String toregister(){
        return "redirect:/register.html";
    }

    /**
     *点击注册，将用户信息插入数据库中
     */
    @RequestMapping("registeruser")
    public String registeruser(User user,String code,Integer duanxin,HttpSession session){
        //首先判断手机验证码和普通验证码正确与否
        String ccode = (String) session.getAttribute("code");
        Integer yyzm = (Integer)session.getAttribute("yzm");
        if(ccode.equals(code)&&yyzm.equals(duanxin)){
            user.setPictureurl("img/"+user.getPictureurl());
            //正确了将用户信息存到数据库中
            userService.insertUser(user);
            return "redirect:/login.html";
        }else{
            //不正确就返回注册页面
            return "redirect:/register.html";
        }

    }
}

