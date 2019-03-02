package top.afanee.blog.service;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Blog;
import top.afanee.blog.po.model.Page;

public interface BlogService {

    PageInfo<Blog> findBlogByPage(Page page);

}
