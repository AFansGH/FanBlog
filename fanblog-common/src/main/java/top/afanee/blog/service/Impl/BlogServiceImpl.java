package top.afanee.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.Blog;
import top.afanee.blog.mappers.BlogMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{
    
    @Autowired
    private BlogMapper blogMapper;
    
    
    @Override
    public PageInfo<Blog> findBlogByPage(Page page) {
        
        Example example = new Example(Blog.class);
        example.orderBy("createTime").desc();
        PageHelper.startPage(page.getPageOn(), page.getPageSize());
        List<Blog> selectAll = blogMapper.selectByExample(example);
        PageInfo<Blog> pageInfo = new PageInfo<>(selectAll);
        return pageInfo;
    }

}
