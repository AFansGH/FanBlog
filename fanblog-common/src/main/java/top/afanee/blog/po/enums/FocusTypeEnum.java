package top.afanee.blog.po.enums;

public enum FocusTypeEnum {
    
    ONESELF(0,"自身"), 
    UNCONCERNED(1, "未关注"),
    CONCERNED(2, "已关注");
    private Integer type;
    private String desc;
    
    private FocusTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static FocusTypeEnum getFocusTypeEnumByValue(Integer type){
        if(type == null){
            return null;
        }
        for(FocusTypeEnum ft : FocusTypeEnum.values()){
            if(ft.getType() == type){
                return ft;
            }
        }
        return null;
    }
}
