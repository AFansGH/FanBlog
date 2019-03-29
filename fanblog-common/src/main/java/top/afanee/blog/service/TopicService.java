package top.afanee.blog.service;

import top.afanee.blog.entity.Topic;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.TopicQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface TopicService extends IService<Topic> {

    PageResult<Topic> findTopicByPage(TopicQuery topicQuery);

}
