package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Ask;
import top.afanee.blog.mapper.AskMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.AskService;
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
public class AskServiceImpl extends ServiceImpl<AskMapper, Ask> implements AskService {

    @Override
    public PageInfo<Ask> findAskByPage(Page page) {
        // TODO Auto-generated method stub
        return null;
    }

}
