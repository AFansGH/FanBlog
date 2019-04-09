package top.afanee.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.afanee.blog.entity.Ask;
import top.afanee.blog.entity.Blog;
import top.afanee.blog.entity.BlogCategory;
import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.entity.Shuoshuo;
import top.afanee.blog.entity.ShuoshuoComment;
import top.afanee.blog.entity.Topic;
import top.afanee.blog.entity.User;
import top.afanee.blog.entity.UserFriend;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.po.enums.BlogStatusEnum;
import top.afanee.blog.po.enums.ResponseCode;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.model.SessionUser;
import top.afanee.blog.po.query.AskQuery;
import top.afanee.blog.po.query.BlogQuery;
import top.afanee.blog.po.query.KnowledgeQuery;
import top.afanee.blog.po.query.ShuoShuoQuery;
import top.afanee.blog.po.query.TopicQuery;
import top.afanee.blog.po.query.UserFriendQuery;
import top.afanee.blog.po.vo.AjaxResponse;
import top.afanee.blog.service.AskService;
import top.afanee.blog.service.BlogCategoryService;
import top.afanee.blog.service.BlogService;
import top.afanee.blog.service.KnowledgeService;
import top.afanee.blog.service.ShuoshuoService;
import top.afanee.blog.service.TopicService;
import top.afanee.blog.service.UserFriendService;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;

