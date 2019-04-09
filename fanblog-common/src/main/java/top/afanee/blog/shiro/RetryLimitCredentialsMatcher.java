package top.afanee.blog.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;



/**
 * 
 * @ClassName RetryLimitCredentialsMatcher
 * @Description TODO(使用原子操作类和登陆的用户名缓存到cache中，继承shiro的密码验证类，在密码验证之前拿到缓存中的用户登陆次数，自增后判断是否大于最多次数限制
 * 缓存使用构造函数在xml文件中注入，指定缓存管理者与缓存的名称
 * )
 * @author Fan
 * @Date 2019年3月29日 上午11:14:13
 * @version 1.0.0
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    
    //原子操作类AtomicInteger，并发安全
    private Cache<String, AtomicInteger> lgoinRetryCache;
    private int maxRetryCount = 5;
    private String lgoinRetryCacheName;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    
    //构造函数，在xml配置文件中，配置了cacheManager和CacheName
    public RetryLimitCredentialsMatcher(CacheManager cacheManager, String RetryCacheName) {
        lgoinRetryCacheName = RetryCacheName;
        lgoinRetryCache = cacheManager.getCache(lgoinRetryCacheName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal(); 
        AtomicInteger retryCount = lgoinRetryCache.get(username);
        //缓存中并没有该用户，则加入该用户且登陆次数为0
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            lgoinRetryCache.put(username, retryCount);
        }
        //缓存中已经有该用户了，自增判断次数超过5。抛出异常
        if (retryCount.incrementAndGet() > maxRetryCount) {
            throw new ExcessiveAttemptsException(
                    username + "登陆失败次数过多，请稍后再试！");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) { 
            // clear retry data
            lgoinRetryCache.remove(username);
        }
        return matches;
    }
}