package top.afanee.blog.service;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Topic;
import top.afanee.blog.po.model.Page;

public interface TopicService {

    PageInfo<Topic> findTopicByPage(Page page);

}
