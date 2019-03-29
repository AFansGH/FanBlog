package top.afanee.blog.service;

import top.afanee.blog.entity.User;
import top.afanee.blog.exception.BussinessException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface UserService extends IService<User> {

    User findUserByUserId(Integer userId);

    User login(Subject currentUser, UsernamePasswordToken token);

    String findHeadIcon(String loginAcct) throws BussinessException ;

    void register(User user) throws BussinessException ;

    User findUserInfo4UserHome(Integer userId) throws BussinessException ;

}
