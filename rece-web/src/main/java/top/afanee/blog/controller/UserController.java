package top.afanee.blog.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Ask;
import top.afanee.blog.entities.Blog;
import top.afanee.blog.entities.Knowledge;
import top.afanee.blog.entities.Topic;
import top.afanee.blog.entities.User;
import top.afanee.blog.po.enums.PageSize;
import top.afanee.blog.po.enums.ResponseCode;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.po.model.SessionUser;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.po.vo.AjaxResponse;
import top.afanee.blog.service.AskService;
import top.afanee.blog.service.BlogService;
import top.afanee.blog.service.KnowledgeService;
import top.afanee.blog.service.SignInService;
import top.afanee.blog.service.TopicService;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;

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
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 
	 * @Description TODO(首页 需要准备首页所需数据)
	 * @param session
	 * @return
	 */
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
		
		return view;
	}
	
	/**
	 * 
	 * @Description TODO(登陆)
	 * @return
	 */
	@RequestMapping("/login")
    public String login(){
        return "/page/login";
    }
	
	 /**
     * @Description (登陆)
     * @param user
     * @return
     */
    @RequestMapping("login.do")
    public @ResponseBody AjaxResponse<String> login(@RequestParam(value = "account") String loginAcct,
            @RequestParam(value = "password") String adminPswd,
            String rememberMe,
            HttpServletRequest request
            ) {
        final String REMEMBERME = "1";
        AjaxResponse<String> ajaxResponse = new AjaxResponse<>();
        Subject currentUser = SecurityUtils.getSubject();
        SessionUser sessionUser = new SessionUser();
        // 当前用户是否已经被认证，即是否登陆
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginAcct, adminPswd);
            try {
                User user = userService.login(currentUser, token);
                if(REMEMBERME.equals(rememberMe)){ 
                    // rememberme功能开启
                     token.setRememberMe(true); 
                }
                sessionUser.setUserid(user.getUserId());
                sessionUser.setUserName(user.getUserName());
                sessionUser.setUserIcon(user.getUserIcon());
                ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
            } catch (UnknownAccountException e) {
                ajaxResponse.setResponseCode(ResponseCode.NOTFOUNDERROR);
                ajaxResponse.setErrorMsg(e.getLocalizedMessage());
                logger.error("用户登录失败,账号:{}",loginAcct);
            } catch (LockedAccountException e) {
                ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
                ajaxResponse.setErrorMsg(e.getLocalizedMessage());
                logger.error("用户登录失败,账号:{}",loginAcct);
            } catch (IncorrectCredentialsException e) {
                ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
                ajaxResponse.setErrorMsg("用户名或密码错误");
                logger.error("用户登录失败,账号:{}",loginAcct);
            } catch (ExcessiveAttemptsException e) {
                ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
                ajaxResponse.setErrorMsg(e.getLocalizedMessage());
                logger.error("用户登录失败,账号:{}",loginAcct);
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
        return ajaxResponse;
    }
	
}
