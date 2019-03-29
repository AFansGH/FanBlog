package top.afanee.blog.service;

import top.afanee.blog.entity.SignIn;
import top.afanee.blog.po.model.SignInfo;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
public interface SignInService extends IService<SignIn> {

    SignInfo findSignInfoByUserId(Integer userid);

}
