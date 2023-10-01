package org.example.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SqlSessionFactory sessionFactory = MyBatisUtils.getSqlSessionFactory();
        Scanner scanner = new Scanner(System.in);
        try (SqlSession session = sessionFactory.openSession()) {
            StudentsMapper mapper = session.getMapper(StudentsMapper.class);

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Query all students");
                System.out.println("2. Query student by ID");
                System.out.println("3. Insert a new student");
                System.out.println("4. Update a student by ID");
                System.out.println("5. Delete student by ID");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        List<Student> allStudents = mapper.selectStudents();
                        for (Student student : allStudents) {
                            System.out.println(student.getId() + ": " + student.getName());
                        }
                        break;
                    case 2:
                        System.out.println("Enter student ID: ");
                        int studentId = scanner.nextInt();
                        Student studentById = mapper.getStudentById(studentId);
                        if (studentById != null) {
                            System.out.println("Student found: " + studentById.getName());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 3:
                        System.out.println("Enter student name: ");
                        String newStudentName = scanner.nextLine();
                        Student newStudent = new Student();
                        newStudent.setName(newStudentName);
                        mapper.insertStudent(newStudent);
                        session.commit(); // Commit the transaction
                        System.out.println("New student inserted.");
                        break;
                    case 4:
                        System.out.println("Enter student ID to update: ");
                        int updateStudentId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Student studentToUpdate = mapper.getStudentById(updateStudentId);
                        if (studentToUpdate != null) {
                            System.out.println("Enter updated student name: ");
                            String updatedName = scanner.nextLine();
                            studentToUpdate.setName(updatedName);
                            mapper.updateStudent(studentToUpdate);
                            session.commit(); // Commit the transaction
                            System.out.println("Student updated.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 5:
                        System.out.println("Enter student ID to delete: ");
                        int deleteStudentId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        mapper.deleteStudentById(deleteStudentId);
                        session.commit(); // Commit the transaction
                        System.out.println("Student deleted.");
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } finally {
            scanner.close();
        }
    }
}
