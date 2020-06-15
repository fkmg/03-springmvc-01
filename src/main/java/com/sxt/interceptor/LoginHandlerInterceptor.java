package com.sxt.interceptor;

import com.sxt.annotion.LoginAnnontion;
import com.sxt.bean.User;
import com.sxt.jedis.JedisClient;
import com.sxt.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(LoginHandlerInterceptor.class);

    @Autowired
    private JedisClient jedisClient;

    /**
     * 在控制器方法调用前执行
     * 返回值为是否中断
     * true,表示继续执行（下一个拦截器或处理器）
     * false则会中断后续的所有操作，所以我们需要使用response来继续响应后续请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        System.out.println(method.getName());
        MethodParameter[] parameters = handlerMethod.getMethodParameters();
        if(parameters !=null){
            System.out.println(StringUtils.join(parameters,"++++++"));
        }
        MethodParameter returnType = handlerMethod.getReturnType();
        System.out.println(returnType.getClass().getName());
        System.out.println(returnType.getParameterName());
        //2、获取方法所在的类Class对象
        Class<?> parent = method.getDeclaringClass();
        //3、判断所在类中是否有登陆的注解
        if(parent != null){
            LoginAnnontion parentAnnotation = parent.getAnnotation(LoginAnnontion.class);
            if(parentAnnotation != null){
                //将请求转发给判断登陆方法
                return judageLogin(request,response,handler);
            }
        }
        //判断此方法中是否有此注解
        LoginAnnontion annotation = method.getAnnotation(LoginAnnontion.class);
        if(annotation != null){
            //将请求转发给判断登陆方法
            return judageLogin(request,response,handler);
        }
        //放行
        log.debug("此方法不用登陆");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean judageLogin(HttpServletRequest request, HttpServletResponse response,Object o) throws IOException {

        Class<?> method = o.getClass();
        Date now = new Date();

        //获取用户的请求ip
        String addr = request.getRemoteAddr();
        int port = request.getRemotePort();
        //判断用户是否登录

        //获取cookie信息
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length>0){
            for(int i=0; i< cookies.length;i++){
               if("loginuser".equals(cookies[i].getName())) {
                   cookie = cookies[i];
                   break;
               }
            }
        }
        if(cookie !=null){
            User users = JsonUtils.jsonToPojo(cookie.getValue(), User.class);
            if(users != null){
                String loginuser = jedisClient.get(users.getUsername());
                //String loginuser = null;
                if(StringUtils.isNotBlank(loginuser)){
                    byte[] bytes = Base64.decodeBase64(loginuser.getBytes());
                    String str = new String(bytes, "utf-8");
                    User user = JsonUtils.jsonToPojo(str, User.class);
                    log.debug("["+DateFormatUtils.format(now,"yyyy/MM/dd HH:mm:ss")+"]"+user.getUsername()+"访问了"+method.getName());
                    return true;
                }
            }
        }


        //重定向让用户去登陆
        log.debug("["+DateFormatUtils.format(now,"yyyy/MM/dd HH:mm:ss")+"]"+addr+"/"+port+"访问了"+method.getName());
        response.sendRedirect("/springmvc/user/toLoginView");
        return false;
    }
}
