package filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


@WebFilter(filterName = "Filter 2", urlPatterns = {"/*"})
public class Filter2_Session implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter2 - session begins");
        //将请求对象强制类型转换为http类型的请求
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        //获取请求对象的url
        String path=httpServletRequest.getRequestURI();
        JSONObject message=new JSONObject();
        //如果是登录界面则不需要进行session验证
        if (path.contains("/login.ctl")||path.contains("/index.html")||path.contains("/myapp")||path.contains(".js")) {
            //执行其他过滤器，如过滤器执行完毕，则执行原请求
            chain.doFilter(request,response);
            System.out.println("Filter 2 - session ends"+"\n");
        }else {
            //获取当前会话对象
            HttpSession session=((HttpServletRequest) request).getSession(false);
            //若session对象为空或currentuser属性为空则显示请重新登录
            if (session==null||session.getAttribute("currentUser")==null){
                message.put("message","请登录或重新登录");
                response.getWriter().println(message);
                return;
            }else {
                //若不为空则执行其他过滤器
                chain.doFilter(request,response);
                System.out.println("Filter 2 - session ends"+"\n");
            }
        }
    }

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return false;
    }
}
