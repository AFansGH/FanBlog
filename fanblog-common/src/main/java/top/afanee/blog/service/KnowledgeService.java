package top.afanee.blog.service;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Knowledge;
import top.afanee.blog.po.model.Page;

public interface KnowledgeService {

    PageInfo<Knowledge> findKnowledgeByPage(Page page);

}
