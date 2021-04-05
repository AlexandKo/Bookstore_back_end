package bookstore.com.core.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = "bookstore.com.core.persistence")
@EntityScan(basePackages = "bookstore.com.domain")
@ComponentScan(basePackages = "bookstore.com.core")
@EnableTransactionManagement
public class AppConfiguration {
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.port}")
    private String databasePort;
    @Value("${database.username}")
    private String databaseUserName;
    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + databaseUrl + ":" + databasePort + "/bookstore?createDatabaseIfNotExist=true");
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");
        liquibase.setShouldRun(true);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}