package cn.sofwin.app.service;

import cn.sofwin.app.dto.OrderDto;
import cn.sofwin.app.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderDto> selectAllDaiApp(Map<String, Object> map);

    void updateOrderStatus(Map<String, Object> map);

    List<OrderDto> selectShopDai(Map<String, Object> map);

    void insertOrder(Order order);
}
