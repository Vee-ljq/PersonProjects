package cn.sofwin.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String pictureurl;
    private Double balance;

}
