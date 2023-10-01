[TOC]

# 一、Maven/Gradle-项目构建工具

## 1、概念

Maven和Gradle都是流行的构建工具，用于管理Java项目的依赖项、构建和部署过程。它们有一些相似之处，但也有一些重要的区别。

**Maven:**

- **发展和市场占有率：** Maven于2004年首次发布，是早期的构建工具之一。它是由Apache软件基金会维护的开源项目，因其稳定性和广泛的使用而广受欢迎。许多开源和企业项目使用Maven进行构建。它是许多传统Java项目的首选构建工具。
- **区别：** Maven使用XML配置文件（`pom.xml`）来定义项目的构建和依赖关系。它采用约定优于配置的原则，有一组默认的标准目录结构和生命周期阶段，开发人员只需在配置文件中声明自定义配置。Maven的插件生态系统非常丰富，允许扩展和自定义构建过程。
- **优势：** Maven的优势包括稳定性、广泛的社区支持、丰富的插件生态系统和易于上手。它适用于传统Java项目，以及许多其他JVM语言的项目。

**Gradle:**

- **发展和市场占有率：** Gradle于2007年首次发布，是相对较新的构建工具。它采用了颠覆性的声明式DSL（领域特定语言）来定义构建过程，因此具有更大的灵活性。Gradle在过去几年中逐渐获得了更多的关注和市场份额，尤其是对于Android应用程序和一些大型企业项目。
- **区别：** Gradle使用Groovy或Kotlin编写的脚本来定义构建和任务。这允许开发人员以更自然的方式描述构建过程，并提供了更大的灵活性和可读性。与Maven不同，Gradle没有强制的约定，允许开发人员更自由地定义项目结构和构建过程。
- **优势：** Gradle的优势包括灵活性、可读性、性能优化、增量构建（只构建发生变化的部分）以及在Android开发中的强大支持。它适用于需要高度自定义构建过程或有复杂构建需求的项目。

总结：

- Maven是一个稳定而成熟的构建工具，适用于传统Java项目，并具有广泛的社区支持和插件生态系统。

- Gradle是一个更灵活、现代和可读性更强的构建工具，适用于需要高度自定义构建过程的项目，尤其在Android开发中表现出色。

## 2、maven

Maven项目的核心配置文件是`pom.xml`（Project Object Model）。`pom.xml`文件定义了项目的元数据、依赖关系、构建配置等。下面详细介绍`pom.xml`的构成以及各个元素的意义：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion> <!-- POM模型的版本，通常使用4.0.0 -->

    <groupId>cn.edu.qdu</groupId> <!-- 项目的组ID，通常使用公司或组织的域名倒序形式 -->
    <artifactId>my-project</artifactId> <!-- 项目的唯一标识符，通常是项目的名称或模块的名称 -->
    <version>1.0.0</version> <!-- 项目的版本号，通常使用标准的版本号格式 -->

    <packaging>jar</packaging> <!-- 定义项目的打包类型，例如，'jar'表示将项目打包为JAR文件 -->

    <!-- 项目的基本信息 -->
    <name>My Project</name> <!-- 项目的人类可读名称 -->
    <description>A sample Maven project</description> <!-- 项目的简要描述 -->

    <!-- 项目依赖关系 -->
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId> <!-- 依赖项的组ID -->
            <artifactId>spring-core</artifactId> <!-- 依赖项的唯一标识符 -->
            <version>5.2.3.RELEASE</version> <!-- 依赖项的版本 -->
        </dependency>
        <!-- 其他依赖项 -->
    </dependencies>

    <!-- 构建配置 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId> <!-- 插件的组ID -->
                <artifactId>maven-compiler-plugin</artifactId> <!-- 插件的唯一标识符 -->
                <version>3.8.1</version> <!-- 插件的版本 -->

                <!-- 插件的配置信息 -->
                <configuration>
                    <source>1.8</source> <!-- 指定Java编译器版本 -->
                    <target>1.8</target> <!-- 指定目标Java版本 -->
                </configuration>
            </plugin>
            <!-- 其他构建插件 -->
        </plugins>
    </build>
</project>

