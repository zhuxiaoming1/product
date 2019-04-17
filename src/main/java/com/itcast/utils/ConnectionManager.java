package com.itcast.utils;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xiaoming
 * @create 2019-04-17-1:13
 */
public class ConnectionManager {

    private static ThreadLocal<Connection> threadLocal =new ThreadLocal<Connection>();

    public static Connection getConnection() {
        Connection con = threadLocal.get();
        if (con==null){
            con  = C3p0Utils.getConnection();
            threadLocal.set(con);
            return con;
        }
        return con;
    }

    public static void begin() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        Connection connection = getConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollBack(){
        Connection connection = getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        Connection connection = getConnection();
        threadLocal.remove();
        try {
            DbUtils.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
