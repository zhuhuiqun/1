package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter 0", urlPatterns = {"/*"})
public class Filter0_Encoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 0 - encoding begins");
        //将请求对象进行强制类型转换成为http类型的请求和响应对象
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取请求对象的url
        String path= request.getRequestURI();
        //获取请求的方法
        String method=request.getMethod();
        if (path.contains("/myapp")){
            System.out.println("不设置字符编码格式");
        }else {
            System.out.println(method);
            //设置响应对象的字符编码格式为utf8
            response.setCharacterEncoding("UTF-8");
            System.out.println("设置响应编码");
            //如果请求方法是post或者put方法，设置请求对象字符编码格式也为utf8
            if (("POST-PUT").contains(method)){
                request.setCharacterEncoding("UTF-8");
            }
        }
        //执行其他过滤器，如过滤器执行完毕，则执行原请求
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 0 - encoding ends"+"\n");
    }

}

