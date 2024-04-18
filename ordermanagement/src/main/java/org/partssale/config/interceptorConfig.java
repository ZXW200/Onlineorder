package org.partssale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class interceptorConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");

        // 基于url跨域
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 跨域配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");

        corsConfiguration.setAllowCredentials(true);


        // 向source中注册new出来的配置，并设置任意url都要进行跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);

    }

}
