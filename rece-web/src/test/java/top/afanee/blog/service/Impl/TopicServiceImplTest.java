package top.afanee.blog.service.Impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.plugins.Page;

import top.afanee.blog.entity.Topic;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.po.query.TopicQuery;
import top.afanee.blog.service.SignInService;
import top.afanee.blog.service.TopicService;


public class TopicServiceImplTest {

    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-beans.xml");
    private TopicService tpicService = iocContainer.getBean(TopicService.class);

    
    @Test
    public void testFindSignInfoByUserid() {
        Object obj = tpicService.findTopicByPage(new TopicQuery());
        System.out.println(obj);
    }
}
