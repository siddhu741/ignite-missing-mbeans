package com.example.ignitemissingmbeans;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean({MBeanServer.class })
public class IgniteMonitorConfiguration {

    @Bean
    public MBeanServer mBeanServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }
    @Bean
    public IgniteMBeanService igniteMBeanService(MBeanServer mBeanServer) {
        return new IgniteMBeanService(mBeanServer);
    }
}