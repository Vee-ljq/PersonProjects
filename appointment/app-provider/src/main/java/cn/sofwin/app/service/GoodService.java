package cn.sofwin.app.service;

import cn.sofwin.app.entity.Good;

import java.util.List;

public interface GoodService {
    List<Good> selectAllServiceByShopId(Integer id);

    void insertService(Good goods);
}
