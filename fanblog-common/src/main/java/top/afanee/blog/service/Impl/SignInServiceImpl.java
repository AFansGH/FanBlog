package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.SignIn;
import top.afanee.blog.entity.User;
import top.afanee.blog.mapper.SignInMapper;
import top.afanee.blog.po.enums.DateTimePatternEnum;
import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.service.SignInService;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.DateUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements SignInService {
    
    @Autowired
    UserService userService;
    
    @Autowired
    SignInMapper signInMapper;

    @Override
    public SignInfo findSignInfoByUserId(Integer userId) {
        SignInfo signInfo = new SignInfo();
        Date curDate = new Date();
        EntityWrapper<SignIn> wrapper = new EntityWrapper<>();
        wrapper.where("sign_date = {0}",curDate);
        //查询当前日期签到了多少人
        signInfo.setTodaySignInCount(this.signInMapper.selectCount(wrapper));
        if(null == userId){
            //没登录,默认没签到，签到天数为0
            signInfo.setHaveSignInToday(Boolean.FALSE);
            signInfo.setUserSignInCount(0);
        }
        else{
            EntityWrapper<SignIn> wrapper2 = new EntityWrapper<>();
            wrapper2.where("user_id = {0}", userId);
            User user = this.userService.findUserByUserId(userId);
            /*signInfo.setMark(user.getMark());*/
            //查询该用户总共签到了多少天
            int userSignInCount = this.signInMapper.selectCount(wrapper);
            signInfo.setUserSignInCount(userSignInCount);
            wrapper2.where("sign_date = {0}", curDate);
            int todayIsSignIn = this.signInMapper.selectCount(wrapper);
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
