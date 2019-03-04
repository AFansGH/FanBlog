package top.afanee.blog.service;

import java.util.List;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import top.afanee.blog.entities.User;

public interface UserService {

	List<User> getAll();

    User findUserByUserId(Integer userId);

    User login(Subject currentUser, UsernamePasswordToken token);

}
