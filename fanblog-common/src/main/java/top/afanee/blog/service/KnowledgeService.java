package top.afanee.blog.service;

import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.KnowledgeQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface KnowledgeService extends IService<Knowledge> {

    PageResult<Knowledge> findKnowledgeByPage(KnowledgeQuery knowledgeQuery);


}
