package cn.sofwin.app.dao;

import cn.sofwin.app.entity.Good;

import java.util.List;

public interface GoodDao {
    List<Good> findAllServiceByShopId(Integer id);

    void insertService(Good goods);
}
