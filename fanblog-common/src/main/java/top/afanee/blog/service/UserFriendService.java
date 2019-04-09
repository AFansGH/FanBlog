package top.afanee.blog.service;

import top.afanee.blog.entity.UserFriend;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.UserFriendQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface UserFriendService extends IService<UserFriend> {

    Integer findFocusType4UserHome(UserFriendQuery userFriendQuery);

    Integer findCount(UserFriendQuery userFriendQuery);

    PageResult<UserFriend> findFriendListByPage(UserFriendQuery userFriendQuery);

    PageResult<UserFriend> findFansListByPage(UserFriendQuery userFriendQuery);

}
