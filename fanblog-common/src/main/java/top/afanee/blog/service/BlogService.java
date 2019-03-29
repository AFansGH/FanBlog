package top.afanee.blog.service;

import top.afanee.blog.entity.Blog;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.BlogQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface BlogService extends IService<Blog> {

    PageResult<Blog> findBlogByPage(BlogQuery blogQuery);


}
