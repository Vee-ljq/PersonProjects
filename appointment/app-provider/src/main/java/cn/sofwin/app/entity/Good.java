package cn.sofwin.app.entity;

import lombok.Data;

@Data
public class Good {

    private Integer id;
    private String goodsname;
    private Integer shopid;
    private String  stoptime;
    private String  goodsaddress;
    private String goodsbrief;
    private String goodsdetail;
    private String indexurl;
    private Integer status;
}
