package top.afanee.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.Topic;
import top.afanee.blog.mappers.TopicMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{
    
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public PageInfo<Topic> findTopicByPage(Page page) {
        
        Example example = new Example(Topic.class);
        example.orderBy("essence").desc()
        .orderBy("grade").desc()
        .orderBy("lastCommentTime").desc()
        .orderBy("createTime").desc();
        PageHelper.startPage(page.getPageOn(), page.getPageSize());
        List<Topic> selectAll = topicMapper.selectByExample(example);
        PageInfo<Topic> pageInfo = new PageInfo<>(selectAll);
        return pageInfo;
    }

}
