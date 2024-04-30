package com.ivo.mas;

import com.ivo.mas.service.MainService;
import com.ivo.mas.system.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    MainService mainService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(mainService)).addPathPatterns("/**").excludePathPatterns("/test/**","/readCardPic","/carNoRead");
    }

}
