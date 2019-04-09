package top.afanee.blog.po.query;


public class BaseQuery {

    private int pageOn = 1;
    private int pageSize = 10;

    public int getPageOn() {
        return pageOn;
    }

    public void setPageOn(int pageOn) {
        this.pageOn = pageOn;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
