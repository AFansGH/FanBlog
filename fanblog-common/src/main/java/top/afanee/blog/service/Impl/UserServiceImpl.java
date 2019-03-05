package top.afanee.blog.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.User;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mappers.UserMapper;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;
import top.afanee.blog.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return this.userMapper.selectAll();
    }

    @Override
    public User findUserByUserId(Integer userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User login(Subject currentUser, UsernamePasswordToken token) {
        // 登陆
        currentUser.login(token);
        // 再次查询
        String loginAcct = token.getUsername();
        Example example = new Example(User.class);
        if (StringUtils.isEmpty(loginAcct)) {
            throw new UnknownAccountException("输入参数不合法");
        }
        if (loginAcct.contains("@")) {
            example.createCriteria().andEqualTo("email", loginAcct);
        } else {
            example.createCriteria().andEqualTo("userName", loginAcct);
        }
        User user = this.userMapper.selectOneByExample(example);
        // 更新
        User updateUser = new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setLastLoginTime(new Date());
        this.userMapper.updateByPrimaryKeySelective(updateUser);
        return user;
    }

    @Override
    public String findHeadIcon(String loginAcct) throws BussinessException {
        Example example = new Example(User.class);
        if (StringUtils.isEmpty(loginAcct)) {
            throw new BussinessException("输入参数不合法");
        }
        if (loginAcct.contains("@")) {
            example.createCriteria().andEqualTo("email", loginAcct);
        } else {
            example.createCriteria().andEqualTo("userName", loginAcct);
        }
        User user = this.userMapper.selectOneByExample(example);
        if (user == null) {
            throw new BussinessException("用户不存在");
        }
        return user.getUserIcon();
    }

    @Override
    public void register(User user) throws BussinessException {

        if (!this.available(user.getUserName()))
            throw new BussinessException("用户名已存在，不可用！");
        if (!this.available(user.getEmail()))
            throw new BussinessException("邮箱已存在，不可用！");
        user.setRegisterTime(new Date());
        String hashAlgorithmName = "MD5";
        String credentials = user.getPassword();
        Object salt = ByteSource.Util.bytes(Constants.MD5_SALT);
        int hashIterations = 3;
        Object encryptionCipher = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        user.setPassword(encryptionCipher.toString());
        user.setUserIcon(StringUtils.getRandomUserIcon());
        user.setUserBg(StringUtils.getRandomUserBg());
        
        this.userMapper.insertSelective(user);
        
        //用户注册完成之后才更新用户头像，与用户注册不相互影响
        String dest = "user_icon/" + user.getUserId() + ".jpg";
        this.copyUserIcon(user.getUserIcon(), dest);
        user.setUserIcon(dest);
        this.updateUserWithoutValidate(user);
        
    }
    
    /**
     * 用户名或邮箱是否可用
     */
    @Override
    public Boolean available(String loginAcct) throws BussinessException{
        
        Example example = new Example(User.class);
        if (StringUtils.isEmpty(loginAcct)) {
            throw new BussinessException("输入参数不合法");
        }
        if (loginAcct.contains("@")) {
            example.createCriteria().andEqualTo("email", loginAcct);
        } else {
            example.createCriteria().andEqualTo("userName", loginAcct);
        }
        User user = this.userMapper.selectOneByExample(example);
        return user == null ? true : false;
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

    public void updateUserWithoutValidate(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);
    }
}
