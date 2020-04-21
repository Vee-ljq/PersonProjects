package cn.sofwin.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Recommend {
    private Integer id;
    private Integer shopid;
}
