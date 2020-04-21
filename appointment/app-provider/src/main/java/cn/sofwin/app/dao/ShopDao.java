package cn.sofwin.app.dao;

import cn.sofwin.app.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDao {
    List<Shop> findAllRecom();

    Shop fingShopById(Integer id);

    List<Shop> findByMenu(Integer id);

    Shop fingByUserId(Integer id);

    void insertShop(Shop shop);
}
