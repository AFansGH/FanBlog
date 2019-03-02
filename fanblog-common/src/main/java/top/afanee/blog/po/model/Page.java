package top.afanee.blog.po.model;


public class Page {
    
    private Integer pageOn;
    private Integer pageSize;
    
    public Page(){
        
    }
    
    public Page(Integer pageOn, Integer pageSize){
        this.pageOn = pageOn;
        this.pageSize = pageSize;
    }
    
    public Integer getPageOn() {
        return pageOn;
    }
    
    public void setPageOn(Integer pageOn) {
        this.pageOn = pageOn;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
}
