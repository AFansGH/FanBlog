package top.afanee.blog.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import top.afanee.blog.entity.Ask;
import top.afanee.blog.entity.Blog;
import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.entity.Topic;
import top.afanee.blog.entity.User;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.po.enums.ResponseCode;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.model.SessionUser;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.po.query.AskQuery;
import top.afanee.blog.po.query.BlogQuery;
import top.afanee.blog.po.query.KnowledgeQuery;
import top.afanee.blog.po.query.TopicQuery;
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
		Integer userId = this.getUserId(session);
		ModelAndView view = new ModelAndView("/page/index");
		//若用户登陆，查询用户签到信息
		if(userId !=null){
			SignInfo signInfo = this.signInService.findSignInfoByUserId(userId);
			view.addObject("signInfo", signInfo);
		}
		PageResult<Topic> topics = this.topicService.findTopicByPage(new TopicQuery());
		view.addObject("topics", topics);
		PageResult<Knowledge> knowledges = this.knowledgeService.findKnowledgeByPage(new KnowledgeQuery());
		view.addObject("knowledges", knowledges);
		PageResult<Ask> asks = this.askService.findAskByPage(new AskQuery());
		view.addObject("asks", asks);
		PageResult<Blog> blogs = this.blogService.findBlogByPage(new BlogQuery());
		view.addObject("blogs", blogs);
		
		return view;
	}
	
	/**
	 * 
	 * @Description TODO(登陆页)
	 * @return
	 */
	@RequestMapping("/login")
    public String login(){
        return "/page/login";
    }
	
	 /**
	  * 
	  * @Description TODO(登陆方法)
	  * @param loginAcct
	  * @param adminPswd
	  * @param rememberMe
	  * @param request
	  * @return
	  */
    @RequestMapping("login.do")
    public @ResponseBody AjaxResponse<String> login(@RequestParam(value = "account") String loginAcct,
            @RequestParam(value = "password") String adminPswd,
            String rememberMe,
            HttpServletRequest request
            ) {
        
        String rootPath = request.getSession().getServletContext().getRealPath("/resources/images/");
        System.out.println("rootPath :"+rootPath);
        
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
    
    /**
     * 
     * @Description TODO(获得用户头像)
     * @param loginAcct
     * @return
     */
    @RequestMapping("/findHeadImage")
    public @ResponseBody AjaxResponse<Object> findHeadImage(@RequestParam(value = "account") String loginAcct){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        String headIcon = null;
        try {
            headIcon = userService.findHeadIcon(loginAcct);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
            ajaxResponse.setData(headIcon);
        } catch (BussinessException e) {
            ajaxResponse.setErrorMsg(e.getLocalizedMessage());
            ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
            logger.error("头像获取失败,账户{}异常{}", loginAcct, e.getLocalizedMessage());
        }
        catch (Exception e) {
            ajaxResponse.setErrorMsg(ResponseCode.SERVERERROR.getDesc());
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
            logger.error("头像获取失败,账户{}异常{}", loginAcct, e.getLocalizedMessage());
        }
        return ajaxResponse;
    }
    
    /**
     * 
     * @Description TODO(注册页)
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        
        return "/page/register";
    }
    
    
    /**
     * 
     * @Description TODO(注册方法)
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/register.do")
    public @ResponseBody AjaxResponse<Object> registerdo(HttpSession session, @Valid User user, BindingResult bindingResult){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            
            String userName = user.getUserName();
            String password = user.getPassword();
            //注册成功
            userService.register(user);
            //登陆
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            currentUser.login(token);
            
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserid(user.getUserId());
            sessionUser.setUserName(user.getUserName());
            sessionUser.setUserIcon(user.getUserIcon());
            session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
        } catch (BussinessException e) {
            ajaxResponse.setErrorMsg(e.getLocalizedMessage());
            ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
            logger.error("用户注册失败,用户名:{}邮箱:{}", user.getUserName(), user.getEmail());
        }catch (Exception e) {
            e.getLocalizedMessage();
            ajaxResponse.setErrorMsg(ResponseCode.SERVERERROR.getDesc()+"注册成功，自动登陆失败！");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
            logger.error("用户登陆失败,用户名:{}邮箱:{}", user.getUserName(), user.getEmail());
        }
        return ajaxResponse;
    }
    
}
