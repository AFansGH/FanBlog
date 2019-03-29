package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Ask;
import top.afanee.blog.mapper.AskMapper;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.AskQuery;
import top.afanee.blog.service.AskService;

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
public class AskServiceImpl extends ServiceImpl<AskMapper, Ask> implements AskService {
    
    @Autowired
    private AskMapper AskMapper;
    
    @Override
    public PageResult<Ask> findAskByPage(AskQuery askQuery) {
        Page<Ask> page = new Page<>(askQuery.getPageOn(), askQuery.getPageSize());
        EntityWrapper<Ask> wrapper = new EntityWrapper<>();
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Ask> topicList = AskMapper.selectPage(page, wrapper);
        PageResult<Ask> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }


}
