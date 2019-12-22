package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;

@WebFilter(filterName = "Filter 1", urlPatterns = {"/*"})
public class Filter1_Date implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 1 - date begins");
        //将请求对象进行强制类型转换成为http类型的请求和响应对象
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //获取请求对象的url
        String path=request.getRequestURI();
        //显示当前时间
        Calendar cal = Calendar.getInstance();
        String time= cal.get(Calendar.YEAR)+"年"
                +(cal.get(Calendar.MONTH) + 1)+"月"
                +cal.get(Calendar.DATE)+"日"
                +cal.get(Calendar.HOUR_OF_DAY)+": "
                +cal.get(Calendar.MINUTE);
        System.out.println(path+" @ "+time);
        //执行其他过滤器
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 1 - date ends");
    }
}
