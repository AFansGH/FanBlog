package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.User;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.UserMapper;
import top.afanee.blog.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findUserByUserId(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User login(Subject currentUser, UsernamePasswordToken token) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String findHeadIcon(String loginAcct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void register(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User findUserInfo4UserHome(Integer userId) throws BussinessException {
        // TODO Auto-generated method stub
        return null;
    }

}
