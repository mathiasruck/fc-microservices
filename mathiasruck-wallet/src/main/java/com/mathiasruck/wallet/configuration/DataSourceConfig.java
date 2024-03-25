package com.mathiasruck.wallet.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${MYSQL_HOST:localhost}")
    private String mysqlHost;

    @Value("${MYSQL_USERNAME:root}")
    private String mysqlUsername;

    @Value("${MYSQL_PASSWORD:root}")
    private String mysqlPassword;

    @Value("${MYSQL_PORT:3307}")
    private String mysqlPort;

    @Value("${MYSQL_DATABASE:mathiasruck-wallet}")
    private String mysqlDatabase;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDatabase)
                .username(mysqlUsername)
                .password(mysqlPassword)
                .build();
    }
}
