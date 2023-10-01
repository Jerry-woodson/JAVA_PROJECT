package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {

        // 设置 Derby 数据库的系统目录
        System.setProperty("derby.system.home", "F:\\db-derby-10.16.1.1-bin");

        // 定义 Derby 数据库连接 URL（嵌入式模式）
        String jdbcUrl = "jdbc:derby:derby;create=true";

        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(jdbcUrl);

            // 创建 SQL 插入命令
            String insertSQL = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";

            // 使用 PreparedStatement 预编译 SQL 命令，以便多次执行
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // 插入 100 条数据
            for (int i = 1; i <= 100; i++) {
                // 设置参数值
                preparedStatement.setInt(1, i); // id
                preparedStatement.setString(2, "Student " + i); // name
                preparedStatement.setInt(3, 20 + i); // age

                // 执行插入
                preparedStatement.executeUpdate();
            }

            // 关闭资源
            preparedStatement.close();
            connection.close();

            System.out.println("100 records inserted into 'students' table successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
