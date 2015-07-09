package com.griddynamics.devschool.shop.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Sergey Korneev
 */
public class LogFilter implements Filter {
    private static String logKey = "sessionId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        MDC.put(logKey, ((HttpServletRequest)servletRequest).getRequestedSessionId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.remove(logKey);
    }
}
