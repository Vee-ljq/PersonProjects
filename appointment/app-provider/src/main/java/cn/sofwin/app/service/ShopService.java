package cn.sofwin.app.service;

import cn.sofwin.app.entity.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> selectAllRecom();

    Shop selectShopById(Integer id);

    List<Shop> selectByMenu(Integer id);

    Shop selectByUserId(Integer id);

    void insertShop(Shop shop);
}
