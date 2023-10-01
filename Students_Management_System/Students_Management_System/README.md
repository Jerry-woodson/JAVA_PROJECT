<h2>基于Mybatis框架下的学生管理系统</h2>
<h3>Tips:</h3>
本项目使用的是内嵌式的derby数据库，同时使用Mybatis框架，将Druid作为连接池。
<h3>项目的基本结构</h3>
-main
<br>----java/resources
<br>-----org.example/mapper
<br>-------data/domain  studentsMapper.xml/derby.jar/derclient.jar/mybatis-config.xml
<br>---------insertData.java/QueryData.java Main.java/MyBatisUtils.java/Student.java/StudentsMapper.java/Test.java
<h3>项目基本实现的功能</h3>
1.根据姓名模糊查询学生（不传姓名时则查询全部学生）</br>
2.根据学生ID查询学生信息</br>
3.录入学生信息</br>
4.根据学生ID修改学生信息</br>
5.根据学生ID的集合/数组批量删除学生信息</br>
注：主要的功能实现
domain文件夹当中主要是Student类、Mybatis类以及Main实现的主类，同时还提供了StudentsMapper的interface
与resources/mapper文件夹当中的StudentsMapper.xml文件进行一定的交互
在pom.xml当中需要引入derby数据库、Druid连接池等的相关依赖