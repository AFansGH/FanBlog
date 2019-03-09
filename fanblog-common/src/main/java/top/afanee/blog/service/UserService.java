package top.afanee.blog.service;

import java.util.List;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import top.afanee.blog.entities.User;
import top.afanee.blog.exception.BussinessException;

public interface UserService {

	List<User> getAll();

    User findUserByUserId(Integer userId);
    
    //查询user对象到前台，需要将密码与激活码置为null
    User findUserInfo4UserHome(Integer userId) throws BussinessException;

    User login(Subject currentUser, UsernamePasswordToken token);

    String findHeadIcon(String loginAcct) throws BussinessException;

    void register(User user) throws BussinessException;
    
    Boolean available(String loginAcct) throws BussinessException;

   

}
