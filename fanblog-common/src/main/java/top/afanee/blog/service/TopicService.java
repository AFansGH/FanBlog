package top.afanee.blog.service;

import top.afanee.blog.entity.Topic;
import top.afanee.blog.po.model.Page;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface TopicService extends IService<Topic> {

    PageInfo<Topic> findTopicByPage(Page page);

}
