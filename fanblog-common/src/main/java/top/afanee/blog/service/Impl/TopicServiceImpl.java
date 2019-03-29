package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Topic;
import top.afanee.blog.mapper.TopicMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.TopicService;
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
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Override
    public PageInfo<Topic> findTopicByPage(Page page) {
        // TODO Auto-generated method stub
        return null;
    }

}
