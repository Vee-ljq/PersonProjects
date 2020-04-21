package cn.sofwin.app.service.imp;

import cn.sofwin.app.dao.ShopDao;
import cn.sofwin.app.entity.Shop;
import cn.sofwin.app.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImp implements ShopService {

    @Autowired
    ShopDao shopDao;

    /**
     * 查询时推荐的商家，并显示到首页的推荐里
     * @return
     */
    @Override
    public List<Shop> selectAllRecom() {
        List<Shop> list = shopDao.findAllRecom();
        return list;
    }

    /**
     * 通过id查询店铺的详细信息
     * @param id
     * @return
     */
    @Override
    public Shop selectShopById(Integer id) {
        Shop shop = shopDao.fingShopById(id);
        return shop;
    }

    /**
     * 通过切换菜单来获取不同的页面内容
     * @param id
     * @return
     */
    @Override
    public List<Shop> selectByMenu(Integer id) {
        List<Shop> shop = shopDao.findByMenu(id);
        return shop;
    }

    /**
     * 通过用户的id查询该用户是否为商户
     * @param id
     * @return
     */
    @Override
    public Shop selectByUserId(Integer id) {
        Shop shop = shopDao.fingByUserId(id);
        return shop;
    }

    /**
     * 将申请的用户插入到数据库中
     * @param shop
     */
    @Override
    public void insertShop(Shop shop) {
        shopDao.insertShop(shop);
    }
}
