package top.afanee.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;

import top.afanee.blog.utils.Constants;

/**
 * 
 * @ClassName AuthorityFilter
 * @Description TODO(权限过滤器)
 * @author Fan
 * @Date 2019年3月30日 下午9:03:32
 * @version 1.0.0
 */
public class AuthorityFilter implements Filter {
    private static String realPath = null;
    private static final String[] ATTACHMENTTYPE = {"zip", "rar"};

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ServletContext servletContext = req.getSession().getServletContext();
        String req_uri = req.getRequestURI();
        String type = req_uri.substring(req_uri.lastIndexOf(".") + 1);
        if(ArrayUtils.contains(ATTACHMENTTYPE, type)){
            resp.sendRedirect(Constants.ERROR_404);
            return ;
        }
        if(realPath == null){
            realPath = getRealPath(req);
        }
        if(servletContext.getAttribute(Constants.REALPATH) == null){
            servletContext.setAttribute(Constants.REALPATH,realPath);
        }
        chain.doFilter(request, response);
    }
    
    private String getRealPath(HttpServletRequest request){
        String port = request.getServerPort() == 80 ? "": ":" + request.getServerPort();
        String realpath = "http://" + request.getServerName() + port + "/rece-web";
        return realpath;
    }
    
    public void destroy() {

    }
}
