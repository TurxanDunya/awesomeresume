package com.company.resume.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter(filterName = "JSPFileFilter", urlPatterns = {"*.jsp"})
public class JspFilter implements Filter {
    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain){

        HttpServletResponse res = (HttpServletResponse) response;

        try {
            res.sendRedirect("error?msg=not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
