# 连接池与Mybatis

[TOC]

## 一. 连接池

### 1.Druid_基础篇

Druid 是一个开源的实时数据分析和数据探索工具，它被设计用来处理大规模的数据，提供快速的查询和分析能力。

1. **实时数据分析：** Druid专注于实时数据分析，它可以处理大量的数据，从秒级到分钟级的延迟，提供实时的查询和分析结果。这对于需要快速了解数据趋势和模式的应用非常有用，例如监控、日志分析、仪表板和实时推荐系统等。

2. **分布式架构：** Druid采用分布式架构，可以水平扩展以处理大规模的数据集。它通常包含多个节点，每个节点都有自己的任务，如数据摄取、查询处理和数据存储。这种架构使得Druid能够处理高吞吐量的数据，并提供高可用性。

3. **列式存储：** Druid使用列式存储引擎来存储数据，这种存储方式在分析性能上具有很大优势。它可以快速执行聚合、过滤和查询操作，同时减少了磁盘和内存的读取开销。

4. **SQL支持：** Druid提供了SQL查询接口，这使得用户可以使用熟悉的SQL语言来查询和分析数据。这对于那些熟悉SQL的数据分析师和工程师来说非常方便。

5. **多维数据模型：** Druid支持多维数据模型，允许用户将数据按不同的维度进行切片和切块。这样可以轻松创建复杂的报表和仪表板，以便更好地理解数据。

6. **生态系统：** Druid有一个丰富的生态系统，包括各种数据摄取工具、可视化工具和第三方插件。这使得用户可以根据其特定需求定制和扩展Druid。

7. **用途广泛：** Druid适用于多种应用场景，包括实时监控、日志分析、推荐系统、广告分析、时间序列数据分析等。它已经在许多大型公司和项目中得到广泛应用。

