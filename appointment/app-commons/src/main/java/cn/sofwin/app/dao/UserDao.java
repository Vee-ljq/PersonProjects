package cn.sofwin.app.dao;

import cn.sofwin.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    //@Select("select * from user where username = #{username} and password=#{password}")
    User selectByUsernameAndPwd(User user);
}
