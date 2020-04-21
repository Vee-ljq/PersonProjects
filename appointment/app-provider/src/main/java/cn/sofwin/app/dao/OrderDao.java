package cn.sofwin.app.dao;

import cn.sofwin.app.dto.OrderDto;
import cn.sofwin.app.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<OrderDto> findAllDaiApp(Map<String, Object> map);

    void updateOrderStatus(Map<String, Object> map);

    List<OrderDto> findShopDai(Map<String, Object> map);

    void insertOrder(Order order);
}
