package com.sxt.utils;

import java.sql.*;

/**
 * Created by Lenovo on 2019/10/13.
 */
public class Jdbc {

    private static String userName="root";
    private static String passWord ="123456";
    private static String url = "jdbc:mysql://192.168.1.130:3306/test";

    /*@Value("${username}")
    private static String userName;
    @Value("${password}")
    private static String passWord;
    @Value("${url}")
    private static String url;*/

    private static Connection ct;
    private static PreparedStatement pst;
    private static ResultSet rst;
    //驱动只加载一次即可
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //初始化参数
    private static void getConection() throws SQLException {
            ct = DriverManager.getConnection(url, userName, passWord);
    }

    /**
     * 执行查询操作
     * @param sql
     * @param obj
     * @return
     */
    public static ResultSet search(String sql,Object... obj){
        //获取连接
        try {
            getConection();
            pst = ct.prepareStatement(sql);
            if(obj != null){
                for(int i =0;i<obj.length;i++){
                    if (obj[i] == null) {
                        break;
                    }
                    pst.setObject((i+1),obj[i]);
                }
            }
            //打印出语句
            System.out.println(pst);
            //执行sql
            rst = pst.executeQuery();
            return rst;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 执行修改操作
     * @param sql
     * @param obj
     * @return
     */
    public static Boolean update(String sql, Object... obj) {
        try {
            // 2.从驱动管理器获取连接

            //3、创建sql传送器
            pst = ct.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pst.setObject(i + 1, obj[i]);
                }
            }
            System.out.println(pst);
            int i = pst.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Jdbc.close();
        }
        return null;
    }

    /**
     * 释放连接
     */
    public static void close() {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (ct != null) {
                ct.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
