package top.afanee.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.afanee.blog.entities.User;
import top.afanee.blog.mappers.UserMapper;
import top.afanee.blog.service.UserService;

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
    public User findUserByUserid(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

}
