package cn.sofwin.app.dto;

import cn.sofwin.app.entity.Order;
import cn.sofwin.app.entity.Shop;
import cn.sofwin.app.entity.User;
import lombok.Data;

@Data
public class OrderDto extends Order {

    private Shop shop;

    private User user;

}
