package top.afanee.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.Ask;
import top.afanee.blog.mappers.AskMapper;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.AskService;

@Service
public class AskServiceImpl implements AskService{
    
    @Autowired
    private AskMapper askMapper;
    
    @Override
    public PageInfo<Ask> findAskByPage(Page page) {
        
        Example example = new Example(Ask.class);
        example.orderBy("createTime").desc();
        PageHelper.startPage(page.getPageOn(), page.getPageSize());
        List<Ask> selectAll = askMapper.selectByExample(example);
        PageInfo<Ask> pageInfo = new PageInfo<>(selectAll);
        return pageInfo;
    }

}
