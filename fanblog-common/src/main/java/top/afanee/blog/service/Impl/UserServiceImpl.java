package top.afanee.blog.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.User;
import top.afanee.blog.mappers.UserMapper;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

    @Override
    public User findUserByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User login(Subject currentUser, UsernamePasswordToken token) {
        //登陆
        currentUser.login(token);
        //再次查询
        String loginAcct = token.getUsername();
        Example example = new Example(User.class);
        if(StringUtils.isEmpty(loginAcct)){
            throw new UnknownAccountException("输入参数不合法");
        }
        if(loginAcct.contains("@")){
            example.createCriteria().andEqualTo("email", loginAcct);
        }
        else {
            example.createCriteria().andEqualTo("userName", loginAcct);
        }
        List<User> userList = userMapper.selectByExample(example);
        User user = userList.get(0);
        //更新
        User updateUser = new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(updateUser);
        return user;
    }


}
