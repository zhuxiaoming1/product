package com.itcast.dao;

import com.itcast.pojo.Products;
import com.itcast.utils.C3p0Utils;
import com.itcast.utils.ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoming
 * @create 2019-04-17-15:17
 */
public class ProductDao {
    //查詢所有商品
    public List<Products> queryAll() throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select * from products";
        List<Products> list = qr.query(sql, new BeanListHandler<Products>(Products.class));
        return list;
    }

    public Products queryById(int pid) throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select * from products where pid=?";
        Products p = qr.query(sql, new BeanHandler<Products>(Products.class), pid);
        return p;
    }

    public Products findByName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select * from products where pname=?";
        Products findByName = qr.query(sql, new BeanHandler<Products>(Products.class), name);
        return findByName;
    }

    public void addProduct(Products newP) throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "insert into products(pname,price,flag,category_id) values (?,?,?,?)";
        Object[] params = {newP.getPname(), newP.getPrice(), newP.getFlag(), newP.getCategory_id()};
        qr.update(sql, params);
    }

    public void updateProduct(Products p) throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "update products set pname=?,price=?,flag=?,category_id=? where pid=?";
        Object[] params = {p.getPname(), p.getPrice(), p.getFlag(), p.getCategory_id(), p.getPid()};
        qr.update(sql, params);
    }

    public void deleteById(int id) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from products where pid=?";
        qr.update(con, sql, id);
    }

    public void deleteAll2(List<Integer> list) throws SQLException {

        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());

        String sql = "delete from products where pid in(";
        StringBuilder sb = new StringBuilder(sql);
        for (int i = 0; i < list.size(); i++) {
            sb.append("?");
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        qr.update(sb.toString(), list.toArray());
    }


}
