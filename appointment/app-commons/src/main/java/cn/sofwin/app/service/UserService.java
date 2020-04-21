package cn.sofwin.app.service;

import cn.sofwin.app.entity.User;

public interface UserService {

    User findByUsernameAndPwd(User user);
}
