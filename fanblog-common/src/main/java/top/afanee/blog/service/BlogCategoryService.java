package top.afanee.blog.service;

import top.afanee.blog.entity.BlogCategory;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    List<BlogCategory> findBlogCategoryList(Integer userId);

}
