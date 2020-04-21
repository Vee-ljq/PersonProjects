package cn.sofwin.app.service.imp;

import cn.sofwin.app.dao.GoodDao;
import cn.sofwin.app.entity.Good;
import cn.sofwin.app.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("goodsService")
public class GoodServiceImp implements GoodService {
    @Autowired
    GoodDao goodDao;
    @Override
    public List<Good> selectAllServiceByShopId(Integer id) {
        List<Good> list = goodDao.findAllServiceByShopId(id);
        return list;
    }

    /**
     * 增加用户的服务
     * @param goods
     */
    @Override
    public void insertService(Good goods) {
        goodDao.insertService(goods);
    }
}
