package cn.sofwin.app.service;

import cn.sofwin.app.entity.User;

import java.util.Map;

public interface UserService {

    User findByUsernameAndPwd(User user);

    void updateByUserNickname(Map<String,Object> map);

    User selectByUserId(Integer id);

    void updateByUserPhone(Map<String, Object> map);

    void updateByUserPassword(Map<String, Object> map);

    void updateByUserAvatar(Map<String, Object> map);

    void insertUser(User user);
}
