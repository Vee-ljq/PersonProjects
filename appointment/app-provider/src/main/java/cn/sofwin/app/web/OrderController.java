package cn.sofwin.app.web;

import cn.sofwin.app.dao.OrderDao;
import cn.sofwin.app.dto.OrderDto;
import cn.sofwin.app.entity.Good;
import cn.sofwin.app.entity.Order;
import cn.sofwin.app.entity.Shop;
import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.GoodService;
import cn.sofwin.app.service.OrderService;
import cn.sofwin.app.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ShopService shopService;

    @Autowired
    GoodService goodService;

    /**
     * 去到带赴约页面
     * @return
     */
    @RequestMapping("dapp")
    public String todapp(Integer flag,HttpSession session){
        session.setAttribute("flag",flag);
        return "redirect:/uo_list.html";
    }

    /**
     * 查询待赴约/待评价/已完成的订单
     * @param session
     * @return
     */
    @RequestMapping("getdappdata")
    @ResponseBody
    public List<OrderDto> getdappdata(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer flag = (Integer)session.getAttribute("flag");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",user.getId());
        map.put("flag",flag);
        List<OrderDto> list = orderService.selectAllDaiApp(map);
        return list;
    }

    /**
     * 查询订单的状态的数量
     * @param session
     * @return
     */
    @RequestMapping("getOrderCount")
    @ResponseBody
    public Integer[] getVaryCount(HttpSession session){
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        map.put("userid",user.getId());
        map.put("flag",0);
        List<OrderDto> list = orderService.selectAllDaiApp(map);
        map.put("flag",1);
        List<OrderDto> list1 = orderService.selectAllDaiApp(map);
        map.put("flag",2);
        List<OrderDto> list2 = orderService.selectAllDaiApp(map);
        map.put("flag",4);
        List<OrderDto> list3 = orderService.selectAllDaiApp(map);

        //商家
        Shop shop = shopService.selectByUserId(user.getId());
        Map<String,Object> map1 = new HashMap<>();
        map.put("shopid",shop.getId());
        map.put("status",0);
        List<OrderDto> list4 = orderService.selectShopDai(map);
        map.put("status",2);
        List<OrderDto> list5 = orderService.selectShopDai(map);
        map.put("status",4);
        List<OrderDto> list6 = orderService.selectShopDai(map);
        Integer[] count = {list.size(),list1.size(),list2.size(),list3.size(),list4.size(),list5.size(),list6.size()};
        return count;
    }

    /**
     * 将订单状态改变
     */
    @RequestMapping("changeStatus")
    @ResponseBody
    public void changeStatus(Integer id,Integer status,HttpSession session){
        User user = (User) session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status+1);
        map.put("userid",user.getId());
        orderService.updateOrderStatus(map);
    }

    /**
     * 跳转到商户列表页面
     */
    @RequestMapping("shoplist")
    public String tomlist(Integer flag,HttpSession session){
        session.setAttribute("flag",flag);
        return "redirect:/mo_list.html";
    }

    /**
     * 异步查询商户的待赴约/待评价
     */
    @RequestMapping("getShopDai")
    @ResponseBody
    public List<OrderDto> getShopDai(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer flag = (Integer)session.getAttribute("flag");
        //通过用户将他对应的商铺的id查询出来
        Shop shop = shopService.selectByUserId(user.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("shopid",shop.getId());
        map.put("status",flag);
        List<OrderDto> list = orderService.selectShopDai(map);
        return list;
    }

    /**
     * 将该店铺的服务都展示出来
     * @param session
     * @return
     */
    @RequestMapping("getShopGoods")
    @ResponseBody
    public List<Good> getShopGoods(HttpSession session){
        User user = (User) session.getAttribute("user");
        //通过商铺的id来查询该商铺下的服务
        Shop shop = shopService.selectByUserId(user.getId());
        List<Good> list = goodService.selectAllServiceByShopId(shop.getId());
        return list;
    }

    /**
     * 跳转到增加用户服务页面
     * @return
     */
    @RequestMapping("toServiceForm")
    public String toServiceForm(){
        return "redirect:/service_form.html";
    }

    @RequestMapping("addService")
    @ResponseBody
    public Map<String,Object> addService(Good goods, @RequestParam(value = "file",required = false)MultipartFile file, HttpSession session){
        User user = (User) session.getAttribute("user");
        Shop shop = shopService.selectByUserId(user.getId());
        String indexurl = file.getOriginalFilename();
        goods.setIndexurl("img/"+indexurl);
        goods.setShopid(shop.getId());
        goods.setStatus(0);
        goodService.insertService(goods);
        Map<String,Object> map = new HashMap<>();
        map.put("ok","ok");
        return map;
    }

    /**
     * 用户点击立即预约之后，在订单里添加一条订单
     */
    @RequestMapping("nowapp")
    @ResponseBody
    public Map<String,Object> nowapp(HttpSession session,Integer shopid){
        User user = (User)session.getAttribute("user");
        Order order = new Order();
        order.setUseid(user.getId());
        order.setShopid(shopid);
        order.setStatus(0);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdftime = sdf.format(date);
        order.setTime(sdftime.substring(0,7));
        order.setDate(sdftime.substring(10,sdftime.length()));
        orderService.insertOrder(order);
        Map<String,Object> map = new HashMap<>();
        return map;
    }
}
