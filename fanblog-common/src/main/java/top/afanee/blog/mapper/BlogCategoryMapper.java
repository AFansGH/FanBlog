package top.afanee.blog.mapper;

import top.afanee.blog.entity.BlogCategory;
import top.afanee.blog.po.query.BlogCategoryQuery;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {
    
    //查询博客分类的同时还封装了每个博客分类下的博客数量
    List<BlogCategory> selectList2BlogCategoryQuery(BlogCategoryQuery blogCategoryQuery);

}
