package com.itcast.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xiaoming
 * @create 2019-04-17-14:30
 */
public class C3p0Utils {

    private static DataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw  new RuntimeException("获取连接失败");
        }
    }

    public static void close(Connection con, Statement stat, ResultSet set){
        try {
            if(con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stat!=null)
                stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(set!=null)
                set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
