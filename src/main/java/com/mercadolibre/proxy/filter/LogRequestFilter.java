package com.mercadolibre.proxy.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.mercadolibre.proxy.LogTypeEnum;
import com.mercadolibre.proxy.entity.LogEntity;
import com.mercadolibre.proxy.repository.LogRepository;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

@Component
public class LogRequestFilter extends ZuulFilter {
	
    private static Logger log = LoggerFactory.getLogger(LogRequestFilter.class);
    
    @Autowired
    private LogRepository logRepository;
    
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 2;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = new HttpServletRequestWrapper(ctx.getRequest());
        String requestData = null;
        
        try {
            if (request.getContentLength() > 0) {
                requestData = CharStreams.toString(request.getReader());
            }
        } catch (Exception e) {
            log.error("Error parsing request", e);
        }
        
        String line = String.format("Request, %s,%s,%s \r\n", 
                 request.getRequestURL(),
                request.getMethod(), 
                requestData);
        
        LogEntity log = new LogEntity();
        log.setCreatedAt(new Date());
        log.setMessage(line);
        log.setTypeLog(LogTypeEnum.REQUEST.name());
        
        logRepository.save(log);
        
        return null;
    }
}