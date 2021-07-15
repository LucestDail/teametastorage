package com.teametastorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teametastorage.interceptor.LogInterceptor;
import com.teametastorage.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		LoginInterceptor loginIntercepter = new LoginInterceptor();
		LogInterceptor logInterceptor = new LogInterceptor();
		registry.addInterceptor(logInterceptor).addPathPatterns("/**/**").excludePathPatterns();
		registry.addInterceptor(loginIntercepter).addPathPatterns("/**/**").excludePathPatterns("/");
	}
}