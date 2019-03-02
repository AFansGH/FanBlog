package top.afanee.blog.service;

import com.github.pagehelper.PageInfo;

import top.afanee.blog.entities.Ask;
import top.afanee.blog.po.model.Page;

public interface AskService {

    PageInfo<Ask> findAskByPage(Page page);

}
