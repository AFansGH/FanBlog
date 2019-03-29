package top.afanee.blog.po.model;


public class Page {
    
    private Integer pageNum;
    private Integer pageSize = 6;
    
    public Page(){
        
    }
    
    public Page(Integer pageNum, Integer pageSize){
        this.setPageNum(pageNum);
        this.pageSize = pageSize;
    }
    
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
    }
    
    
}
