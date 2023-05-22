package io.bootify.delivery_management_system.filter;

import com.alibaba.fastjson.JSON;
import io.bootify.delivery_management_system.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 *
 */
@Slf4j
@WebFilter(filterName="loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //url route matcher
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        long id=Thread.currentThread().getId();
        log.info("Thread Id: {}", id);
        String requestURI=request.getRequestURI();
        log.info("Intercept request: {}",request.getRequestURI());
        //pass urls
        String [] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/**"
        };
        boolean check= check(urls,requestURI);
        if(check){
            log.info("This request {} not require process",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee") != null){
            log.info("User Login，User Id is：{}",request.getSession().getAttribute("employee"));
           filterChain.doFilter(request,response);
           return;
        }
        log.info("User has not Login");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

//        log.info("Intercept request: {}",request.getRequestURI());
//        filterChain.doFilter(request,response);
    }

    /**
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
       for (String url:urls){
           boolean match=PATH_MATCHER.match(url,requestURI);
           if(match){
               return true;
           }
       }
       return false;
    }
}
