package top.afanee.blog.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import top.afanee.blog.cache.CategoryCache;
import top.afanee.blog.utils.SpringContextUtil;


public class BloContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        //初始化SpringContextUtil的context
        ServletContext context = event.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        SpringContextUtil.setContext(ctx);
        //获得类别缓存 后刷新缓存
        CategoryCache categoryCache = (CategoryCache) ctx.getBean("categoryCache");
        categoryCache.refreshCategoryCache();
      
    }

}