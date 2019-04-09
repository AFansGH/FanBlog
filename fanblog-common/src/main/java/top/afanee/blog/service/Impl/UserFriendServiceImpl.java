package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.UserFriend;
import top.afanee.blog.mapper.UserFriendMapper;
import top.afanee.blog.po.enums.FocusTypeEnum;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.UserFriendQuery;
import top.afanee.blog.service.UserFriendService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

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
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {
    
    @Autowired
    private UserFriendMapper userFriendMapper;
    
    @Override
    public Integer findFocusType4UserHome(UserFriendQuery userFriend) {
        //相等表示自己
        if(userFriend.getUserId()!= null && userFriend.getUserId().equals(userFriend.getFriendUserId())){
            return FocusTypeEnum.ONESELF.getType();
        }
        //查到 已经关注了
        Integer findCount = this.findCount(userFriend);
        if(findCount != 0){
            return FocusTypeEnum.CONCERNED.getType();
        }
        //没查到 未关注
        return FocusTypeEnum.UNCONCERNED.getType();
    }

    @Override
    public Integer findCount(UserFriendQuery userFriend) {
        
        EntityWrapper<UserFriend> wrapper = new EntityWrapper<>();
        wrapper.where("user_id = {0}",userFriend.getUserId()).and("friend_user_id = {0}",userFriend.getFriendUserId());
        Integer count = userFriendMapper.selectCount(wrapper);
        return count;
    }
    
    /**
     * 查询用户关注，用户id 为条件
     */
    @Override
    public PageResult<UserFriend> findFriendListByPage(UserFriendQuery userFriendQuery) {
        Page<UserFriend> page = new Page<>(userFriendQuery.getPageOn(), userFriendQuery.getPageSize());
        EntityWrapper<UserFriend> wrapper = new EntityWrapper<>();
        wrapper.where("user_id = {0}", userFriendQuery.getUserId());
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<UserFriend> topicList = userFriendMapper.selectPage(page, wrapper);
        PageResult<UserFriend> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }
    
    /**
     * 查询用户的粉丝，friend_id为条件
     */
    @Override
    public PageResult<UserFriend> findFansListByPage(UserFriendQuery userFriendQuery) {
        Page<UserFriend> page = new Page<>(userFriendQuery.getPageOn(), userFriendQuery.getPageSize());
        EntityWrapper<UserFriend> wrapper = new EntityWrapper<>();
        wrapper.where("friend_user_id = {0}", userFriendQuery.getFriendUserId());
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<UserFriend> topicList = userFriendMapper.selectPage(page, wrapper);
        PageResult<UserFriend> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }

}
