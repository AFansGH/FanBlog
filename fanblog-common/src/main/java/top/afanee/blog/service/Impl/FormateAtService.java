package top.afanee.blog.service.Impl;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.afanee.blog.entity.User;
import top.afanee.blog.service.UserService;
import top.afanee.blog.utils.Constants;


@Component
public class FormateAtService {
    
    @Autowired
    private UserService userService;
    
    private static String userUrl = Constants.DOMAIN + "/user/";
    //private static Pattern referer_pattern = Pattern.compile("@.+?[\\s:]");@([^@\\s\\:\\;\\,\\\\.\\<\\?\\？\\{\\}\\&]{1,})
    private static Pattern referer_pattern = Pattern.compile("@([^@\\s\\:\\;\\,\\\\.\\<\\?\\？\\{\\}\\&]{1,})");//
    
    /**
     * 
     * @Description TODO(生成引用链接)
     * @param msg 页面内容
     * @param userIds 页面中提到的用户id集合
     * @return
     */
    public String generateRefererLinks(String msg, Set<Integer> userIds){
        StringBuilder html = new StringBuilder();
        int lastIdx = 0;
        Matcher matcher = referer_pattern.matcher(msg);
        while (matcher.find()) {
            String origion_str = matcher.group();
            String userName = origion_str.substring(1, origion_str.length()).trim();
            html.append(msg.substring(lastIdx, matcher.start()));
            User user = this.userService.findUserByUserName(userName);
            if(user == null){
                user = this.userService.findUserByEmail(userName);
            }
            if(null != user){
                html.append("<a href=\"" + userUrl + user.getUserId() + "\"  class=\"referer\"  target=\"_blank\">@");
                html.append(userName.trim());
                html.append("</a> ");
                userIds.add(user.getUserId());
            }
            else{
                html.append(origion_str);
            }
            lastIdx = matcher.end();
        }
        html.append(msg.substring(lastIdx));
        return html.toString();
    }
}

