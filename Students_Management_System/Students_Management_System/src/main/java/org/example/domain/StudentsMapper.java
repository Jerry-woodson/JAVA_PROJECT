package org.example.domain;

import java.util.List;

public interface StudentsMapper {

    // 查询所有学生
    List<Student> selectStudents();

    // 根据学生ID查询学生信息
    Student getStudentById(int id);

    // 插入一条学生记录
    void insertStudent(Student student);

    // 更新学生信息
    void updateStudent(Student student);

    // 根据学生ID删除学生记录
    void deleteStudentById(int id);

    List<Student> getAllStudents();
}
