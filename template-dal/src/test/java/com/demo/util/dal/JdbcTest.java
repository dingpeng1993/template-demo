package com.demo.util.dal;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author dp
 * @date 2020/7/16 9:14 上午
 */
public class JdbcTest {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://10.96.238.10:10406/pops?useUnicode=true&characterEncoding=UTF-8";

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "123456";

    private String sql = "SELECT * FROM user_test WHERE id = 3;";

    private static DataSource dataSource;

    private int times = 20;

    @Before
    public void init() throws Exception{
        InputStream inputStream = JdbcTest.class.getResourceAsStream("/druid.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        dataSource = DruidDataSourceFactory.createDataSource(properties);
    }

    @Test
    public void JdbcTest() throws Exception {
        Class.forName(DRIVER);
        JDBCOneTimeTest();

    }

    @Test
    public void JDBCManyTimesTest() throws Exception{
        Class.forName(DRIVER);
        long totalStartTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            JDBCOneTimeTest();
        }
        System.out.println("total time cost: " + (System.currentTimeMillis() - totalStartTime) + "ms");
    }
    private void JDBCOneTimeTest() throws SQLException {
        long beforeConnectionTime = System.currentTimeMillis();
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
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
