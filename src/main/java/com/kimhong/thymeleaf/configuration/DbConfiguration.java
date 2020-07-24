package com.kimhong.thymeleaf.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;

//@Configuration

public class DbConfiguration {

    @Bean
    public DataSource configurationDb() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/ams");
        builder.username("postgres");
        builder.password("Pa$$word9d99it2");
        return builder.build();
    }
}
