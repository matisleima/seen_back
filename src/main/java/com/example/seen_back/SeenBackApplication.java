package com.example.seen_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class SeenBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeenBackApplication.class, args);
    }


    @Bean
    @Profile("h2")
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("DatabaseH2/1_reset.sql"));
        populator.addScript(new ClassPathResource("DatabaseH2/2_create.sql"));
        populator.addScript(new ClassPathResource("DatabaseH2/3_import.sql"));

        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
