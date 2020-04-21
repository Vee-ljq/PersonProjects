package cn.sofwin.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {

    private Integer id;
    private Integer useid;
    private Integer shopid;
    private Integer status;
    private String date;
    private String time;
}
