package cn.sofwin.app.service.imp;

import cn.sofwin.app.dao.UserDao;
import cn.sofwin.app.entity.User;
import cn.sofwin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 实现用户的登录
     * @return
     */
    @Override
    public User findByUsernameAndPwd(User user1) {
        User user = userDao.selectByUsernameAndPwd(user1);
        return user;
    }

    /**
     * 修改用户的昵称
     * @param map
     */
    @Override
    public void updateByUserNickname(Map<String,Object> map) {
        userDao.updateByUserNickname(map);
    }

    /**
     * 通过用户的id
     * 查询用户的信息
     * @param id
     * @return
     */
    @Override
    public User selectByUserId(Integer id) {
        User user = userDao.findByUserId(id);
        return user;
    }

    /**
     * 用户修改手机号
     * @param map
     */
    @Override
    public void updateByUserPhone(Map<String, Object> map) {
        userDao.updateByUserPhone(map);
    }

    /**
     * 用户修改密码
     * @param map
     */
    @Override
    public void updateByUserPassword(Map<String, Object> map) {
        userDao.updateByUserPassword(map);
    }

    /**
     * 修改用户的头像
     * @param map
     */
    @Override
    public void updateByUserAvatar(Map<String, Object> map) {
        userDao.updateByUserAvatar(map);
    }

    /**
     * 将用户信息插入到数据库中
     * @param user
     */
    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
}
