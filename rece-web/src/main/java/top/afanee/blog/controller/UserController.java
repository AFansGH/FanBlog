package top.afanee.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.afanee.blog.entities.User;
import top.afanee.blog.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public List<User> Test(){
		
		List<User> userList =  userService.getAll();
		return userList;
	}
	
}
