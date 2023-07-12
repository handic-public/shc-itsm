package com.shc.itsm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shc.itsm.board.service.QnaService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

	private final long MAX_AGE_SECS = 3600;
	private final String ALLOWED_METHOD_NAMES = "GET,POST,PUT,PATCH,DELETE,OPTIONS";
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("WebMvcConfig : " + registry.toString());
		// 모든경로에 대해 적용
		registry.addMapping("/**")
		// Origin이 http:localhost:3000에 대해
		.allowedOrigins("http://localhost:3000")
		// GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용
		.allowedMethods(ALLOWED_METHOD_NAMES.split(","))
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
		;
	}
}
