package com.liawan.config;

import com.liawan.web.interceptor.IndexInterceptor;
import com.liawan.web.interceptor.InstallInterceptor;
import com.liawan.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @author :
 * @createDate : 2018年9月10日
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginAuthenticator;
    @Autowired
    private IndexInterceptor indexInterceptor;
    @Autowired
    private InstallInterceptor installInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthenticator).addPathPatterns("/admin/**").excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/getLogin");
        registry.addInterceptor(indexInterceptor);
        registry.addInterceptor(installInterceptor).addPathPatterns("/**").excludePathPatterns("/install")
                .excludePathPatterns("/install/execute").excludePathPatterns("/js/**").excludePathPatterns("/css/**")
                .excludePathPatterns("/img/**").excludePathPatterns("/plugins/**");
    }

    /**
     * 释放静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 通过addResourceHandler添加资源映射路径，然后通过addResourceLocations来指定路径。可以访问自定义upload文件夹
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/")
                .addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/liawan/upload/");
        registry.addResourceHandler("/source/**").addResourceLocations("classpath:/templates/themes/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//		// 单个文件最大 KB,MB
//		factory.setMaxFileSize("10240KB");
//		// 设置总上传数据总大小
//		factory.setMaxRequestSize("102400KB");
        // TODO 报错，做一下修改
        // 单个文件最大 KB,MB
        factory.setMaxFileSize(DataSize.parse("10240KB"));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("102400KB"));
        return factory.createMultipartConfig();
    }
}
