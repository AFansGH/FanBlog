package top.afanee.blog.po.model;


public class SignInfo {
    //今天是否已经签到
    private boolean haveSignInToday;
    
    //用户签到了的天数总和
    private Integer userSignInCount = 0;
    
    //今日签到人数
    private Integer todaySignInCount;
    
    //当前日期
    private String curDate;
    
    //当前年份
    private String curYear;
    
    //当前月份
    private String curMonth;
    
    //当前几号
    private String curDay;
    
    

    public String getCurYear() {
        return curYear;
    }

    public void setCurYear(String curYear) {
        this.curYear = curYear;
    }

    public String getCurMonth() {
        return curMonth;
    }

    public void setCurMonth(String curMonth) {
        this.curMonth = curMonth;
    }

    public String getCurDay() {
        return curDay;
    }

    public void setCurDay(String curDay) {
        this.curDay = curDay;
    }

    //用户积分
    private Integer mark = 0;
    
    
    
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public boolean isHaveSignInToday() {
        return haveSignInToday;
    }

    public void setHaveSignInToday(boolean haveSignInToday) {
        this.haveSignInToday = haveSignInToday;
    }

    public Integer getUserSignInCount() {
        return userSignInCount;
    }

    public void setUserSignInCount(Integer userSignInCount) {
        this.userSignInCount = userSignInCount;
    }

    public Integer getTodaySignInCount() {
        return todaySignInCount;
    }

    public void setTodaySignInCount(Integer todaySignInCount) {
        this.todaySignInCount = todaySignInCount;
    }

    @Override
    public String toString() {
        return "SignInfo [haveSignInToday=" + haveSignInToday + ", userSignInCount=" + userSignInCount
                + ", todaySignInCount=" + todaySignInCount + ", curDate=" + curDate + ", curYear=" + curYear
                + ", curMonth=" + curMonth + ", curDay=" + curDay + ", mark=" + mark + "]";
    }

    
    
    
}
