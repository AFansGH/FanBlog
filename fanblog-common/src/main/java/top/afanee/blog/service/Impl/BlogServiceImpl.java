package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Blog;
import top.afanee.blog.mapper.BlogMapper;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.BlogQuery;
import top.afanee.blog.service.BlogService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {


    @Autowired
    private BlogMapper blogMapper;
    
    @Override
    public PageResult<Blog> findBlogByPage(BlogQuery blogQuery) {
        Page<Blog> page = new Page<>(blogQuery.getPageOn(), blogQuery.getPageSize());
        EntityWrapper<Blog> wrapper = new EntityWrapper<>();
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Blog> topicList = blogMapper.selectPage(page, wrapper);
        PageResult<Blog> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }

}
