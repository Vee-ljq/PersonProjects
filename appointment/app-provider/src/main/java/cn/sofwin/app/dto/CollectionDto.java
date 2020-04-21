package cn.sofwin.app.dto;

import cn.sofwin.app.entity.Collection;
import cn.sofwin.app.entity.Shop;
import lombok.Data;

@Data
public class CollectionDto extends Collection {

    private Shop shop;
}
