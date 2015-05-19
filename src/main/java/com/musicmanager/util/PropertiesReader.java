package com.musicmanager.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesReader implements InitializingBean {

    @Autowired
    private Environment env;

    @Value("${BaseDirectory}")
    private String injectedProperty;

    public PropertiesReader() {
        super();
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("in afterPropertiesSet via @Value: " + injectedProperty);
        System.out.println("in afterPropertiesSet Environment: " + env.getProperty("BaseDirectory"));
    }
}
