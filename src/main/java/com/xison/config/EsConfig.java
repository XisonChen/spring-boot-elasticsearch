package com.xison.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * created by Xison Chen
 * on 2017/11/12-23:15
 */
@Configuration
public class EsConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {

        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
        Settings setting = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(setting);
        client.addTransportAddress(node);
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}
