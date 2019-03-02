package top.afanee.blog.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Ask;
import top.afanee.blog.entities.Blog;
import top.afanee.blog.entities.Knowledge;
import top.afanee.blog.entities.Topic;
import top.afanee.blog.entities.User;
import top.afanee.blog.po.enums.PageSize;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.service.AskService;
import top.afanee.blog.service.BlogService;
import top.afanee.blog.service.KnowledgeService;
import top.afanee.blog.service.SignInService;
import top.afanee.blog.service.TopicService;
import top.afanee.blog.service.UserService;

@Controller
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
    private SignInService signInService;
	@Autowired
    private TopicService topicService;
	@Autowired
    private KnowledgeService knowledgeService;
	@Autowired
    private AskService askService;
	@Autowired
    private BlogService blogService;
	
	
	
	@RequestMapping("/")
	public ModelAndView index(HttpSession session){
		Integer userid = this.getUserid(session);
		ModelAndView view = new ModelAndView("/page/index");
		//若用户登陆，查询用户签到信息
		if(userid !=null){
			SignInfo signInfo = this.signInService.findSignInfoByUserid(userid);
			view.addObject("signInfo", signInfo);
		}
		Page page = new Page(1, PageSize.PAGE_SIZE20.getSize());
		PageInfo<Topic> topics = this.topicService.findTopicByPage(page);
		view.addObject("topics", topics);
		PageInfo<Knowledge> knowledges = this.knowledgeService.findKnowledgeByPage(page);
		view.addObject("knowledges", knowledges);
		PageInfo<Ask> asks = this.askService.findAskByPage(page);
		view.addObject("asks", asks);
		PageInfo<Blog> blogs = this.blogService.findBlogByPage(page);
		view.addObject("blogs", blogs);
		
		//测试路径
		view.addObject("realpath", "http://localhost:8080/rece-web/");
		
		return view;
	}
	
}