@Controller
@RequestMapping("/user")
public class UserHomeController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(UserHomeController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private AskService askService;
    @Autowired
    private ShuoshuoService shuoshuoService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogService blogService;
    
    
    
    @RequestMapping(value="/{userId}")
    public ModelAndView user(HttpSession session, @PathVariable Integer userId){
        ModelAndView view = new ModelAndView("/page/user/home");
        try {
            User user = this.userService.findUserInfo4UserHome(userId);
            view.addObject("user", user);
            UserFriendQuery userFriendQuery = new UserFriendQuery();
            //将前台传递的userid与登陆的userid作为条件判断是否为朋友主页或是自身主页
            userFriendQuery.setUserId(super.getUserId(session));
            userFriendQuery.setFriendUserId(userId);
            view.addObject("focusType", this.userFriendService.findFocusType4UserHome(userFriendQuery));
            //获取粉丝数和关注数量
            userFriendQuery = new UserFriendQuery();
            userFriendQuery.setFriendUserId(userId);
            view.addObject("fansCount", this.userFriendService.findCount(userFriendQuery));
            userFriendQuery = new UserFriendQuery();
            userFriendQuery.setUserId(userId);
            view.addObject("focusCount", this.userFriendService.findCount(userFriendQuery));
        } catch (BussinessException e) {
            logger.error("获取用户信息失败：", e);
            view.setViewName("redirect:" + Constants.ERROR_404);
            return view;
        }
        return view;
    }
    
    /**
     * 
     * @Description TODO(加载所有说说)
     * @param session
     * @param shuoShuoQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("loadShuoShuos")
    public AjaxResponse<Object> loadShuoShuos(HttpSession session, ShuoShuoQuery shuoShuoQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            PageResult<Shuoshuo> pageResult = this.shuoshuoService.findShuoShuoListByPage(shuoShuoQuery);
            ajaxResponse.setData(pageResult);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.error("加载说说异常", e);
            ajaxResponse.setErrorMsg("加载说说出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    /**
     * 
     * @Description TODO(加载具体的说说)
     * @param session
     * @param shuoShuoQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("loadShuoShuoDetail")
    public AjaxResponse<Object> loadShuoShuoDetail(HttpSession session, ShuoShuoQuery shuoShuoQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            Shuoshuo shuoShuo = this.shuoshuoService.findShuoShuo(shuoShuoQuery);
            ajaxResponse.setData(shuoShuo);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.error("加载说说异常", e);
            ajaxResponse.setErrorMsg("加载说说出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    /**
     * 
     * @Description TODO(评论说说)
     * @param session
     * @param shuoShuoComment
     * @return
     */
    @ResponseBody
    @RequestMapping("publicShuoShuoComment")
    public AjaxResponse<Object> publicShuoShuoComment(HttpSession session, ShuoshuoComment shuoShuoComment){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
        if(sessionUser==null){
            ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
            ajaxResponse.setErrorMsg("请先登录");
            return ajaxResponse;
        }
        try {
            super.setUserBaseInfo(ShuoshuoComment.class, shuoShuoComment, session);
            this.shuoshuoService.addShuoShuoComment(shuoShuoComment);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
            ajaxResponse.setData(shuoShuoComment);
        } catch (BussinessException e) {
            ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
            ajaxResponse.setErrorMsg(e.getLocalizedMessage());
            logger.error("{}评论出错", shuoShuoComment.getUserName());
        }
        return ajaxResponse;
    }
    
    
    @RequestMapping("loadTopic")
    public @ResponseBody AjaxResponse<Object> loadTopic(HttpSession session, TopicQuery topicQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            PageResult<Topic> pageResult = this.topicService.findTopicByPage(topicQuery);
            
            ajaxResponse.setData(pageResult);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.error("加载帖子异常", e);
            ajaxResponse.setErrorMsg("加载帖子出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    
    @RequestMapping("loadAsk")
    public @ResponseBody AjaxResponse<Object> loadAsk(HttpSession session, AskQuery askQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            PageResult<Ask> pageResult = this.askService.findAskByPage(askQuery);
            ajaxResponse.setData(pageResult);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.error("加载问答异常", e);
            ajaxResponse.setErrorMsg("加载问答出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    
    @RequestMapping("loadKnowledge")
    public @ResponseBody AjaxResponse<Object> loadKnowledge(HttpSession session, KnowledgeQuery knowledgeQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try {
            PageResult<Knowledge> pageResult = this.knowledgeService.findKnowledgeByPage(knowledgeQuery);
            ajaxResponse.setData(pageResult);
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.error("加载知识库异常", e);
            ajaxResponse.setErrorMsg("加载知识库出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    
    
    @RequestMapping("loadFocus")
    public @ResponseBody AjaxResponse<Object> loadUserFriend(HttpSession session, UserFriendQuery userFriendQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try{
            PageResult<UserFriend> pageResult = this.userFriendService.findFriendListByPage(userFriendQuery);
            ajaxResponse.setData(pageResult);
        } catch (Exception e) {
            logger.error("加载关注用户异常", e);
            ajaxResponse.setErrorMsg("加载关注用户出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    
    @RequestMapping("loadFans")
    public @ResponseBody AjaxResponse<Object> loadUserFans(HttpSession session, UserFriendQuery userFriendQuery){
        AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
        try{
            PageResult<UserFriend> pageResult = this.userFriendService.findFansListByPage(userFriendQuery);
            ajaxResponse.setData(pageResult);
        } catch (Exception e) {
            logger.error("加载用户粉丝异常", e);
            ajaxResponse.setErrorMsg("加载粉丝出错");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
    /**
     * 
     * @Description TODO(加载用户博客页面)
     * @param session
     * @param userId
     * @param blogQuery
     * @return
     */
    @RequestMapping(value = "/{userId}/blog", method = RequestMethod.GET)
    public ModelAndView blog(HttpSession session, @PathVariable Integer userId, BlogQuery blogQuery){
        ModelAndView view = new ModelAndView("/page/user/blog");
        try {
            User user = this.userService.findUserInfo4UserHome(userId);
            UserFriendQuery userFriendQuery = new UserFriendQuery();
            userFriendQuery.setUserId(super.getUserId(session));
            userFriendQuery.setFriendUserId(userId);
            view.addObject("focusType", this.userFriendService.findFocusType4UserHome(userFriendQuery));
            blogQuery.setStatus(BlogStatusEnum.PUBLIC);
            List<BlogCategory> categoryList = this.blogCategoryService.findBlogCategoryList(userId);
            PageResult<Blog> result = this.blogService.findBlogByPage(blogQuery);
            view.addObject("user", user);
            view.addObject("categoryList", categoryList);
            view.addObject("result", result);
            
            //获取粉丝和关注数量
            userFriendQuery = new UserFriendQuery();
            userFriendQuery.setFriendUserId(userId);
            view.addObject("fansCount", this.userFriendService.findCount(userFriendQuery));
            userFriendQuery = new UserFriendQuery();
            userFriendQuery.setUserId(userId);
            view.addObject("focusCount", this.userFriendService.findCount(userFriendQuery));
        } catch (Exception e) {
            logger.error("获取博客信息失败：", e);
            view.setViewName("redirect:" + Constants.ERROR_404);
            return view;
        }
        return view;
    }
    
    @RequestMapping(value = "/{userId}/blog/{blogId}", method = RequestMethod.GET)
    public ModelAndView blogDetail(HttpSession session, @PathVariable Integer blogId){
        ModelAndView view = new ModelAndView("/page/user/blog_detail");
        try {
            Blog blog = this.blogService.showBlog(blogId);
            view.addObject("topic", blog);
        } catch (Exception e) {
            logger.error("获取博客信息失败：", e);
            view.setViewName("redirect:" + Constants.ERROR_404);
            return view;
        }
        return view;
    }
    
}
