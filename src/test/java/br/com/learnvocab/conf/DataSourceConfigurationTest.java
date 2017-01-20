package br.com.learnvocab.conf;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 */
public class DataSourceConfigurationTest {

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/learnvocab_test");
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }
}
