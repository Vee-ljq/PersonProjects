package cn.sofwin.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Shop {
    private Integer id;
    private Integer userid;
    private String shopname;
    private String shopbrief;
    private String shopaddress;
    private String yyzzurl;
    private String indexurl;
    private Integer buypeople;
    private Integer sellcount;
    private String shopdetail;
    private String stoptime;
    private String category;
    private Integer recom;
}
