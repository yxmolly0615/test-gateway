package com.molly.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
/*测试
http://localhost:2299/loadbalance-service/lbt/port?microserviceName=bookingcar-service&userid=1
 */
@Component
public class BookingCarPreFilter extends ZuulFilter {
    protected static final Logger logger=Logger.getLogger(BookingCarPreFilter.class.getName());

    /**
     * 过滤类型:pre路由之前,routing路由之时,post路由之后,error发生错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**通过int值自定义过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }
    /**
     * 是否需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //添加自定义判断
        return true;
    }

    /**
     * 过滤规则
     * @return
     */
    @Override
    public Object run() {
        RequestContext rc=RequestContext.getCurrentContext();//获取请求上下文
        HttpServletRequest req=rc.getRequest();
        Object userid=req.getParameter("userid");
        if(userid==null) {
            logger.info("userid is null");
            rc.setSendZuulResponse(false);//默认true,指定未通过过滤
            rc.setResponseStatusCode(401);
            try {
                rc.getResponse().getWriter().write("userid conot be null");
            } catch (IOException e) {
            }
            return null;
        }
        //鉴权不通过
        if(!(auth(userid.toString()))){
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(402);
            try {
                rc.getResponse().getWriter().write("userid don't have authorization");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        //鉴权通过后
        String accesstoken =encode(userid.toString());
        logger.info(String.format("pre routing,token={%s}",accesstoken));
        rc.addZuulRequestHeader("Authorization",accesstoken);
        logger.info("added token to zuule request");
        return null;
    }

    private String encode(String userid) {
        byte[] encodedBytes= Base64.getEncoder().encode(userid.getBytes());
        return new String(encodedBytes);
    }

    /**
     * 自定义鉴权逻辑
     * @param toString
     * @return
     */
    private boolean auth(String toString) {
        return true;
    }
}
