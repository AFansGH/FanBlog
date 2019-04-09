package top.afanee.blog.mapper;

import top.afanee.blog.entity.Blog;
import top.afanee.blog.po.query.UpdateQuery4ArticleCount;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface BlogMapper extends BaseMapper<Blog> {
    
    /**
     * 
     * @Description TODO(更新博客的下载、收藏、喜欢次数)
     * @param updateQuery4ArticleCount
     * @return
     */
    Integer updateInfoCount(UpdateQuery4ArticleCount updateQuery4ArticleCount);

}
