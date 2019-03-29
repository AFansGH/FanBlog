package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Blog;
import top.afanee.blog.mapper.BlogMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.BlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;

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

    @Override
    public PageInfo<Blog> findBlogByPage(Page page) {
        // TODO Auto-generated method stub
        return null;
    }

}
