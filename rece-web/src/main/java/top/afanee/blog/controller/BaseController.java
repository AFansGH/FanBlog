package top.afanee.blog.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import top.afanee.blog.po.model.SessionUser;
import top.afanee.blog.utils.Constants;

/**
 * 
 * @ClassName BaseController
 * @Description TODO(基础Controller)
 * @author Fan
 * @Date 2019年3月2日 下午1:23:41
 * @version 1.0.0
 */
public class BaseController {
	
	public void setUserBaseInfo(Class<?> clazz, Object obj, HttpSession session){
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		Integer userId = sessionUser.getUserId();
		String userName = sessionUser.getUserName();
		String userIcon = sessionUser.getUserIcon();
		try {
			Method UserIdMethod = clazz.getDeclaredMethod("setUserId", Integer.class);
			UserIdMethod.invoke(obj, userId);
			Method UserNameMethod = clazz.getDeclaredMethod("setUserName", String.class);
			UserNameMethod.invoke(obj, userName);
			Method UserIconMethod = clazz.getDeclaredMethod("setUserIcon", String.class);
			UserIconMethod.invoke(obj, userIcon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public Integer getUserId(HttpSession session){
		Object sessionObject = session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionObject != null){
			return ((SessionUser)sessionObject).getUserId();
		}
		return null;
	}
}
