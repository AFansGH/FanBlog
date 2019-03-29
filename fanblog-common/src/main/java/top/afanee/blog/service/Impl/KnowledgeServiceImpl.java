package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.mapper.KnowledgeMapper;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.KnowledgeQuery;
import top.afanee.blog.service.KnowledgeService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {
    
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    
    @Override
    public PageResult<Knowledge> findKnowledgeByPage(KnowledgeQuery knowledgeQuery) {
        Page<Knowledge> page = new Page<>(knowledgeQuery.getPageOn(), knowledgeQuery.getPageSize());
        EntityWrapper<Knowledge> wrapper = new EntityWrapper<>();
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Knowledge> topicList = knowledgeMapper.selectPage(page, wrapper);
        PageResult<Knowledge> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }


}
