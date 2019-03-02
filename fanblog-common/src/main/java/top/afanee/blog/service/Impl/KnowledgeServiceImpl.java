package top.afanee.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.afanee.blog.entities.Knowledge;
import top.afanee.blog.mappers.KnowledgeMapper;
import top.afanee.blog.po.enums.StatusEnum;
import top.afanee.blog.po.model.Page;
import top.afanee.blog.service.KnowledgeService;

@Service
public class KnowledgeServiceImpl implements KnowledgeService{
    
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    
    @Override
    public PageInfo<Knowledge> findKnowledgeByPage(Page page) {
        
        Knowledge knowledge = new Knowledge();
        knowledge.setStatus(StatusEnum.AUDIT.getType());
        
        Example example = new Example(Knowledge.class);
        example.orderBy("createTime").desc();
        PageHelper.startPage(page.getPageOn(), page.getPageSize());
        List<Knowledge> selectAll = knowledgeMapper.selectByExample(example);
        PageInfo<Knowledge> pageInfo = new PageInfo<>(selectAll);
        return pageInfo;
    }

}
