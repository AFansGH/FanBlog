package top.afanee.blog.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.afanee.blog.entities.SignIn;
import top.afanee.blog.entities.User;
import top.afanee.blog.mappers.SignInMapper;
import top.afanee.blog.po.enums.DateTimePatternEnum;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.service.SignInService;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.DateUtil;

@Service
public class SignInServiceImpl implements SignInService{
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SignInMapper signInMapper;
    
    @Override
    public SignInfo findSignInfoByUserid(Integer userId) {
       
        SignInfo signInfo = new SignInfo();
        SignIn signIn = new SignIn();
        Date curDate = new Date();
        
        String format = DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
        signIn.setSignDate(DateUtil.parse(format, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
        //今日签到人数
        int selectCount = signInMapper.selectCount(signIn);
        signInfo.setTodaySignInCount(selectCount);
        if(null == userId){
            //没登录,默认没签到，签到天数为0
            signInfo.setHaveSignInToday(Boolean.FALSE);
            signInfo.setUserSignInCount(0);
        }
        else{
            signIn.setUserId(userId);
            signIn.setSignDate(null);
            User user = this.userService.findUserByUserId(userId);
            signInfo.setMark(user.getMark());
            //查询该用户总共签到了多少天
            int userSignInCount = this.signInMapper.selectCount(signIn);
            signInfo.setUserSignInCount(userSignInCount);
            int todayIsSignIn = this.signInMapper.selectCount(signIn);
            if(todayIsSignIn == 0){
                //查询该用户当天是否签到
                signInfo.setHaveSignInToday(Boolean.FALSE);
            }
            else{
                signInfo.setHaveSignInToday(Boolean.TRUE);
            }
            signInfo.setCurDate(DateUtil.format(curDate, DateTimePatternEnum.MM_POINT_DD.getPattern()));
            signInfo.setCurYear(DateUtil.format(curDate, DateTimePatternEnum.YYYY.getPattern()));
            signInfo.setCurMonth(DateUtil.format(curDate, DateTimePatternEnum.MM.getPattern()));
            signInfo.setCurDay(DateUtil.format(curDate, DateTimePatternEnum.dd.getPattern()));
            
        }
        return signInfo;
    }

}
