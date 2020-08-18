package com.mercadolibre.proxy.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

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

@Component
public class LogResponseFilter extends ZuulFilter {
	
    private static Logger logger = LoggerFactory.getLogger(LogResponseFilter.class);
    
    @Autowired
    private LogRepository logRepository;
    
    @Override
    public String filterType() {
        return "post";
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
        final InputStream responseDataStream = ctx.getResponseDataStream();
        try {
            final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            ctx.setResponseBody(responseData);
            LogEntity log = new LogEntity();
            log.setCreatedAt(new Date());
            log.setMessage(responseData);
            log.setTypeLog(LogTypeEnum.RESPONSE.name());
            logRepository.save(log);
        } catch (IOException e) {
            logger.error("Error reading body", e);
        }
        return null;
    }
}
