package com.livrariamabuko.Livraria.Mabuko.security.filter;

import org.springframework.core.annotation.Order;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.livrariamabuko.Livraria.Mabuko.security.config.CpnConfigs;

import com.fasterxml.classmate.Filter;
import ch.qos.logback.core.spi.FilterReply;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrossOriginRequestFilter implements Filter{
    private static final String PREFLIGHT_REQUEST_METHOD = "OPTIONS";

	@Autowired
	private CpnConfigs configuration;

	public void init(FilterConfig filterConfig) throws ServletException {
		// nothing to do.
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		response.setHeader("Access-Control-Allow-Origin", configuration.getSecurity().getAllowedOrigin());
		response.setHeader("Access-Control-Allow-Credentials", "true");

		if (isPreFlightRequestFromAllowedOrigin(request)) {
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, PATCH, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Allow-Max-Age", "3600");
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
		// nothing to do.
	}

	private boolean isPreFlightRequestFromAllowedOrigin(HttpServletRequest request) {

		boolean trustedOrigin = configuration.getSecurity().getAllowedOrigin().equals(request.getHeader("Origin"));
		boolean preflightRequest = PREFLIGHT_REQUEST_METHOD.equals(request.getMethod());

		return trustedOrigin && preflightRequest;
	}

	
    

    @Override
    public boolean include(Object element) {
        // TODO Auto-generated method stub
        return false;
    }

   
    
}
