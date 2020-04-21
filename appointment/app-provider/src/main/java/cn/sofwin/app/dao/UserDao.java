package cn.sofwin.app.dao;

import cn.sofwin.app.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {

    //@Select("select * from user where username = #{username} and password=#{password}")
    User selectByUsernameAndPwd(User user);

    void updateByUserNickname(Map<String,Object> map);

    User findByUserId(Integer id);

    void updateByUserPhone(Map<String, Object> map);

    void updateByUserPassword(Map<String, Object> map);

    void updateByUserAvatar(Map<String, Object> map);

    void insertUser(User user);
}
