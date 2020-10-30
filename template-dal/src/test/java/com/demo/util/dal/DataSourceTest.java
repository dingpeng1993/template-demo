package com.demo.util.dal;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author dp
 * @date 2020/7/19 5:06 下午
 */
public class DataSourceTest {

    private String sql = "SELECT * FROM user_test WHERE id = 3;";

    private static DataSource dataSource;

    private int times = 20;

    @Before
    public void init() throws Exception{
        InputStream inputStream = DataSourceTest.class.getResourceAsStream("/druid.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        dataSource = DruidDataSourceFactory.createDataSource(properties);
    }

    @Test
    public void DataSourceManyTimesTestTest() throws Exception {
        long totalStartTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            DataSourceOneTimesTest();
        }
        System.out.println("total time cost: " + (System.currentTimeMillis() - totalStartTime) + "ms");
    }

    private void DataSourceOneTimesTest() throws Exception{
        long beforeConnectionTime = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        long afterConnectionTime = System.currentTimeMillis();
        System.out.println("Create Connection costs:  " + (afterConnectionTime - beforeConnectionTime) + " ms");
        executeSql(connection);
        System.out.println("Execute Query costs:  " + (System.currentTimeMillis() - afterConnectionTime) + " ms");
        connection.close();
    }

    private void executeSql(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        statement.close();
    }
}