```

### version

在Maven的`<version>`元素中，通常可以使用以下几种不同的版本号格式：

1. **标准版本号**：这是最常见的版本号格式，通常采用三位数字表示，如`1.2.3`。这三个数字分别代表主版本号、次版本号和修订版本号。主版本号表示重大变更，次版本号表示较小的变更和功能添加，修订版本号表示错误修复和小的改进。
2. **Alpha版本（α）**：Alpha版本表示开发阶段的早期版本，可能包含不稳定的功能和API。通常以`alpha`或`α`后缀表示，如`1.2.0-alpha1`。
3. **Beta版本（β）**：Beta版本表示开发阶段的中期版本，通常相对稳定，但可能还有一些问题需要解决。通常以`beta`或`β`后缀表示，如`2.0.0-beta2`。
4. **RC（Release Candidate）版本**：RC版本表示预览版本，通常用于邀请社区测试和反馈。通常以`rc`后缀表示，如`3.0.0-rc1`。
5. **里程碑版本（Milestone）**：里程碑版本表示开发中的关键阶段，通常包含一组功能的集成和测试。通常以`M`后缀表示，如`4.0.0-M3`。
6. **SNAPSHOT版本**：SNAPSHOT版本表示开发中的不稳定版本，通常用于表示正在进行的开发工作。SNAPSHOT版本可以用于快速迭代和开发，但不适用于发布。通常以`-SNAPSHOT`后缀表示，如`5.0.0-SNAPSHOT`。
7. **LATEST版本**：`LATEST`是一个特殊的版本号，用于表示始终使用最新版本。但是，不建议在实际项目中使用`LATEST`，因为它不稳定且可能导致构建不一致性。

通常，推荐在实际项目中使用标准版本号，而避免使用Alpha、Beta、RC、Milestone和SNAPSHOT等不稳定版本，以确保项目的稳定性和可维护性。版本号的选择应根据项目的需求和发布策略来确定。

### packaging

在Maven中，`<packaging>`元素用于指定项目的打包类型，即最终构建输出的文件类型。以下是常见的Maven打包类型：

1. **jar**：将项目打包为可执行的JAR文件。这是Java应用程序的常见打包类型，包含编译后的Java类文件以及项目的资源文件。
2. **war**：将项目打包为Web应用程序存档（WAR文件）。这种打包类型用于部署到Java Web容器（例如Tomcat）中，通常包含Web应用程序的Servlet、JSP文件和静态资源。
3. **pom**：这个打包类型通常用于父项目或聚合项目（Multi-Module Project），表示它本身不产生构建产物，而是用于管理其他子模块项目的依赖关系。
4. **ear**：将项目打包为企业应用程序存档（EAR文件）。这种打包类型用于部署到Java EE应用服务器中，包含EJB（Enterprise JavaBeans）模块、Web模块和其他Java EE组件。
5. **rar**：将项目打包为资源适配器存档（RAR文件）。RAR文件是用于Java EE应用服务器的JCA（Java Connector Architecture）适配器。
6. **bundle**：这个打包类型通常用于OSGi（Open Service Gateway Initiative）项目，用于构建OSGi bundle。
7. **maven-plugin**：用于构建Maven插件项目，这样可以创建自定义的Maven插件并将其发布到Maven仓库供其他项目使用。
8. **ejb**：将项目打包为EJB模块，通常包含EJB组件的Java类和部署描述文件。
9. **apk**：将Android应用程序项目打包为Android应用包（APK文件），用于Android应用程序的构建。
10. **test-jar**：将测试类和资源打包为JAR文件，通常用于共享测试类库供其他项目使用。

这些是常见的Maven打包类型，可以根据项目的类型和需求选择适当的打包类型来定义`<packaging>`元素。不同的打包类型会影响项目的构建输出和部署方式。

### settings.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
  <pluginGroups></pluginGroups>

  <proxies></proxies>

  <servers></servers>

  <mirrors>
  	<mirror>
  		<id>aliyunmaven</id>
 		 <mirrorOf>*</mirrorOf>
  		<name>阿里云公共仓库</name>
  		<url>https://maven.aliyun.com/repository/public</url>
	</mirror>
  </mirrors>

  <profiles></profiles>
</settings>

```

配置阿里云镜像源。

# 二、JDBC - Java数据库连接

**数据库**：

- 数据库是用于存储和管理数据的软件系统，它可以按照特定的结构和规则来组织数据，以方便数据的存储、检索、更新和管理。
- 数据库系统通常由数据库管理系统（DBMS）来管理，DBMS是一个软件程序，它负责处理数据库的创建、维护、备份、恢复、安全性等方面的任务。
- 常见的数据库系统包括MySQL、Oracle、SQL Server、PostgreSQL、SQLite等。（本地数据库derby[^1]）

**JDBC（Java Database Connectivity）**：

- JDBC是Java语言的一组API，用于连接和操作各种关系型数据库。

- JDBC允许Java应用程序与数据库进行通信，执行SQL查询、插入、更新和删除等操作，并处理数据库连接、事务管理和错误处理等任务。

- JDBC提供了一种标准化的方式，使Java应用程序能够与不同的数据库系统进行交互，而无需了解底层数据库的细节。

  ![0468611f01bdd43d4222062d0262ecf0](.\第三节_JDBC.assets\APP_JDBC_数据库.png)

**Hibernate**：

- **Hibernate**是一个开源的Java持久化框架，它提供了一种对象关系映射（ORM）的方式，允许Java对象与数据库表之间的映射。
- Hibernate的目标是简化数据库访问，并提供一种更面向对象的方法来处理数据，从而隐藏了底层数据库的复杂性。
- Hibernate使用XML或注解来配置对象与数据库表之间的映射关系。开发人员可以定义实体类，并使用Hibernate的映射配置来指定如何将这些实体映射到数据库表。
- Hibernate负责管理数据库连接、执行SQL查询、插入、更新和删除等操作，以及处理事务管理和缓存。
- Hibernate还提供了高级功能，如延迟加载、缓存管理、查询语言（HQL和Criteria API）等。

