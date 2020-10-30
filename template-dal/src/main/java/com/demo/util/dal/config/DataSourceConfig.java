package com.demo.util.dal.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;

/**
 * @author dp
 * @date 2020/7/11 2:51 下午
 */
@Configuration
@EnableConfigurationProperties(DataSourceConfig.DataSourceProperties.class)
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;


    @Bean("dataSource1")
    @Primary
    public DataSource buildDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriver());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setInitialSize(dataSourceProperties.getMinIdle());
        dataSource.setMaxActive(dataSourceProperties.getMaxActive());
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setMinEvictableIdleTimeMillis(150000);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        return null;
    }

    @Data
    @ConfigurationProperties(prefix="datasource.properties")
    public static class DataSourceProperties {

        private String driver;

        private String url;

        private String username;

        private String password;

        private int maxActive;

        private int minIdle;

    }
}
