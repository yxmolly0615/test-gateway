package com.molly.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

/**测试
 * http://localhost:2299/loadbalance-service/lbt/port?microserviceName=bookingcar-service
 */
//@Component
public class BookingCarRoutingFilter extends ZuulFilter {
    @Value("${unauthorized.url.redirect:http://www.sina.com.cn}")
    private String urlRedirect;
    private static final Logger logger=Logger.getLogger(BookingCarRoutingFilter.class.getName());
    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }
    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }
    @Override
    public boolean shouldFilter() {
        //自定义判断
        return true;
    }
    /**
     * 自定义鉴权,通过到一个链接,不通过到另一个
     * @return
     */
    @Override
    public Object run() {
        logger.info("query interception");
        RequestContext rc=RequestContext.getCurrentContext();
        if(isAuthorizationRequest(rc)){
            // if the requested url is authorized, the route host is set to the requested one
            setRouteHost(rc);
        }else{
            try {
                // if the requested URL is not authorized, the route host is set to the urlRedirect value
                // the client will be redirected to the new host
                rc.setRouteHost(new URL(urlRedirect));
            } catch (MalformedURLException e) {
                logger.info("error happened when routing request");
            }
        }
        return null;
    }
    private boolean isAuthorizationRequest(RequestContext rc){
        //自定义过滤器
        HttpServletRequest req=rc.getRequest();
        String userid=req.getParameter("userid");
        if(userid==null){
            return false;
        }
        return true;
    }
    /**
     * zuul的请求上下文
     * @param rc
     */
    private void setRouteHost(RequestContext rc){
        String urls=rc.getRequest().getRequestURI().toString();
        try {//拼接:http://  localhost :port
            URL url=new URL(urls);
            String protocol=url.getProtocol();
            String rootHost=url.getHost();
            int port=url.getPort();
            String portS=(port>0?":"+port:"");
            rc.setRouteHost(new URL(protocol+"://"+rootHost+portS));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