**MyBatis**：

- MyBatis是一个开源的Java持久化框架，它简化了数据库访问的过程，使开发人员能够使用简单的XML或注解配置来映射Java对象和SQL查询。

- MyBatis提供了一种对象关系映射（ORM）的方式，允许Java对象和数据库表之间的数据映射。开发人员可以定义SQL查询和映射规则，然后使用MyBatis执行查询并将结果映射到Java对象中。

- MyBatis还提供了高级功能，如动态SQL、批处理、缓存管理等，使数据库操作更加灵活和高效。

**关系：**

- JDBC通常是用来连接Java应用程序和数据库之间的桥梁，允许Java代码执行SQL操作。
- MyBatis和Hibernate是建立在JDBC之上的框架，它简化了数据库访问，提供了更高级的抽象和便捷性。MyBatis和Hibernate使用JDBC来执行底层数据库操作。
- MyBatis和Hibernate都是ORM框架，它们也提供了对象与数据库之间的映射，但它们的设计哲学和用法有所不同。MyBatis更加注重SQL的灵活性和手动控制，而Hibernate更强调对象的自动持久性和映射。

**综上所述，数据库是数据的存储和管理系统，JDBC是Java应用程序与数据库之间的标准接口，而MyBatis和Hibernate是一个用于简化数据库访问的框架，它们共同为Java开发人员提供了强大的工具来操作和管理数据库。开发人员可以根据项目的需求选择使用JDBC直接访问数据库，或者使用MyBatis和Hibernate来简化和提高数据库访问的效率。**

[^1]: Derby是一个适用于小型项目、原型开发以及需要嵌入式数据库的应用程序的理想选择。它具有简单的配置和低资源消耗，但也可以用于一些中等规模的数据库应用。由于其轻量级和跨平台的特性，Derby在许多Java应用程序中得到广泛使用。如果你需要一个小型的、易于集成的关系型数据库，Derby可能是一个不错的选择。



## 1、创建Derby数据库

### 1.  下载derby

derby下载地址：https://db.apache.org/derby/releases/release-10_14_2_0.html

derby下载地址（短链接）：http://mtw.so/6vDXzy

### 2.  设置环境变量

- 设置`DERBY_HOME`环境变量，指向Derby的安装目录。
- 将`DERBY_HOME/bin`添加到你的系统`PATH`中，以便在命令行中能够直接访问Derby工具。

### 3. 创建数据库

- 打开命令行终端。
- 使用以下命令创建一个新的Derby数据库（这将创建一个名为"qdu"的数据库）：

```cmd
ij> CONNECT 'jdbc:derby:qdu;create=true';
```

这将连接到名为"qdu"的数据库，如果不存在则会创建它。

### 4. 启动Derby Network Server（可选）：

- Derby可以在嵌入式模式下运行，也可以作为网络服务器运行，允许多个客户端连接。

- 要以网络服务器模式启动Derby，请运行以下命令：

```cmd
startNetworkServer -h <host> -p <port>
```

这将启动Derby Network Server。

### 5. 连接到数据库：

   - 你可以使用Java代码或Derby的命令行工具（如ij）连接到数据库并执行操作。

### 6. 关闭数据库和服务器：

   - 使用以下命令关闭数据库连接：

```cmd
ij> CONNECT 'jdbc:derby:MyDatabase;shutdown=true';
```

   - 使用以下命令停止Derby Network Server（如果正在运行）：

```cmd
NetworkServerControl shutdown -h <host> -p <port>
```



### Derby与mysql的一些差异

1. **标识符引用**：

   - Derby默认情况下将标识符视为不区分大小写，而MySQL默认情况下是区分大小写的。这意味着在Derby中，表名和列名不区分大小写，但在MySQL中它们是区分大小写的。你可以使用引号来明确指定大小写敏感的标识符，例如："TableName"。

2. **字符串的引号**：

   - 在Derby中，字符串可以用单引号或双引号括起来，例如：'Hello' 或 "Hello" 都是有效的字符串。
   - 在MySQL中，字符串通常使用单引号括起来，例如：'Hello'。

3. **注释**：

   - Derby和MySQL支持SQL注释，但注释的语法略有不同。在Derby中，注释可以使用"--"（双短横线）或"/* */"（斜杠星号）来表示。在MySQL中，注释通常使用"#"（井号）或"--"表示。

4. **自增列**：

   - 在Derby中，自增列通常使用

     ```sql
     GENERATED ALWAYS AS IDENTITY
     ```

     来定义。例如：

     ```sql
     id INT GENERATED ALWAYS AS IDENTITY
     ```

   - 在MySQL中，自增列通常使用

     ```sql
     AUTO_INCREMENT
     ```

     属性来定义。例如：

     ```sql
     id INT AUTO_INCREMENT
     ```

