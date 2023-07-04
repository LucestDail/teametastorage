package com.teametastorage.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.teametastorage.domain.Member;

@SuppressWarnings("deprecation")
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (logger.isInfoEnabled()) {
    		logger.info("(" +request.getRemoteAddr() + " : " + request.getRemotePort()+ ") => " + request.getRequestURI() + " / Thread [" + Thread.currentThread().getId() + "] -> " +Thread.currentThread().getStackTrace()[1].getClassName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
        }
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try{
			Member sessionMember = (Member) request.getSession().getAttribute("member");
			if(Objects.isNull(sessionMember)) {
				modelAndView.setViewName("member/login.html");
			}else {
				super.postHandle(request, response, handler, modelAndView);
			}
		}catch(Exception e){
			if (logger.isInfoEnabled()) {
				logger.info("(" +request.getRemoteAddr() + " : " + request.getRemotePort()+ ") => " + request.getRequestURI() + " / Thread [" + Thread.currentThread().getId() + "] -> " +super.getClass().getCanonicalName() + " : crash");
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}