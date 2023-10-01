package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryData {
    public static void main(String[] args) {
        // 设置 Derby 数据库的系统目录
        System.setProperty("derby.system.home", "F:\\db-derby-10.16.1.1-bin");

        // 定义 Derby 数据库连接 URL（嵌入式模式）
        String jdbcUrl = "jdbc:derby:derby;create=true";

        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(jdbcUrl);

            // 创建 SQL 查询
            String querySQL = "SELECT * FROM students";

            // 创建 Statement 对象并执行查询
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySQL);

            // 处理查询结果
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

            // 关闭资源
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
