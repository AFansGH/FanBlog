package top.afanee.blog.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import top.afanee.blog.entity.User;
import top.afanee.blog.mapper.UserMapper;
import top.afanee.blog.utils.Constants;



/**
 * 
 * @ClassName ShiroRealm
 * @Description TODO(认证与授权)
 * @author a2576
 * @Date 2018年12月3日 下午4:19:37
 * @version 1.0.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        User userQuery = new User();
        String username = upToken.getUsername();
        if(username.contains("@")){
            userQuery.setEmail(upToken.getUsername());
        }
        else{
            userQuery.setUserName(username);
        }
        User user = userMapper.selectOne(userQuery);
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (user.getAccountState() == 0) {
            throw new LockedAccountException("账号未激活");
        }
        
        // 构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        // 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = user.getUserName();
        // 2). credentials: 密码.
        Object credentials = user.getPassword();
        // 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = this.getName();
        // 4). 盐值.（709394）
        ByteSource credentialsSalt = ByteSource.Util.bytes(Constants.MD5_SALT);
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123";
        Object salt = ByteSource.Util.bytes(Constants.MD5_SALT);
        int hashIterations = 3;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
    
    /**
     * 前台用户并不做细粒度的权限控制
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    
    

}
