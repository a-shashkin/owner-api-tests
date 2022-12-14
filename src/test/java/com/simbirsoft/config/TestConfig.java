package com.simbirsoft.config;

@TestConfig.Sources({"classpath:config/config.properties"})
public interface TestConfig extends org.aeonbits.owner.Config {

    String baseUrl();
    String token();
}
