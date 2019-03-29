package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Topic;
import top.afanee.blog.mapper.TopicMapper;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.TopicQuery;
import top.afanee.blog.service.TopicService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public PageResult<Topic> findTopicByPage(TopicQuery topicQuery) {
        Page<Topic> page = new Page<>(topicQuery.getPageOn(), topicQuery.getPageSize());
        EntityWrapper<Topic> wrapper = new EntityWrapper<>();
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Topic> topicList = topicMapper.selectPage(page, wrapper);
        PageResult<Topic> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }

}