5. **LIMIT和OFFSET（分页查询）**：

   - 在Derby中，分页查询可以使用

     ```sql
     OFFSET
     ```

     和

     ```sql
     FETCH FIRST
     ```

     子句，例如：

     ```sql
     SELECT * FROM TableName OFFSET 10 ROWS FETCH FIRST 5 ROWS ONLY
     ```

   - 在MySQL中，通常使用

     ```sql
     LIMIT
     ```

     子句来实现分页查询，例如：

     ```sql
     SELECT * FROM TableName LIMIT 5 OFFSET 10
     ```

6. **日期和时间函数**：

   - Derby和MySQL支持一些相同的日期和时间函数，但具体的函数名称和用法可能会有所不同。例如，Derby使用`CURRENT_TIMESTAMP`来获取当前时间，而MySQL使用`NOW()`。

7. **连接字符串**：

   - 连接到数据库时，Derby和MySQL的JDBC连接字符串可能有不同的格式和参数。

## 2、通过JDBC创建Derby数据库连接

### 1.  新建一个Maven项目

​	进入Idea，点击左上角文件-新建-项目。

![image-20230911144606305](.\第三节_JDBC.assets\image-20230911144606305.png)

​	在左侧的菜单栏选择`Maven`，右侧选择对应`JDK1.8`的SDK。

![image-20230911144633571](.\第三节_JDBC.assets\image-20230911144633571.png)

​	点击下一步后，可以输入项目名称，以`Day002`为例，展开工件坐标，其中`组ID`、`工件ID`、`版本`分别对应了Maven中的`groupId`、`artifactId`、`version`

![image-20230911144727572](.\第三节_JDBC.assets\image-20230911144727572.png)

### 2.  引入Mysql或Derby的依赖

