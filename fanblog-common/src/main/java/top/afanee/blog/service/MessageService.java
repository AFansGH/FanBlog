package top.afanee.blog.service;

import top.afanee.blog.entity.Message;
import top.afanee.blog.po.model.MessageParams;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-04-02
 */
public interface MessageService extends IService<Message> {

    void createMessage(MessageParams messageParams);

}
