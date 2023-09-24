package org.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;

@Configuration
public class DataConfiguration {
    @Bean
    DatabaseStartupValidator databaseStartupValidator(SpringLiquibase liquibase) {
        var dsv = new DatabaseStartupValidator();
        dsv.setDataSource(liquibase.getDataSource());
        dsv.setValidationQuery("select 1 from address");
        return dsv;
    }
}
