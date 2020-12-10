package edu.guat.config;

import edu.guat.interceptor.LoginInterceptor;
import edu.guat.utils.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringToDateConverter stringToDateConverter;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册登录拦截器
        InterceptorRegistration loginInterceptorRegistration = registry.addInterceptor(loginInterceptor);
        //拦截所有请求
        loginInterceptorRegistration.addPathPatterns("/**");
        //放行以下资源
        loginInterceptorRegistration.excludePathPatterns("/");
        loginInterceptorRegistration.excludePathPatterns("/js/**");
        loginInterceptorRegistration.excludePathPatterns("/css/**");
        loginInterceptorRegistration.excludePathPatterns("/fonts/**");
        loginInterceptorRegistration.excludePathPatterns("/plugins/**");
        loginInterceptorRegistration.excludePathPatterns("/lib/**");
        loginInterceptorRegistration.excludePathPatterns("/img/**");
        loginInterceptorRegistration.excludePathPatterns("/user/login");
        loginInterceptorRegistration.excludePathPatterns("/user/register");
        loginInterceptorRegistration.excludePathPatterns("/user/logout");
        loginInterceptorRegistration.excludePathPatterns("/user/resetpwd");
    }

    /**
     * 配置静态资源不被拦截
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        /**
         * 解决图片上传无法立即显示，而需要重启才能访问的问题。
         * 这是因为对服务器的保护措施导致的，服务器不能对外部暴露真实的资源路径，需要配置虚拟路径映射访问。
         */
        registry.addResourceHandler("/img/pet/**").addResourceLocations("file:D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/pet/");
        registry.addResourceHandler("/img/keeper/**").addResourceLocations("file:D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/keeper/");
        registry.addResourceHandler("/img/pay/**").addResourceLocations("file:D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/pay/");
    }
}
