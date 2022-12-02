package com.zzy.exam.seven;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author molimark<br />
 * @date: 2022/12/2 10:19<br/>
 * @description: <br/>
 */
public class Druid {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\examtest\\test.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        String sql = "select * from t_test";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            String one = resultSet.getString(1);
            String two = resultSet.getString(2);
            String three = resultSet.getString(3);
            String four = resultSet.getString(4);
            System.out.println(one+two+three+four);
        }
        stmt.close();
        connection.close();
    }
}
