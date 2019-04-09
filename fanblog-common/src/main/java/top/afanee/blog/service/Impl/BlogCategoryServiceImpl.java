package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.BlogCategory;
import top.afanee.blog.mapper.BlogCategoryMapper;
import top.afanee.blog.po.query.BlogCategoryQuery;
import top.afanee.blog.service.BlogCategoryService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory>
        implements BlogCategoryService {

    /**
     * 查询博客分类的同时还封装了每个博客分类下的博客数量
     */
    @Override
    public List<BlogCategory> findBlogCategoryList(Integer userId) {
        BlogCategoryQuery blogCategoryQuery = new BlogCategoryQuery();
        blogCategoryQuery.setUserId(userId);
        List<BlogCategory> list = super.baseMapper.selectList2BlogCategoryQuery(blogCategoryQuery);
        return list;
    }

}