- 首先访问[Maven Repository: Search/Browse/Explore --- Maven 存储库：搜索/浏览/探索 (mvnrepository.com)](https://mvnrepository.com/)
- 搜索栏输入想要使用的插件，如`mysql`

- 选择一个软件，点击对应版本（建议与客户端版本一致）

```xml
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

- 粘贴到pom.xml内

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Test002_maven</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.derby/derbyclient -->
        <dependency>
            <groupId>org.apache.derby</groupId>
            <artifactId>derbyclient</artifactId>
            <version>10.14.2.0</version>
        </dependency>
    </dependencies>
    
</project>

```
### 3.  创建连接

```java
public static void main(String[] args) {
        //String dbUrl = "jdbc:mysql://rm-m5e06004ccyah338s7o.mysql.rds.aliyuncs.com:3306/qdu";
     	//String dbUser = "qdu";
        //String dbPassword = "student_123";
        String dbUrl = "jdbc:derby://localhost:2023/qdu;create=true";
        
        // 加载Derby的JDBC驱动程序
        //try {
        //    DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    
    	//DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        try(Connection connection = DriverManager.getConnection(dbUrl);){
            // TODO 利用connection进行DDL或DML
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

1. **DDL（Data Definition Language）**：

   - **目的**：DDL用于定义数据库的结构，包括表、索引、视图、触发器等数据库对象的创建、修改和删除。
   - **主要命令**：DDL的主要命令包括`CREATE`、`ALTER`和`DROP`。
   - **示例**：创建表、修改表结构、删除表、创建索引、创建视图、创建触发器等操作都属于DDL操作。

   DDL操作通常由数据库管理员或有权操作数据库结构的用户执行，因为它们对数据库的结构进行更改。

2. **DML（Data Manipulation Language）**：

   - **目的**：DML用于操作和管理数据库中的数据，包括插入、更新、删除和查询记录。
   - **主要命令**：DML的主要命令包括`INSERT`、`UPDATE`、`DELETE`和`SELECT`。
   - **示例**：向表中插入新记录、更新表中的记录、删除表中的记录以及执行各种查询操作都属于DML操作。

   DML操作通常由应用程序开发人员和普通数据库用户执行，因为它们关注的是数据的操作而不是数据库结构的定义。

### 4.  Statement

![img](.\第三节_JDBC.assets\1816118-20191126204448583-1757029266.jpg)

#### Statement分类：

1. **Statement**：

   - **创建方式**：使用`Connection`的`createStatement()`方法创建。

   - **用途**：`Statement`对象用于执行静态SQL语句，这些语句在执行之前通常不会发生变化。

   - **示例**：

     ```java
     Statement statement = connection.createStatement();
     String sql = "SELECT * FROM students";
     ResultSet resultSet = statement.executeQuery(sql);
     ```

   - **注意事项**：`Statement`存在SQL注入风险，因为它不会对SQL语句进行参数化处理。不建议将用户输入直接嵌入SQL语句中，以避免安全问题。

**永远不要相信用户的输入！ - 沃兹基·硕德**

```
一个侧试工程师走进一家酒吧，要了一杯啤酒；
一个测试工程师走进一家酒吧，要了一杯咖啡；
一个侧试工程师走进一家酒吧，要了 0.7 杯啤酒；
一个侧试工程师走进一家酒吧，要了 -1  杯啤酒；
一个测试工程师走进一家酒吧，要了 232 杯啤酒；
一个测试工程师走进一家酒吧，要了一杯蜥蜴；
一个测试工程师走进一家酒吧，要了一份 asdfQWer @ 24dg ! & * ( @ ;
一个测试工程师走进一家酒吧，什么也没要；
一个测试工程师泪井一家酒吧，又走出去又进来又出去又进来又出去，最后在外面把老板打了一顿；
一个测试工程师走进一家酒吧，要了一杯烫烫烫的锟斤拷；
一个测试工程师走进一家酒吧，要了 NaN 杯 Null ；
一个测试工程师把酒吧拆了；
一个测试工程师伪装成老板走讲一家酒吧，要了 500 杯啤酒并且不付钱；
一万个测试工程师在酒吧门外呼啸而过；
一个测试工程师走进一家酒吧，要了一杯啤酒; DROP TABLE 酒吧;
测试工程师们满意地离开了酒吧。



然后一名顾客点了一份炒饭，酒吧炸了。
```

```java
String str="天气预报说周二有小雨！";
try {
	String str3=new String(str.getBytes("GBK"),"UTF-8");
	String str4=new String(str3.getBytes("UTF-8"),"GBK");
	System.out.println(str4);
} catch (Exception e) {
	e.printStackTrace();
}
```

2. **PreparedStatement**：

   - **创建方式**：使用`Connection`的`prepareStatement()`方法创建。

   - **用途**：`PreparedStatement`对象用于执行预编译的SQL语句，通常用于执行多次的SQL语句，可以提高性能并防止SQL注入攻击。

   - **示例**：

     ```java
     String sql = "INSERT INTO students (name, date_of_birth, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
     PreparedStatement preparedStatement = connection.prepareStatement(sql);
     preparedStatement.setString(1, "John");
     preparedStatement.setDate(2, new Date());
     preparedStatement.setString(3, "15666666469@163.com");
     preparedStatement.setString(4, "15666666469");
     preparedStatement.setString(5, "青岛大学师范学院C楼507宿舍");
     int rowsAffected = preparedStatement.executeUpdate();
     ```
   
   - **注意事项**：`PreparedStatement`允许通过参数占位符（`?`）传递参数，可以有效地防止SQL注入攻击，并提高性能，因为SQL语句仅在编译时被解析一次。

3. **CallableStatement**：

   - **创建方式**：使用`Connection`的`prepareCall()`方法创建。

   - **用途**：`CallableStatement`对象用于执行数据库存储过程（Stored Procedure）或函数（Function）。

   - **示例**：

     ```java
     String sql = "{call my_stored_procedure(?, ?)}";
     CallableStatement callableStatement = connection.prepareCall(sql);
     callableStatement.setInt(1, 123);
     callableStatement.registerOutParameter(2, Types.INTEGER); // 如果存储过程返回一个OUT参数
     callableStatement.execute();
     int result = callableStatement.getInt(2); // 获取OUT参数的值
     ```

   - **注意事项**：`CallableStatement`主要用于调用存储过程，并支持输入参数、输出参数和返回结果。

这些不同类型的`Statement`对象用于执行不同种类的SQL操作。`PreparedStatement`是最常用的，因为它提供了安全性和性能的优势。根据具体需求和SQL操作类型，选择合适的`Statement`类型。

#### 参考SQL：

```sql
-- 删除学生表
DROP TABLE students;
-- 创建学生表
CREATE TABLE students (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50),
    date_of_birth DATE,
    email VARCHAR(100),
    phone_number VARCHAR(20),
    address VARCHAR(200)
);
-- 查询数据
SELECT id, name, date_of_birth, email, phone_number, address FROM students;
-- 插入一行数据
INSERT INTO students (name, date_of_birth, email, phone_number, address) VALUES ('张三', '2004-10-01', 'zhangsan@qdu.edu.cn', '15666666469', '青岛大学师范学院C楼507宿舍');
-- 修改一行数据
UPDATE students SET name = '李四' where name = '张三';
-- 删除一行数据
DELETE FROM STUDENTS where name = '李四';
```



#### Statement使用：

1. **execute(String sql)**：

   - 用途：执行给定的SQL语句，可以是任何SQL语句（例如，SELECT、INSERT、UPDATE、DELETE等）。
   - 返回值：如果SQL语句是查询语句（SELECT），则返回一个`ResultSet`对象，用于检索查询结果。对于其他类型的SQL语句，返回`false`。

   ```java
   Statement statement = connection.createStatement();
   boolean isResultSet = statement.execute("SELECT * FROM students");
   if (isResultSet) {
       ResultSet resultSet = statement.getResultSet();
       // 处理查询结果
   } else {
       int rowsAffected = statement.getUpdateCount();
       // 处理更新操作的结果
   }
   ```

2. **executeQuery(String sql)**：

   - 用途：执行查询SQL语句，通常用于从数据库中检索数据。
   - 返回值：返回一个`ResultSet`对象，用于检索查询结果。

   ```java
   Statement statement = connection.createStatement();
   ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
   // 处理查询结果
   while (resultSet.next()) {
   	int id = resultSet.getInt("id");
   	String name = resultSet.getString("name");
   	java.sql.Date dateOfBirth = resultSet.getDate("date_of_birth");
   	String email = resultSet.getString("email");
   	String phoneNumber = resultSet.getString("phone_number");
   	String address = resultSet.getString("address");
   
   	// 处理每一行记录
   	System.out.println("ID: " + id);
   	System.out.println("Name: " + name);
   	System.out.println("Date of Birth: " + dateOfBirth);
   	System.out.println("Email: " + email);
   	System.out.println("Phone Number: " + phoneNumber);
   	System.out.println("Address: " + address);
   	System.out.println("----------------------------------");
   }
   ```

3. **executeUpdate(String sql)**：

   - 用途：执行更新SQL语句（INSERT、UPDATE、DELETE等），通常用于插入、更新或删除数据。
   - 返回值：返回一个整数，表示受影响的行数。

   ```java
   Statement statement = connection.createStatement();
   int rowsAffected = statement.executeUpdate("UPDATE students SET name = 'monitor' WHERE id = 1");
   // 处理更新操作的结果
   if (rowsAffected == Statement.SUCCESS_NO_INFO) {
       System.out.println("SQL执行成功，但没有返回结果信息。");
   } else if (rowsAffected == Statement.EXECUTE_FAILED) {
       System.out.println("SQL执行失败。");
   } else {
   	System.out.println("受影响的行数: " + result);
   }
   ```

4. **executeBatch()**：

   - 用途：批量执行一组SQL语句，通常用于执行多个SQL操作，提高性能。`addBatch`方法通常用于执行非查询（INSERT、UPDATE、DELETE等）SQL语句的批处理操作。。
   - 返回值：返回一个整数数组，表示每个SQL语句的执行结果。

   ```java
   Statement statement = connection.createStatement();
   statement.addBatch("INSERT INTO students (name, phone_number) VALUES ('Alice', '13800000000')");
   statement.addBatch("INSERT INTO students (name, phone_number) VALUES ('Bob', '13800000001')");
   int[] batchResults = statement.executeBatch();
   // 处理批处理操作的结果
   for (int result : batchResults) {
   	if (result == Statement.SUCCESS_NO_INFO) {
       	System.out.println("SQL执行成功，但没有返回结果信息。");
       } else if (result == Statement.EXECUTE_FAILED) {
           System.out.println("SQL执行失败。");
       } else {
           System.out.println("受影响的行数: " + result);
       }
   }
   ```

注意事项和标准：

1. **Statement的可重用性**：通常情况下，`Statement`对象可以重复使用，也就是说，可以多次使用相同的`Statement`对象来执行不同的SQL语句。
2. **关闭前可重用**：在关闭`Statement`之前，可以重复使用它。一旦调用了`Statement`的`close`方法关闭了`Statement`，它就不再可重用，任何后续的操作都会导致`SQLException`。
3. **一次性性质**：每次执行`Statement`的`executeQuery`、`executeUpdate`或`execute`方法时，它会执行一次SQL查询或更新操作，然后返回相应的结果。这意味着，每次执行这些方法后，`Statement`会清除之前的执行状态，以便执行新的SQL语句。
4. **PreparedStatement和CallableStatement的可重用性**：`PreparedStatement`和`CallableStatement`对象通常比普通的`Statement`更具可重用性。可以多次执行相同的预编译SQL语句，只需不断为参数赋新值，然后执行即可。这是因为`PreparedStatement`和`CallableStatement`在编译阶段预先准备了SQL语句，只需在执行时提供参数值即可。
## 3、JDBC的高级实践
### 1. 事务

JDBC（Java Database Connectivity）允许管理数据库的事务。事务是一组数据库操作，这些操作要么全部成功执行，要么全部失败回滚。JDBC提供了一些方法来启动、提交和回滚事务，并确保数据库操作的一致性和可靠性。以下是关于JDBC事务管理的示例：

```java
public static void main(String[] args) {
        String dbUrl = "jdbc:derby://localhost:2023/qdu;create=true";
        try(Connection connection = DriverManager.getConnection(dbUrl);){
            // 手动启动事务
            connection.setAutoCommit(false);
            // TODO 利用connection进行DDL或DML
            // ...
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 回滚事务
                if (connection != null) {
                    connection.rollback();
                    System.out.println("事务已回滚");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
```

### 2.连接池

**定义**： JDBC连接池是一个数据库连接的缓存池，它维护了一组数据库连接，这些连接可以被应用程序重复使用。连接池通过预先创建一些数据库连接并将它们保存在池中，以减少连接的创建和销毁开销。应用程序在需要与数据库进行交互时，可以从池中获取一个可用的连接，用于执行数据库操作。操作完成后，连接被释放回池中，而不是关闭，以便以后再次使用。

**实际意义**： JDBC连接池的实际意义在于提供了以下几个方面的好处：

1. **提高性能**：连接池可以避免频繁地创建和销毁数据库连接，减少了连接的开销，从而提高了数据库访问的性能。连接可以被重复使用，而不是每次都重新创建。
2. **资源管理**：连接池能够有效地管理和控制数据库连接的数量。可以配置连接池的最大连接数和最小连接数，以避免资源耗尽或浪费。
3. **避免连接泄漏**：连接池可以确保在应用程序代码中适当地释放连接，从而避免了连接泄漏的问题，即未正确关闭连接而导致资源泄漏。
4. **提高可扩展性**：连接池使应用程序更容易进行扩展，因为它们可以管理连接的分配和回收，以适应不同负载和并发情况。
5. **优化数据库连接的使用**：连接池可以管理连接的状态和健康状况，以确保可用连接的有效使用。某些连接池还提供了监控和统计功能，可用于分析连接使用情况。

几个主流的Java数据库连接池（JDBC连接池）：

|     连接池名称      | 优势                                                         | 区别和特点                                                   |
| :-----------------: | ------------------------------------------------------------ | ------------------------------------------------------------ |
|      HikariCP       | - 高性能：HikariCP 被认为是性能最好的连接池之一。 - 轻量级：占用内存较少。 - 自动化管理：自动优化连接池的配置。(Springboot2版本后默认使用) | - 高性能：HikariCP 提供了卓越的性能，适用于高并发应用。 - 轻量级：相比其他连接池，它的内存占用更少。 - 高度自动化：配置简单且自动化程度高。 |
| Apache Commons DBCP | - 稳定：成熟且稳定的连接池库。 - 易于配置：简单的配置和使用。 | - 稳定性：DBCP 是 Apache 项目的一部分，经过长时间的发展和维护。 - 简单易用：适用于一般应用程序，易于配置。 |
|        C3P0         | - 高度可配置：丰富的配置选项，适应不同的需求。 - 长时间运行的应用：适用于需要长时间运行的应用。 | - 配置选项：C3P0 提供了大量的配置选项，可以根据需求进行调整。 - 长时间运行的应用：适用于需要长时间保持连接的应用。 |
|  Tomcat JDBC Pool   | - 高性能：与Tomcat服务器集成紧密，性能不错。 - Tomcat集成：适用于Tomcat服务器上的应用。 | - 高性能：与Tomcat服务器集成，性能表现良好。 - Tomcat集成：最适合在Tomcat环境中使用，对Tomcat支持最好。 |
|        Druid        | - 强大的监控和统计功能：提供了丰富的监控和统计特性。 - 安全性：提供了一些安全特性，如SQL注入防护。 | - 监控和统计：Druid 提供了强大的监控和统计功能，用于分析连接池性能。 - 安全性：包含一些安全特性，有助于防止SQL注入攻击。 |

#### 1、HikariCP的引入、创建、使用

按照标准流程，去Maven仓库查询依赖。

```xml
<!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>4.0.3</version>
</dependency>

```

在Java中创建连接池

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydatabase");
config.setUsername("yourUsername");
config.setPassword("yourPassword");

HikariDataSource dataSource = new HikariDataSource(config);

```

|         配置属性          | 属性描述                                                     |     构造器默认值     | 翻译后的默认值 | Validate 重置                                                |
| :-----------------------: | ------------------------------------------------------------ | :------------------: | :------------: | ------------------------------------------------------------ |
|        autoCommit         | 自动提交从池中返回的连接                                     |         true         |      true      | -                                                            |
|     connectionTimeout     | 等待来自池的最大毫秒数                                       | SECONDS.toMillis(30) |     30000      | 如果小于250毫秒，则被重置回30秒                              |
|        idleTimeout        | 连接允许在池中闲置的最长时间                                 | MINUTES.toMillis(10) |     600000     | 如果idleTimeout+1秒 > maxLifetime 且 maxLifetime > 0，则会被重置为0 （代表永远不会退出）， 如果idleTimeout != 0且小于10秒， 则会被重置为10秒 |
|        maxLifetime        | 池中连接最长生命周期                                         | MINUTES.toMillis(30) |    1800000     | 如果不等于0且小于30秒 则会被重置回30分钟                     |
|    connectionTestQuery    | 如果驱动程序支持JDBC4， 强烈建议不要设置此属性               |         null         |      null      | -                                                            |
|        minimumIdle        | 池中维护的最小空闲连接数                                     |          -1          |       10       | minIdle 小于0 或者 minIdle 大于 maxPoolSize， 则被重置为 maxPoolSize |
|      maximumPoolSize      | 池中最大连接数，包括闲置和使用中的连接                       |          -1          |       10       | 如果 maxPoolSize 小于 1， 则会被重置。 当 minIdle <= 0被重置为 DEFAULT_POOL_SIZE 则为10， 如果 minIdle > 0 则重置为 minIdle 的值 |
|      metricRegistry       | 该属性允许指定一个 Codahale / Dropwizard MetricRegistry 的实例， 供池使用以记录各种指标 |         null         |      null      | -                                                            |
|    healthCheckRegistry    | 该属性允许指定池使用的 Codahale / Dropwizard HealthCheckRegistry 的实例来报告当前健康信息 |         null         |      null      | -                                                            |
|         poolName          | 连接池的用户定义名称，主要出现在日志记录和 JMX 管理控制台中以识别池和池配置 |         null         |  HikariPool-1  | -                                                            |
| initializationFailTimeout | 如果池无法成功初始化连接， 则此属性控制池是否将 fail fast    |          1           |       1        | -                                                            |
|  isolateInternalQueries   | 是否在其自己的事务中隔离内部池查询， 例如连接活动测试        |        false         |     false      | -                                                            |
|    allowPoolSuspension    | 控制池是否可以通过 JMX 暂停和恢复                            |        false         |     false      | -                                                            |
|         readOnly          | 从池中获取的连接是否默认处于只读模式                         |        false         |     false      | -                                                            |
|      registerMbeans       | 是否注册 JMX 管理 Bean（MBeans）                             |        false2        |     false      | -                                                            |
|          catalog          | 为支持 catalog 概念的数据库设置默认 catalog                  |    driver default    |      null      | -                                                            |
|     connectionInitSql     | 该属性设置一个SQL语句，在将每个新连接创建后， 将其添加到池中之前执行该语句 |         null         |      null      | -                                                            |
|      driverClassName      | HikariCP 将尝试通过仅基于 jdbcUrl 的 DriverManager 解析驱动程序， 但对于一些较旧的驱动程序，还必须指定 driverClassName |         null         |      null      | -                                                            |
|   transactionIsolation    | 控制从池返回的连接的默认事务隔离级别                         |         null         |      null      | -                                                            |
|     validationTimeout     | 连接将被测试活动的最大时间量                                 | SECONDS.toMillis(5)  |      5000      | 如果小于250毫秒，则会被重置回5秒                             |
|     scheduledExecutor     | 此属性允许设置将用于各种内部计划任务的 java.util.concurrent.ScheduledExecutorService 实例 |         null         |      null      | -                                                            |
|       threadFactory       | 此属性允许设置将用于创建池使用的所有线程的 java.util.concurrent.ThreadFactory 的实例 |         null         |      null      | -                                                            |
|          schema           | 该属性为支持模式概念的数据库设置默认模式                     |    driver default    |      null      | -                                                            |
|        dataSource         | 这个属性允许直接设置数据源的实例被池包装， 而不是让 HikariCP 通过反射来构造它 |         null         |      null      | -                                                            |
|  leakDetectionThreshold   | 记录消息之前连接可能离开池的时间量， 表示可能的连接泄漏      |          0           |       0        | 如果大于0且不是单元测试， 则进一步判断：(leakDetectionThreshold < SECONDS.toMillis(2) or (leakDetectionThreshold > maxLifetime && maxLifetime > 0)， 会被重置为0 . 即如果要生效则必须>0，而且不能小于2秒， 而且当maxLifetime > 0时不能大于maxLifetime |

一个简单的连接池配置：

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:derby:/path/to/your/database;create=true"); // Derby数据库连接URL
config.setUsername("your_username");
config.setPassword("your_password");
// 最小空闲连接数
config.setMinimumIdle(10); 
// 最大连接数
config.setMaximumPoolSize(20); 
// 关闭自动提交
config.setAutoCommit(false); 

// 创建Hikari数据源
HikariDataSource dataSource = new HikariDataSource(config);
```

以下代码示例，说明了使用线程池的优势：

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PerformanceComparison {

    public static void main(String[] args) {
        // 使用HikariCP连接池
        testWithHikariCP();

        // 不使用连接池
        testWithoutHikariCP();
    }

    // 使用HikariCP连接池
    private static void testWithHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:derby://localhost:2023/qdu;create=true");
        config.setMaximumPoolSize(20);
        HikariDataSource dataSource = new HikariDataSource(config);

        // 指定线程数量
        int numThreads = 20; 
        Thread[] threads = new Thread[numThreads];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try (Connection connection = dataSource.getConnection()) {
                    String insertSQL = "INSERT INTO students (name, date_of_birth, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

                    for (int j = 0; j < 50; j++) {
                        preparedStatement.setString(1, "Student " + j);
                        preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                        preparedStatement.setString(3, "student" + j + "@example.com");
                        preparedStatement.setString(4, "1234567890");
                        preparedStatement.setString(5, "Address " + j);
                        preparedStatement.executeUpdate();
                    }

                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("使用Hikari连接池执行消耗: " + (endTime - startTime) + " ms");
    }

    // 不使用连接池
    private static void testWithoutHikariCP() {
        // 指定线程数量
        int numThreads = 20; 
        Thread[] threads = new Thread[numThreads];

        long startTime = System.currentTimeMillis();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:2023/qdu;create=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < numThreads; i++) {
            Connection finalConnection = connection;
            threads[i] = new Thread(() -> {
                try {
                    String insertSQL = "INSERT INTO students (name, date_of_birth, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = finalConnection.prepareStatement(insertSQL);

                    for (int j = 0; j < 50; j++) {
                        preparedStatement.setString(1, "Student " + j);
                        preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                        preparedStatement.setString(3, "student" + j + "@example.com");
                        preparedStatement.setString(4, "1234567890");
                        preparedStatement.setString(5, "Address " + j);
                        preparedStatement.executeUpdate();
                    }

                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("使用传统JDBC连接并发执行消耗: " + (endTime - startTime) + " ms");
    }
}
```