[GitHub - alibaba/druid: 阿里云计算平台DataWorks 团队出品，为监控而生的数据库连接池](https://github.com/alibaba/druid)

#### 开始

首先引入`Druid`的`Maven`依赖：

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid</artifactId>
	<version>1.2.18</version>
</dependency>
```

编写一段简单的代码运行`Druid`数据连接池：

```java
private static String jdbcHost = "jdbc:derby://localhost:22023/qdu";

public static void main(String[] args) throws SQLException {

    DruidDataSource dataSource = new DruidDataSource();
    //druid需指定驱动类
    dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
    dataSource.setUrl(jdbcHost);
    dataSource.setInitialSize(5);
    dataSource.setMinIdle(5);
    dataSource.setMaxActive(20);
    //需指定健康检测执行语句
    dataSource.setValidationQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
    DruidPooledConnection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from students");
    while (resultSet.next()) {
        System.out.println(resultSet.getLong("id"));
        System.out.println(resultSet.getString("name"));
    }

    resultSet.close();
    statement.close();
    connection.close();
    dataSource.close();
}
```

#### Druid属性

| 配置项                        | 功能                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| url                           | 数据库连接的URL。这是数据库服务器的地址和端口以及数据库名称的组合，用于指定连接到哪个数据库。 |
| username                      | 数据库用户名，用于登录数据库。                               |
| password                      | 数据库密码，用于登录数据库。                                 |
| filters                       | 连接池的过滤器配置，这里设置为"stat"，用于启用Druid的统计功能。 |
| maxActive                     | 连接池中允许的最大活动连接数。当连接池达到这个限制时，后续的连接请求将被阻塞等待。 |
| initialSize                   | 连接池的初始连接数。连接池将在启动时创建这么多连接，并随后按需增加。 |
| maxWait                       | 在连接池耗尽且达到最大活动连接数时，等待可用连接的最长时间（以毫秒为单位）。 |
| minIdle                       | 连接池中保持的最小空闲连接数。如果连接池中的连接数少于这个值，连接池会自动创建新连接。 |
| timeBetweenEvictionRunsMillis | 定期运行的线程检查连接池中的连接是否过期（闲置时间过长），以及是否需要移除。这个属性指定了检查的时间间隔（以毫秒为单位）。 |
| minEvictableIdleTimeMillis    | 连接池中的连接在闲置多长时间后可以被移除（以毫秒为单位）。   |
| testWhileIdle                 | 是否在连接空闲时进行连接健康检查。如果设置为true，连接池会定期执行`validationQuery`以验证连接是否有效。 |
| testOnBorrow                  | 是否在从连接池借用连接时进行连接健康检查。如果设置为true，连接池会在每次获取连接时执行`validationQuery`以验证连接是否有效。 |
| testOnReturn                  | 是否在归还连接到连接池时进行连接健康检查。如果设置为true，连接池会在连接归还时执行`validationQuery`以验证连接是否有效。 |
| poolPreparedStatements        | 是否启用预编译语句池。如果设置为true，连接池将缓存预编译的SQL语句，以提高性能。 |
| maxOpenPreparedStatements     | 预编译语句池的最大大小。当启用预编译语句池时，这个属性指定了最大缓存的预编译语句数量。 |
| asyncInit                     | 是否启用异步初始化。如果设置为true，连接池将在后台异步初始化，以加快应用程序的启动速度。 |

## 二. XML

XML（可扩展标记语言，Extensible Markup Language）是一种用于标记数据的文本格式。它是一种通用的标记语言，用于表示结构化的信息以及数据的存储和传输。XML的设计目标是具有自我描述性、可扩展性和通用性，因此它在各种领域和应用中得到广泛使用。

### 特点

1. **自我描述性**: XML使用标签来描述数据的含义和结构。每个标签都有一个开始标签和一个结束标签，它们将数据包裹起来并指示数据的类型和关系。例如：

   ```xml
   <person>
       <name>Johnson Gee</name>
       <age>27</age>
   </person>
   ```

2. **可扩展性**: XML允许用户定义自己的标签和数据结构，以满足特定的需求。这意味着XML可以适应不同的应用和数据模型。

3. **通用性**: XML不限制数据的类型或内容，可以用于表示文本、数字、日期、图像、配置文件等各种数据类型。

4. **平台无关性**: XML是独立于平台和编程语言的，可以在不同的系统和应用之间轻松传递数据。

5. **数据交换**: XML常用于数据交换和数据存储，例如在Web服务、配置文件、数据库导出/导入等方面。

6. **可读性**: XML文档通常具有良好的可读性，因为它使用了类似HTML的标签语法，并且可以通过文本编辑器进行编辑和查看。

7. **命名空间**: XML支持命名空间，允许多个XML文档中使用相同的元素名称，但具有不同的含义。

8. **验证和解析**: XML文档可以使用**DTD**（文档类型定义）或XML模式（**XSD**）进行验证，以确保其结构和内容符合规范。XML解析器可用于读取和处理XML数据。

#### DTD

```dtd
<!-- student.dtd -->
<!ELEMENT students (student+)>
<!ELEMENT student (name, age, gender)>
<!ELEMENT name (#PCDATA)>
<!ELEMENT age (#PCDATA)>
<!ELEMENT gender (#PCDATA)>
```

```xml-dtd
<!-- students.xml -->
<!DOCTYPE students SYSTEM "student.dtd">
<students>
  <student>
    <name>John Doe</name>
    <age>20</age>
    <gender>Male</gender>
  </student>
  <student>
    <name>Jane Smith</name>
    <age>22</age>
    <gender>Female</gender>
  </student>
</students>
```

#### XSD

```xml
<!-- student.xsd -->
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="students">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="student" maxOccurs="unbounded">
          <xs:complexType>
            <xs:sequence>
              <xs:element name="name" type="xs:string"/>
              <xs:element name="age" type="xs:int"/>
              <xs:element name="gender" type="xs:string"/>
            </xs:sequence>
          </xs:complexType>
        </xs:element>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
</xs:schema>
```

```xml
<!-- students.xml -->
<students xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="student.xsd">
  <student>
    <name>John Doe</name>
    <age>20</age>
    <gender>Male</gender>
  </student>
  <student>
    <name>Jane Smith</name>
    <age>22</age>
    <gender>Female</gender>
  </student>
</students>
```

DTD（文档类型定义）和XSD（XML模式定义）都用于定义XML文档的结构和约束，但它们之间存在一些关键区别：

1. **语法和格式**:
   - DTD使用一种相对简单的语法，它包含在XML文档内部或通过外部引用进行定义。
   - XSD使用XML语法本身来定义模式，它通常是一个独立的XML文档，其结构比DTD更复杂。
2. **数据类型支持**:
   - DTD的数据类型支持相对有限，主要包括文本数据、元素、属性和简单的约束。
   - XSD提供了更丰富的数据类型支持，可以定义复杂的数据类型，如日期、时间、数字、枚举等，以及自定义数据类型。
3. **约束能力**:
   - DTD的约束能力有限，不能提供详细的约束规则，如数据范围、唯一性约束等。
   - XSD具有更强大的约束能力，可以定义元素和属性的详细规则，包括最小和最大出现次数、默认值、唯一性约束、键和引用等。
4. **命名空间支持**:
   - DTD的命名空间支持有限，不够灵活，难以处理复杂的命名空间问题。
   - XSD提供了更强大的命名空间支持，允许更好地管理和隔离不同命名空间中的元素和类型定义。
5. **可读性**:
   - DTD通常比XSD更简洁和易读，因为其语法相对简单。
   - XSD的XML格式使其更具结构，但也更复杂。
6. **适用性**:
   - DTD适用于较简单的XML文档结构和约束需求，或者在某些旧系统中广泛使用。
   - XSD更适用于复杂的XML文档结构和更丰富的约束需求，是一种更现代和强大的模式定义方法。

## 三. Mybatis

### 1.添加mybatis依赖

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.11</version>
</dependency>
```

### 2.配置连接池（可选）

```java
/**
 * 继承于Mybatis的PooledDataSourceFactory
 * @author 冀忠祥
 * @date 2023/9/18 13:43
 */
public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        // 设置连接池配置，如url、用户名、密码等
        dataSource.setUrl("jdbc:derby://localhost:22023/qdu");
        //dataSource.setUsername("root");
        //dataSource.setPassword("password");

        // 其他连接池配置
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setValidationQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");

        // 设置DruidDataSource为数据源
        this.dataSource = dataSource;
    }
}
```

### 3.配置Mybatis

创建Mybatis的配置文件（通常是`mybatis-config.xml`），并在其中使用`DruidDataSourceFactory`作为数据源工厂。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!-- 以下内容三选一 -->
            <!-- 直接使用JDBC作为数据源工厂 -->
            <dataSource type="POOLED">
                <property name="driver" value="org.apache.derby.jdbc.ClientDriver"/>
                <property name="url" value="jdbc:derby://localhost:22023/qdu"/>
                <property name="username" value=""/>
                <property name="password" value=""/>
            </dataSource>
            <!-- 使用步骤2手动创建的Druid连接池作为数据源工厂 -->
            <dataSource type="cn.edu.qdu.DruidDataSourceFactory" />
        </environment>
    </environments>
</configuration>
```

### 4.创建Mybatis映射文件和接口

创建一个MyBatis映射文件（如`StudnetsMapper.xml`）以及对应的Java接口（如`StudnetsMapper.java`），定义SQL映射和数据库操作。

#### （1）Lombok - 番外篇

Lombok是一个Java库，它通过注解来减少代码的冗余和重复，提高代码的可读性和可维护性。Lombok使得开发人员可以用更简洁的方式编写Java类，省去了很多常规的样板代码。

##### 常见使用方法：

**@Getter / @Setter：** `@Getter` 和 `@Setter` 注解用于自动生成类的 getter 和 setter 方法，减少了手动编写这些方法的工作。你可以在类级别或字段级别使用这些注解。

**@ToString：** `@ToString` 注解自动生成 `toString()` 方法，方便对象的字符串表示。你可以自定义生成的字符串格式。

**@Data：** `@Data` 注解集成了 `@Getter`、`@Setter`、`@ToString`、`@EqualsAndHashCode` 等，用于生成包含所有这些方法的类。

**@Builder：** `@Builder` 注解生成构建器模式的代码，使得对象创建更加灵活和可读性强。

##### 开始：

1. 引入lombok依赖

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

2. 在idea内安装lombok插件

   ![image-20230918141647235](C:\Users\JohnsonChi\OneDrive\工作文件\青岛大学\2023秋季学期\课件\第五节_连接池与Mybatis.assets\image-20230918141647235.png)

   

3. 在idea内启用注解处理

   ![image-20230918141747580](C:\Users\JohnsonChi\OneDrive\工作文件\青岛大学\2023秋季学期\课件\第五节_连接池与Mybatis.assets\image-20230918141747580.png)

#### （2）Student.java

首先定义一个与数据库Students表对应的实体类，首先我们在`cn.edu.qdu`下建立`domain`包结构用于存储所有的实体类，然后在`domain`下创建Student类。（PS：一般来说实体类及其属性的名称应该是其对应数据库表名、列名的驼峰式命名结果。因为上节课我们把学生表命名为了Students，实际应该是Student。算了改吧，将错就错。命名不一致也不会影响任何执行。）

```java
import java.util.Date;

public class Student {
    private int id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

```

使用lombok省略后：

```java

import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
public class Student {

    private int id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    
}
```




#### （3）StudnetsMapper.java

```java
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
    
}
```
#### （4）StudentsMapper.xml

在项目`resources`目录下创建文件夹`mapper`，在该文件夹内创建`StudentsMapper.xml`文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.edu.qdu.mapper.StudentsMapper">

    <resultMap id="studentResultMap" type="cn.edu.qdu.domain.Student">
        <id property="id" column="id" />
        <result property="name" column="name" />
        <result property="dateOfBirth" column="date_of_birth" />
        <result property="email" column="email" />
        <result property="phoneNumber" column="phone_number" />
        <result property="address" column="address" />
    </resultMap>

    <!-- 查询所有学生 -->
    <select id="selectStudents" resultMap="studentResultMap">
        SELECT * FROM STUDENTS
    </select>

    <!-- 根据学生ID查询学生信息 -->
    <select id="getStudentById" parameterType="int" resultType="cn.edu.qdu.domain.Student">
        SELECT id, name, date_of_birth, email, phone_number, address
        FROM students
        WHERE id = #{id}
    </select>

    <!-- 插入一条学生记录 -->
    <insert id="insertStudent" parameterType="cn.edu.qdu.domain.Student">
        INSERT INTO students (name, date_of_birth, email, phone_number, address)
        VALUES (#{name}, #{dateOfBirth}, #{email}, #{phoneNumber}, #{address})
    </insert>

    <!-- 更新学生信息 -->
    <update id="updateStudent" parameterType="cn.edu.qdu.domain.Student">
        UPDATE students
        SET name = #{name}, date_of_birth = #{dateOfBirth},
        email = #{email}, phone_number = #{phoneNumber}, address = #{address}
        WHERE id = #{id}
    </update>

    <!-- 根据学生ID删除学生记录 -->
    <delete id="deleteStudentById" parameterType="int">
        DELETE FROM students
        WHERE id = #{id}
    </delete>

</mapper>

```

### 5.将Mapper配置入mybatis-config.xml内

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 数据源配置 -->
    
    <!-- 映射文件路径 -->
    <mappers>
        <mapper resource="mapper/StudentsMapper.xml" />
    </mappers>
</configuration>

```

### 6.编写测试代码

```java
public static void main(String[] args) throws IOException {
    // 加载MyBatis配置文件
    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
    /**
     * 这部分代码从类路径中加载名为 "mybatis-config.xml" 的 MyBatis 配置文件，
     * 并使用 SqlSessionFactoryBuilder 构建了一个 SqlSessionFactory 实例。
     * SqlSessionFactory 是 MyBatis 中非常重要的一个工厂，它用于
     * 创建 SqlSession 对象，从而与数据库进行交互。 
     * 
     * 通常情况下，SqlSessionFactory 对象适合使用单例模式进行缓存，
     * 因为它的创建和初始化过程相对较重，而且一个应用程序通常只需要
     * 一个全局的 SqlSessionFactory 实例来管理数据库连接和 SQL 映射。
     */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

    /**
     * 使用 sqlSessionFactory 创建了一个 SqlSession 对象。
     * SqlSession 是 MyBatis 中用于执行 SQL 操作的主要接口。
     * 通常，它代表了与数据库的一次会话。在使用完后，通常会
     * 通过 try-with-resources 语句来自动关闭 SqlSession，
     * 以确保资源得以释放。
     */
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        // 获取StudentsMapper接口的实例
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        // 查询用户
        List<Student> allStudents = studentsMapper.getAllStudents();
        allStudents.forEach(System.out::println);

    }
}
```

### 7.映射文件语法

Mybatis 的映射文件使用一种特定的语法来定义 SQL 查询、插入、更新和删除等数据库操作。

#### （1）SQL 查询语句：

```xml
<select id="selectStudentById" resultType="Student">
    SELECT * FROM students WHERE id = #{id}
</select>
```

- `<select>` 标签用于定义一个查询操作。此外还有`<insert>`、`<update>`、`<delete>`标签。

- `resultType` 属性指定了查询结果的类型。除`resultType`外还有`resultMap`。

- `#{id}` 是参数占位符，用于传递查询参数。

`resultType` 和 `resultMap` 是 Mybatis 中用于处理查询结果映射的两种不同方式，它们有以下区别：

##### 1.resultType

`resultType` 用于指定查询结果的类型，通常是一个 Java Bean 类型。Mybatis 会自动将查询结果映射到指定类型的对象中，**基于结果集的列名和属性名的映射规则（严格相同）**。

例如，假设你有一个 `Student` 类：

```java
public class Student {
    private int id;
    private String name;
    private int age;
    // 省略其他属性和方法
}
```

你可以在 SQL 查询中使用 `resultType` 来指定结果类型：

```xml
<select id="selectStudentById" resultType="Student">
    SELECT * FROM students WHERE id = #{id}
</select>
```

##### 2.resultMap

`resultMap` 允许你更精确地控制结果集到对象的映射，可以**自定义映射规则**。你需要在 Mybatis 配置文件中定义一个 `resultMap`，并在 SQL 查询中引用该映射。

例如，定义一个 `resultMap`：

```xml
<resultMap id="studentResultMap" type="cn.edu.qdu.Student">
    <id property="id" column="id" />
    <result property="name" column="name" />
    <result property="dateOfBirth" column="date_of_birth" />
    <result property="email" column="email" />
    <result property="phoneNumber" column="phone_number" />
    <result property="address" column="address" />
</resultMap>
```

然后，在 SQL 查询中使用 `resultMap`：

```
Student selectStudentById(@Param("id") Long id);
```

```xml
<select id="selectStudentById" resultMap="studentResultMap">
    SELECT * FROM students WHERE id = #{id}
</select>
```

这里你可以自定义查询结果列名和对象属性名之间的映射，不受默认规则的限制。

##### 3.占位符

在 MyBatis 中，`#` 和 `$` 是两种不同类型的参数占位符，它们在 SQL 查询中的使用方式和行为有所不同：

1. `#` 占位符：
   - `#` 占位符是预处理的参数占位符。当你使用 `#` 时，MyBatis 会将参数值以安全的方式嵌入到 SQL 查询中，**避免 SQL 注入攻击**。
   - `#` 占位符在参数值周围添加引号，并将参数值作为字符串进行处理。这意味着它适用于字符串类型的参数，也适用于数字和日期等类型。
   - 例如，`#{name}` 会被转化为 `'John'`，`#{age}` 会被转化为 `30`，`#{birthdate}` 会被转化为 `'1993-01-15'`。
   
2. `$` 占位符：
   - `$` 占位符是直接插值的参数占位符。当你使用 `$` 时，MyBatis 会将参数值直接嵌入到 SQL 查询中，不进行额外的处理。
   
   - `$` 占位符不会为参数值添加引号或进行其他特殊处理，参数值将按照原始数据类型插入到 SQL 查询中。
   
   - 例如，`${name}` 将被插入为 `'John'`，`${age}` 将被插入为 `30`，`${birthdate`} 将被插入为 `'1993-01-15'`。
   

区别总结：

- 使用 `#` 占位符更安全，因为它会预处理参数值，防止 SQL 注入攻击。
- 使用 `$` 占位符更灵活，因为它直接插入参数值，适用于一些特殊需求。
- 使用 `#` 占位符时，参数值的数据类型不影响 SQL 查询的正确性。例如，`#{age}` 可以接受整数或字符串类型的参数。
- 使用 `$` 占位符时，需要确保参数值的数据类型与 SQL 查询中的位置相匹配，否则可能导致语法错误。

在大多数情况下，推荐使用 `#` 占位符，以提高查询的安全性。只有在某些需要直接插入参数值的情况下，才使用 `$` 占位符。

##### 4.@Param注解

`@Param` 注解是 MyBatis 中的一个注解，用于**指定方法参数的名称**，以在 XML 映射文件中引用这些参数。

**当方法需要传入多个参数时，需要添加@Param注解。**


#### （2）条件判断（IF 标签）：

```java
List<Student> selectStudentsByCondition(@Param("name") String name);
```

```xml
<select id="selectStudentsByCondition" resultType="Student">
    SELECT * FROM students
    <where>
        <if test="name != null and name != '' ">
            AND name = #{name}
        </if>
    </where>
</select>
```

- `<where>`根据条件动态生成查询条件。

  `<where>` 标签会自动去除生成的条件语句中的第一个 `AND`（或者 `OR`），以避免不必要的语法错误。

  `<where>` 标签通常与 `<if>` 标签或其他条件判断标签一起使用，以根据不同的条件动态生成查询条件。

- `<if>` 标签用于根据条件动态生成 SQL 片段。

- `test` 属性指定了条件表达式，如果条件为真，则包含 `<if>` 内的 SQL 片段。

#### （3）循环迭代（FOR EACH 标签）：

```
List<Student> selectStudentsInList(@Param("ids") List<Long> ids);
```

```xml
<select id="selectStudentsInList" resultType="Student">
    SELECT * FROM students
    WHERE id IN
    <foreach collection="ids" item="id" open="(" separator="," close=")">
        #{id}
    </foreach>
</select>
```

- `<foreach>` 标签用于迭代一个集合，并在 SQL 查询中生成相应的内容。
- `collection` 属性指定了要迭代的集合。
- `item` 属性指定了迭代的当前元素。
- `open` 属性指定了循环的开始部分。
- `separator` 属性指定了元素之间的分隔符。
- `close` 属性指定了循环的结束部分。

#### （4）动态 SQL（choose、when、otherwise 标签）：

```
List<Student> selectStudentByCondition(@Param("phoneNumber") String phoneNumber);
```

```xml
<select id="selectStudentByCondition" resultType="Student">
    SELECT * FROM students
    <choose>
        <when test="phoneNumber != null and phoneNumber != '' ">
            WHERE phone_number = #{phoneNumber}
        </when>
        <otherwise>
            WHERE 1=1
        </otherwise>
    </choose>
</select>
```

- `<choose>` 标签用于从多个条件中选择一个。

- `<when>` 标签用于定义条件分支。

- `<otherwise>` 标签用于定义默认分支。

### 8.Mybatis缓存机制

MyBatis的缓存机制分为一级缓存（Local Cache）和二级缓存（Second Level Cache）两种。

1. **一级缓存（Local Cache）**：

   - 一级缓存是MyBatis的默认缓存，它是基于SqlSession级别的缓存，也就是在同一个SqlSession中执行的SQL语句可以共享这个缓存。
   - 当执行查询语句时，查询的结果会被存储在一级缓存中，以键值对的方式存储，其中键是SQL语句的ID，值是查询的结果。
   - 如果在同一个SqlSession中多次执行相同的SQL查询，MyBatis会首先检查一级缓存，如果找到了相同的查询，直接从缓存中返回结果，而不会再次执行数据库查询。
   - 一级缓存在SqlSession关闭时会被清空，或者可以通过`clearCache`方法手动清除。

   ```
   javaCopy codeSqlSession sqlSession = sqlSessionFactory.openSession();
   User user1 = sqlSession.selectOne("getUserById", 1); // 查询并将结果放入一级缓存
   User user2 = sqlSession.selectOne("getUserById", 1); // 直接从一级缓存中获取结果，不再执行查询
   ```

2. **二级缓存（Second Level Cache）**：

   - 二级缓存是在SqlSessionFactory级别的缓存，多个SqlSession之间可以共享这个缓存。
   - 二级缓存在多个SqlSession之间共享相同的数据。当一个SqlSession执行查询并将结果存入二级缓存时，其他SqlSession可以从缓存中获取相同的数据。
   - 二级缓存需要在MyBatis的配置文件中进行显式配置，并且需要在相应的Mapper中启用缓存。配置和启用方式如下：

   ```xml
   <!-- 在MyBatis配置文件中启用二级缓存 -->
   <settings>
       <setting name="cacheEnabled" value="true"/>
   </settings>
   
   <!-- 在Mapper接口中启用缓存 -->
   <mapper namespace="com.example.UserMapper">
       <cache/>
       <!-- Mapper的其他配置 -->
   </mapper>
   ```

   - 二级缓存使用时需要注意，**存入缓存的对象必须是可序列化的**，因为缓存可以跨进程或服务器使用。

   ```java
   // 获取SqlSessionFactory
   SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
   SqlSession sqlSession1 = sqlSessionFactory.openSession();
   // 查询并将结果放入二级缓存
   User user1 = sqlSession1.selectOne("getUserById", 1); 
   // 提交事务，将数据存入二级缓存
   sqlSession1.commit(); 
   
   
   SqlSession sqlSession2 = sqlSessionFactory.openSession();
   // 从二级缓存中获取结果，不再执行查询
   User user2 = sqlSession2.selectOne("getUserById", 1); 
   ```

注意事项：

- 二级缓存的使用需要小心，不适用于所有情况。在一些高并发、频繁变更的数据环境下，可能会导致数据一致性问题，因此需要谨慎使用。
- 一级缓存和二级缓存都是可配置的，可以根据应用需求来选择是否启用以及如何配置缓存。
- 缓存的生命周期受到事务的影响，如果在事务内更新了数据，相关缓存会失效，以确保数据的一致性。

MyBatis的缓存机制可以显著提高查询性能，但在配置和使用时需要谨慎考虑缓存的清除策略和数据一致性问题。

**序列化**：

1. **Serializable接口**：Java中，要使一个类的实例可序列化，需要实现`java.io.Serializable`接口，这是一个标记接口，没有需要实现的方法。当类实现了这个接口时，编译器会知道该类的实例可以被序列化。
2. **对象的所有字段**：在一个可序列化对象中，所有的字段，包括私有字段，都应该是可序列化的。如果某个字段引用了不可序列化的对象，那么整个对象都无法序列化，这可能会导致序列化异常。
3. **transient关键字**：可以使用`transient`关键字来标记一个字段，表示不希望将其序列化。这在某些情况下很有用，例如对于临时计算字段或不应该被序列化的字段。
4. **版本控制**：为了防止反序列化时的版本冲突，建议在可序列化类中声明一个`serialVersionUID`字段。如果你不显式声明它，Java会根据类的结构自动生成一个版本号，但在某些情况下可能会导致问题。
