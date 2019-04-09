package top.afanee.blog.po.model;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;


public class PageResult <T>{
    private Page<T> page;
    
    private List<T> list;
    
    public PageResult() {
    
    }
    
    public PageResult(Page<T> page, List<T> list) {
        this.page = page;
        this.list = list;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult [page=" + page + ", list=" + list + "]";
    }
    
    
    
}

