package com.mathiasruck.wallet.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yaml");
//        liquibase.setContexts("development,test,production"); // Specify contexts, if needed
        liquibase.setShouldRun(true); // Should Liquibase migrations be run automatically or not
        return liquibase;
    }
}
