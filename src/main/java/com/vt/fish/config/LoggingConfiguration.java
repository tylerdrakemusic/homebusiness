package com.vt.fish.config;

import com.vt.fish.logging.LoggingAspect;
import com.vt.fish.logging.VibrantLogger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggingConfiguration {

    @Bean
    public VibrantLogger vibrantLogger() {return new VibrantLogger();}

    @Bean
    public LoggingAspect loggingAspect(VibrantLogger vibrantLogger,
                                       Optional<HttpServletRequest> httpServletRequestOptional,
                                       BeanFactory beanFactory){
        return new LoggingAspect(vibrantLogger,httpServletRequestOptional, beanFactory);
    }
}
