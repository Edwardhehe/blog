package cn.edwardhehe.blog.service;

import cn.edwardhehe.blog.dao.UserDao;
import cn.edwardhehe.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserDao userDao;

    public boolean login(String username, String password){
        User user = userDao.findByUsernameAndPassword(username, password);
        if(null == user){
            return false;
        }else {

            return true;
        }
    }
}
