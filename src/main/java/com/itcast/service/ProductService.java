package com.itcast.service;

import com.itcast.dao.ProductDao;
import com.itcast.pojo.Products;
import com.itcast.utils.ConnectionManager;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiaoming
 * @create 2019-04-17-15:16
 */
public class ProductService {


    public List<Products> queryAll() {
        ProductDao dao = new ProductDao();
        List<Products> list = null;
        try {
            list = dao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Products queryById(int pid) {
        ProductDao dao = new ProductDao();
        Products products = null;
        try {
            products = dao.queryById(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Products findByName(String name) {
        ProductDao dao = new ProductDao();
        Products findByName = null;
        try {
            findByName = dao.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findByName;
    }

    public void addProduct(Products newP) {
        ProductDao dao = new ProductDao();
        try {
            dao.addProduct(newP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Products p) {
        ProductDao dao = new ProductDao();
        try {
            dao.updateProduct(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        ProductDao dao = new ProductDao();
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(List<Integer> list) {
        ProductDao dao = new ProductDao();
        try {
            ConnectionManager.begin();
            for (Integer id : list) {
                dao.deleteById(id);
            }
            ConnectionManager.commit();
        }catch (Exception e){
            ConnectionManager.rollBack();
        }finally {
            ConnectionManager.close();
        }
    }


    public void deleteAll2(List<Integer> list) {

        ProductDao dao = new ProductDao();
        try {
            dao.deleteAll2(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
