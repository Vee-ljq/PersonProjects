package cn.sofwin.app.service.imp;

import cn.sofwin.app.dao.OrderDao;
import cn.sofwin.app.dto.OrderDto;
import cn.sofwin.app.entity.Order;
import cn.sofwin.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderDao orderDao;
    /**
     * 查询待赴约/待评价/待完成的订单
     * @param map
     * @return
     */
    @Override
    public List<OrderDto> selectAllDaiApp(Map<String, Object> map) {
        List<OrderDto> list = orderDao.findAllDaiApp(map);
        return list;
    }

    /**
     * 更新订单的状态
     * @param map
     */
    @Override
    public void updateOrderStatus(Map<String, Object> map) {
        orderDao.updateOrderStatus(map);
    }

    /**
     * 查询商户的待赴约/已完成订单之类的
     * @param map
     * @return
     */
    @Override
    public List<OrderDto> selectShopDai(Map<String, Object> map) {
        List<OrderDto> list = orderDao.findShopDai(map);
        return list;
    }

    /**
     * 添加订单，并插入到数据库中
     * @param order
     */
    @Override
    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

}
