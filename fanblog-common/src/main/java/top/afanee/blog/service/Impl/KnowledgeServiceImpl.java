package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.mapper.KnowledgeMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.KnowledgeService;
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
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {

    @Override
    public PageInfo<Knowledge> findKnowledgeByPage(Page page) {
        // TODO Auto-generated method stub
        return null;
    }

}
