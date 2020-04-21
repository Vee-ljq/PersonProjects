package cn.sofwin.app.web;

import cn.sofwin.app.entity.Shop;
import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.ShopService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app/shop")
public class ShopController {
  

    @Autowired
    ShopService shopService;

    /**
     * 首页异步获取数据
     * @return
     */
    @RequestMapping("getindexdata")
    @ResponseBody
    public List<Shop> getindexdata(){
        List<Shop> list = shopService.selectAllRecom();
        return list;
    }

    /**
     *点击预约，跳转到预约详情
     */
    @RequestMapping("btnappointment")
    public String btnappointment(Integer id, HttpSession session){
        /*Shop shop = shopService.selectShopById(id);
        model.addAttribute("shop",shop);*/
        session.setAttribute("shopid",id);
        return "redirect:/service.html";
    }

    /**
     * 异步获取店铺信息
     */
    @RequestMapping("getshopmsg")
    @ResponseBody
    public Shop getshopmsg(HttpSession session){
        Integer id = (Integer)session.getAttribute("shopid");
        //通过id查询店铺信息
        Shop shop = shopService.selectShopById(id);
        return shop;
    }
    /**
     * 通过切换不同的菜单，来获取不同的页面内容
     */
    @RequestMapping("changeMenu")
    @ResponseBody
    public List<Shop> changeMenu(Integer id){
        List<Shop> shop = shopService.selectByMenu(id);
        return shop;
    }

    /**
     * 检查是否是商户，不是就申请
     * @param session
     * @return
     */
    @RequestMapping("checkShanghu")
    @ResponseBody
    public Shop checkShanghu(HttpSession session){
        User user = (User)session.getAttribute("user");
        Shop shop = shopService.selectByUserId(user.getId());
        return shop;
    }

    /**
     * 将申请的商户插入到数据库中
     * @param file
     * @param file1
     * @param shop
     * @param session
     * @return
     */
    @RequestMapping("applyShang")
    @ResponseBody
    public Map<String,Object> applyShang(@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam(value = "file1",required = false)MultipartFile file1,Shop shop,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        shop.setUserid(user.getId());
        String yyzzurl = "img/"+file.getOriginalFilename();
        String indexurl = "img/"+file1.getOriginalFilename();
        shop.setYyzzurl(yyzzurl);
        shop.setIndexurl(indexurl);
        shop.setBuypeople(0);
        shop.setSellcount(0);
        shopService.insertShop(shop);
        return map;
    }
}
