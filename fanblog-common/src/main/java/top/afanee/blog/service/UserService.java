package top.afanee.blog.service;

import java.util.List;

import top.afanee.blog.entities.User;

public interface UserService {

	List<User> getAll();

    User findUserByUserid(Integer userid);

}
