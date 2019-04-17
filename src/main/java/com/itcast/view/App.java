package com.itcast.view;

import com.itcast.pojo.Products;
import com.itcast.service.ProductService;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiaoming
 * @create 2019-04-17-14:34
 */
public class App {
    public static void main(String[] args) {


        while (true) {
            System.out.println("输入以下命令进行操作");
            System.out.println("C:创建、U:修改、D:删除、DA:删除所有、I:通过id查询、FA:查询所有、Q:退出");
            String choose = enterString();
            switch (choose.toUpperCase()) {
                case "C":
                    addProduct();
                    break;
                case "U":
                    updateProduct();
                    break;
                case "D":
                    deleteById();
                    break;
                case "DA":
                    deleteAll();
                    break;
                case "I":
                    queryById();
                    break;
                case "FA":
                    queryAll();
                    break;
                case "Q":
                    System.out.println("您确定要退出吗？y/n");
                    String quit = enterString();
                    if ("y".equalsIgnoreCase(quit)) {
                        System.exit(0);
                    }
                    break;
                default:
                    System.err.println("请根据提示输入！！！");
                    break;
            }
        }
    }


    private static void queryAll() {
        ProductService service = new ProductService();
        List<Products> list = service.queryAll();
        if (list == null || list.isEmpty()) {
            System.out.println("沒有商品記錄");
            return;
        }
        System.out.println("查詢到的商品為：");
        for (Products products : list) {
            System.out.println(products);
        }
    }

    private static void queryById() {
        System.out.println("請輸入要查詢的商品id");
        ProductService service = new ProductService();
        int pid = enterInt();
        Products p = service.queryById(pid);
        if (p == null) {
            System.out.println("不存在該id對應的商品");
            return;
        }
        System.out.println("查詢到的商品為：");
        System.out.println(p);
    }

    private static void deleteAll() {
        System.out.println("批量删除");
        List<Integer> list = new ArrayList<>();
        ProductService service = new ProductService();
        queryAll();
        System.out.println("进入到批量删除模式(输入-1退出)：");
        while (true){
            System.out.println("请输入要删除商品的id:");
            int id= enterInt();
            if (id==-1){
                break;
            }
            Products p = service.queryById(id);
            if (p==null){
                System.out.println("要删除的商品不存在");
                continue;
            }
            System.out.println("要删除的商品为：");
            System.out.println(p);
            list.add(id);
        }
        service.deleteAll2(list);
        System.out.println("删除成功");
        queryAll();
    }

    private static void deleteById() {
        queryAll();
        System.out.println("请输入要删除的商品id");
        int id = enterInt();
        ProductService service = new ProductService();
        Products p = service.queryById(id);
        if (p==null){
            System.out.println("要删除的商品不存在");
            return;
        }
        System.out.println("要删除的商品为");
        System.out.println(p);
        System.out.println("确认要删除吗 y/n");
        String choose = enterString();
        if ("y".equalsIgnoreCase(choose)){
            service.deleteById(id);
            System.out.println("删除成功");
            queryAll();
        }
    }

    private static void updateProduct() {

        queryAll();
        System.out.println("请输入要修改的商品id");
        int id = enterInt();
        ProductService service = new ProductService();
        Products queryById = service.queryById(id);
        if (queryById==null){
            System.out.println("要修改的商品不存在");
            return;
        }
        System.out.println(queryById);
        System.out.println("请输入商品的新名称");
        String name = enterString();
        System.out.println("请输入价格");
        double price = enterDouble();
        boolean b =true;
        String flag="";
        while (b){
            System.out.println("请选择是否上下架 1表示上架、0表示下架");
            flag = enterString();
            if (!flag.equals("1")&&!flag.equals("0")){
                System.out.println("请按照提示输入");
                continue;
            }
            b=false;
        }
        b=true;
        int category_id = 0;
        while (b){
            System.out.println("请选择商品分类 1.家电2.服装3.化妆品");
            category_id = enterInt();
            if (category_id < 1 || category_id > 3) {
                System.err.println("请按照提示输入!!");
                continue;
            }
            b=false;
        }
        Products p = new Products(id, name, price, flag, category_id);
        service.updateProduct(p);
        System.out.println("修改成功");
        queryAll();
    }

    private static void addProduct() {
        ProductService service = new ProductService();
        System.out.println("请输入新商品的名称");
        String name = enterString();
        //需要根据商品名称查询数据库是否有相同的商品
        Products findByName = service.findByName(name);
        if (findByName != null) {
            System.out.println("要添加的商品已存在");
            return;
        }
        System.out.println("请输入商品价格");
        double price = enterDouble();
        System.out.println("请选择是否上下架 1表示上架、0表示下架");
        String flag = enterString();
        System.out.println("请选择商品分类 1.家电2.服装3.化妆品");
        int category_id = enterInt();
        if (category_id < 1 || category_id > 3) {
            System.err.println("请按照提示输入!!");
            return;
        }
        Products newP = new Products(0, name, price, flag, category_id);
        service.addProduct(newP);
        System.out.println("添加成功");
    }


    //避免scanner类的键入冲突
    private static String enterString() {
        return new Scanner(System.in).nextLine();
    }

    private static int enterInt() {
        return new Scanner(System.in).nextInt();
    }

    private static double enterDouble() {
        return new Scanner(System.in).nextDouble();
    }
}
