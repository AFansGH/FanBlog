package top.afanee.blog.service;

import top.afanee.blog.entity.Ask;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.AskQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface AskService extends IService<Ask> {

    PageResult<Ask> findAskByPage(AskQuery askQuery);


}
