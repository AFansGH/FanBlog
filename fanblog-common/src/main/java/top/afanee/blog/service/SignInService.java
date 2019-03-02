package top.afanee.blog.service;

import top.afanee.blog.po.model.SignInfo;

public interface SignInService {

    SignInfo findSignInfoByUserid(Integer userid);

}
