package cn.sofwin.app.web;

import cn.sofwin.app.dao.CollectionDao;
import cn.sofwin.app.dto.CollectionDto;
import cn.sofwin.app.entity.Collection;
import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    /**
     * 查询我的收藏
     * @param session
     * @return
     */
    @RequestMapping("getCollectionData")
    @ResponseBody
    public List<CollectionDto> getCollectionData(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<CollectionDto> list = collectionService.selectCollectionByUserid(user.getId());
        return list;

    }
    /**
     * 删除一个收藏
     */

    @ResponseBody
    @RequestMapping("delCollection")
    public Map<String,Object> delCollection(String collectionid){

        collectionService.deleteCollectionById(Integer.parseInt(collectionid));
        Map<String,Object> map = new HashMap<>();
        return map;
    }
    /**
     * 新增收藏
     */
    @ResponseBody
    @RequestMapping("nowcol")
    public Map<String,Object> insertCollection(Integer shopid,HttpSession session){
        User user  = (User) session.getAttribute("user");
        Collection collection = new Collection();
        collection.setShopid(shopid);
        collection.setUseid(user.getId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String date1 = sdf.format(date);
        collection.setDate(date1.substring(0,7));
        collection.setTime(date1.substring(10,date1.length()));
        collectionService.insertCollection(collection);
        Map<String,Object> map = new HashMap<>();
        return map;
    }

}
