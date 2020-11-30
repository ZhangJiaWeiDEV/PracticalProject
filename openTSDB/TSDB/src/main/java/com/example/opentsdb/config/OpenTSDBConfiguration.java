package com.example.opentsdb.config;

import org.apache.http.nio.reactor.IOReactorException;
import org.opentsdb.client.OpenTSDBClient;
import org.opentsdb.client.OpenTSDBClientFactory;
import org.opentsdb.client.OpenTSDBConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenTSDBConfiguration {

    @Value("${opentsdb.host}")
    public String host;

    @Value("${opentsdb.port}")
    public Integer post;


    @Bean
    public OpenTSDBClient openTSDBClient() {

        OpenTSDBConfig config = OpenTSDBConfig.address(host, post)
                .config();
        try {
            OpenTSDBClient client = OpenTSDBClientFactory.connect(config);
            return client;
        } catch (IOReactorException e) {
            e.printStackTrace();
            return null;
        }
    }

}
