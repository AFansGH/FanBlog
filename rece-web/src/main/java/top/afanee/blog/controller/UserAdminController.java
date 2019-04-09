package top.afanee.blog.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.afanee.blog.entity.Attachment;
import top.afanee.blog.entity.Blog;
import top.afanee.blog.entity.User;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.po.enums.BlogStatusEnum;
import top.afanee.blog.po.enums.ResponseCode;
import top.afanee.blog.po.model.SessionUser;
import top.afanee.blog.po.vo.AjaxResponse;
import top.afanee.blog.service.BlogCategoryService;
import top.afanee.blog.service.BlogService;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;



@Controller
@RequestMapping("/admin")
public class UserAdminController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(UserAdminController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogService blogService;
    
    /**
     * 
     * @Description TODO(添加博客前数据准备与页面逻辑)
     * @param session
     * @return
     */
    @RequestMapping("preAddBlog")
    public ModelAndView preAddBlog(HttpSession session){
        ModelAndView view = new ModelAndView("/page/admin/add_blog");
        SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
        if(sessionUser==null){
            view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
            return view;
        }
        try {
            User user = this.userService.findUserInfo4UserHome(sessionUser.getUserId());
            view.addObject("user", user);
            view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserId()));
        } catch (BussinessException e) {
            logger.error("获取用户信息失败：{}", e);
            view.setViewName("redirect:" + Constants.ERROR_404);
            return view;
        }
        return view;
    }
    
    /**
     * 
     * @Description TODO(保存博客的业务逻辑)
     * @param session
     * @param blog
     * @param attachment
     * @return
     */
    @RequestMapping("saveBlog")
    public @ResponseBody AjaxResponse<Integer> saveBlog(HttpSession session, Blog blog, Attachment attachment){
        AjaxResponse<Integer> ajaxResponse = new AjaxResponse<Integer>();
        try {
            blog.setStatus(BlogStatusEnum.PUBLIC);
            super.setUserBaseInfo(Blog.class, blog, session);
            if(blog.getBlogId()!=null){
                this.blogService.modifyBlog(blog, attachment);
            }
            else{
                this.blogService.addBlog(blog, attachment);
            }
            ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
            ajaxResponse.setData(blog.getBlogId());
        }catch(BussinessException e){
            logger.error("添加博客出错", e);
            ajaxResponse.setErrorMsg(e.getLocalizedMessage());
            ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
        }
        catch (Exception e) {
            logger.error("添加博客出错{}", e);
            ajaxResponse.setErrorMsg("添加博客出错,请重试");
            ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
        }
        return ajaxResponse;
    }
    
}
