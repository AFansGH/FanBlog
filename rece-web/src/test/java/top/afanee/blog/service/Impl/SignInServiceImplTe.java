package top.afanee.blog.service.Impl;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import top.afanee.blog.po.model.SignInfo;
import top.afanee.blog.service.SignInService;


public class SignInServiceImplTe {

    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-beans.xml");
    private SignInService employeeService = iocContainer.getBean(SignInService.class);

    
    @Test
    public void testFindSignInfoByUserid() {
        SignInfo signInfo = employeeService.findSignInfoByUserId(1);
        System.out.println(signInfo);
    }
}
