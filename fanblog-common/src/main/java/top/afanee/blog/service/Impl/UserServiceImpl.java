package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.User;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.UserMapper;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;
import top.afanee.blog.utils.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User findUserByUserId(Integer userId) {
        User user = userMapper.selectById(userId);
        return user;
    }

    @Override
    public User login(Subject currentUser, UsernamePasswordToken token) {
        //登陆
        currentUser.login(token);
        
        //登陆成功后查询登陆的用户信息
        User userQuery = new User();
        String username = token.getUsername();
        if(username.contains("@")){
            userQuery.setEmail(token.getUsername());
        }
        else{
            userQuery.setUserName(username);
        }
        User user = userMapper.selectOne(userQuery);
        user.setPassword(null);
        userQuery.setLastLoginTime(new Date());
        userQuery.setUserId(user.getUserId());
        
        //更新用户登陆时间
        userMapper.updateById(userQuery);
        return user;
    }

    @Override
    public String findHeadIcon(String loginAcct) {
        User user = new User();
        if(loginAcct.contains("@")){
            user.setEmail(loginAcct);
        }
        else{
            user.setUserName(loginAcct);
        }
        User selectOne = userMapper.selectOne(user);
        if(selectOne != null){
            return selectOne.getUserIcon();
        }
        return null;
    }
    
    /**
     * 用户注册
     * 1.判断用户名，邮箱是否可用
     * 2.给用户设置随机头像（物理地址真实存在），背景，注册时间，最后登录时间，对用户密码加密后插入数据库
     * 3.copy真实地址的头像到用户头像文件夹下，更新用户表
     * 
     */
    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void register(User user)throws BussinessException{
        String userName = user.getUserName();
        String password = user.getPassword();
        String email = user.getEmail();
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(email)
                || userName.length() < Constants.LENGTH_1 || userName.length() > Constants.LENGTH_20
                || password.length() < Constants.LENGTH_6 || password.length() > Constants.LENGTH_16
                || !StringUtils.isUserName(userName) || !StringUtils.isPassword(password)|| !StringUtils.isEmail(email)
                ){
            throw new BussinessException("输入参数不合法");
        }
        if(this.findUserByUserName(userName) != null){
            throw new BussinessException("用户名已存在");
        }
        if(this.findUserByEmail(email) != null){
            throw new BussinessException("邮箱已存在");
        }
        Date date = new Date();
        user.setRegisterTime(date);
        user.setLastLoginTime(date);
        user.setUserIcon(StringUtils.getRandomUserIcon());
        user.setUserBg(StringUtils.getRandomUserBg());
        //md5加密密码
        user.setPassword(StringUtils.encode(password));
        this.userMapper.insert(user);
        String dest = "user_icon/" + user.getUserId() + ".jpg";
        this.copyUserIcon(user.getUserIcon(), dest);
        user.setUserIcon(dest);
        this.updateUserWithoutValidateById(user);
    }

    @Override
    public User findUserInfo4UserHome(Integer userId) throws BussinessException {
        User user = this.findUserByUserId(userId);
        if(user == null){
            throw new BussinessException("用户不存在！");
        }
        user.setPassword(null);
        user.setActivationCode(null);
        return user;
    }
    
    @Override
    public User findUserByUserName(String userName) {   
        User userQuery = new User();
        userQuery.setUserName(userName);
        User user = userMapper.selectOne(userQuery);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User userQuery = new User();
        userQuery.setEmail(email);
        User user = userMapper.selectOne(userQuery);
        return user;
    }
    
    public void copyUserIcon(String source, String dest) {
        File sourceFile = new File(Constants.ABSOLUTEPATH + "/resources/images/" + source);
        File destFile = new File(Constants.ABSOLUTEPATH + "/resources/images/" + dest);
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void updateUserWithoutValidateById(User user) {
        this.userMapper.updateById(user);
    }

}
