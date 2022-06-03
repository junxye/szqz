package com.szqz.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * 创建数据库连接池
 */
public class JDBCUtils {

    static ComboPooledDataSource dataSource = new ComboPooledDataSource();/*
    static{
        dataSource = new ComboPooledDataSource();
    }*/
    static {

        try {
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/secondhand");
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("SQLjx1413");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        //System.out.println(dataSource.getJdbcUrl());
        return dataSource;
    }

    /**
     * @return
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException{
        return dataSource.getConnection();
    }

    /**
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn , Statement st , ResultSet rs){
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }
    public static void release(Connection conn , Statement st){
        closeSt(st);
        closeConn(conn);
    }


    private static void closeRs(ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            rs = null;
        }
    }

    private static void closeSt(Statement st){
        try {
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            st = null;
        }
    }

    private static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn = null;
        }
    }

}