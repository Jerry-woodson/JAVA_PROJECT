package org.example.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        // 加载MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        Reader reader = null;
        if (inputStream != null) {
            reader = new InputStreamReader(inputStream);
            // 继续处理 reader
        } else {
            System.err.println("Failed to load the resource file.");
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取StudentsMapper接口的实例
            StudentsMapper studentsMapper = ((SqlSession) sqlSession).getMapper(StudentsMapper.class);

            // 查询用户
            List<Student> allStudents = studentsMapper.getAllStudents();
            allStudents.forEach(System.out::println);

        }
    }
}
