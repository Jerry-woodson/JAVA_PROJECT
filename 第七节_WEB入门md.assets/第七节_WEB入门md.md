# Web入门

[TOC]

## 一、请求

###  （一）请求（Request）的定义
   - 请求是客户端向服务器发送的一种HTTP消息，用于请求特定资源或执行特定操作。这个资源可以是一个网页、图片、文件或任何其他服务器上的数据。
### （二）请求的组成部分
   - 请求行（Request Line）：请求的第一部分，包括HTTP方法、请求的URL和HTTP协议版本。常见的HTTP方法包括GET、POST、PUT、DELETE等。
   - 请求头部（Request Headers）：包含了关于请求的元信息，如浏览器类型、接受的数据类型、Cookie等。常见的请求头包括：
     - User-Agent：浏览器或客户端的标识。
     - Accept：指定客户端可以接受的响应数据类型。
     - Cookie：包含客户端的会话信息。
     - Authorization：用于身份验证的凭证信息。
   - 请求体（Request Body）：在某些HTTP方法（例如POST和PUT）中，请求体包含了发送到服务器的数据。这通常用于向服务器提交表单数据或JSON等信息。
### （三）请求示例

下面是一个HTTP请求的示例，展示了请求行、请求头部和请求体的组成部分：

```makefile
POST /api/login HTTP/1.1
Host: example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)
Accept: application/json
Content-Type: application/x-www-form-urlencoded
Content-Length: 27

username=johnsonGee&password=12345
```

   - 请求行：`POST /api/login HTTP/1.1` 表示使用POST方法请求服务器上的`/api/login`资源，使用HTTP/1.1协议。
   - 请求头部：包括Host、User-Agent、Accept、Content-Type等。
   - 请求体：包含了以表单形式提交的用户名和密码。

### （四）请求处理流程
   - 当客户端发送请求到服务器时，服务器会根据请求行中的URL和HTTP方法来确定要执行的操作。
   - 服务器会解析请求头部，以了解客户端的需求，如接受的数据类型和身份验证信息。
   - 如果有请求体，服务器会解析其内容，以获取提交的数据。
   - 服务器会根据请求的内容执行相应的业务逻辑，并生成HTTP响应发送回客户端。

理解HTTP请求的组成部分对于开发JavaWeb应用程序和处理客户端与服务器之间的通信至关重要。

### （五）请求类型

   除了POST请求，HTTP协议还定义了其他常见的请求方法，每种方法都具有不同的语义和用途。以下是一些常见的HTTP请求方法：

   1. **GET**：用于从服务器获取资源，通常不应该对服务器状态产生副作用。GET请求将查询参数附加到URL，通常用于请求网页、图片、文本等资源。

      URL的最大长度限制通常在2048个字符左右。

   2. **POST**：用于向服务器提交数据，通常用于创建新资源或执行一些会改变服务器状态的操作。POST请求将数据包含在请求体中，可以发送表单数据、JSON、XML等。

   3. **PUT**：用于更新服务器上的资源，通常是全量更新。客户端发送的数据应该包含完整的资源表示。如果资源不存在，服务器可能会创建它。

   4. **DELETE**：用于请求服务器删除指定的资源。DELETE请求不应该包含请求体，通常通过URL来指定要删除的资源。

### （六）Http状态码

HTTP状态码是服务器响应HTTP请求时返回的三位数字代码，用于指示请求的结果状态。

1. **1xx（信息性状态码）**：表示请求已接收，继续处理。
   - 100 Continue：服务器已收到请求的头部，客户端应继续发送请求体。
   - 101 Switching Protocols：服务器已经理解客户端的请求，正在切换协议。
2. **2xx（成功状态码）**：表示请求被成功接收、理解和接受。
   - **200 OK**：请求成功，服务器返回请求的数据。
   - 201 Created：请求已经成功并且服务器创建了新的资源。
   - 202 Accepted：请求已被接受，但尚未处理完成。
   - 204 No Content：服务器成功处理了请求，但没有返回任何内容。
3. **3xx（重定向状态码）**：表示需要客户端采取进一步的操作才能完成请求。
   - 301 Moved Permanently：请求的资源已永久移动到新的URL。
   - **302 Found**（或者临时重定向）：请求的资源临时移动到新的URL。
   - 304 Not Modified：客户端缓存的资源仍然有效，可以使用缓存的版本。
4. **4xx（客户端错误状态码）**：表示客户端提交的请求有错误或不完整。
   - **400 Bad Request**：客户端请求有语法错误。
   - **401 Unauthorized**：需要身份验证或缺少权限。
   - **403 Forbidden**：服务器拒绝请求，没有权限访问资源。
   - **404 Not Found**：请求的资源不存在。
5. **5xx（服务器错误状态码）**：表示服务器在尝试处理请求时发生错误。
   - **500 Internal Server Error**：服务器遇到了不可预知的错误。
   - **502 Bad Gateway**：服务器作为网关或代理，从上游服务器接收到无效响应。
   - 503 Service Unavailable：服务器暂时无法处理请求，通常是因为过载或维护。
   - 504 Gateway Timeout：服务器作为网关或代理，未能及时从上游服务器接收到响应。

## 二、Tomcat

![img](.\第七节_WEB入门md.assets\144f-khmynua1708316.jpg)

![img](.\第七节_WEB入门md.assets\Tomcat_593-1695633531198-7.png)

### （一）Tomcat基本概念

**Apache Tomcat**（通常简称为Tomcat）是一个开源的Java Servlet容器，用于运行Java Web应用程序。它是由Apache Software Foundation维护和开发的，是一个流行的Web应用服务器和Servlet容器。

1. **Servlet容器**：Tomcat的主要角色之一是作为Servlet容器。Servlet是Java编写的服务器端程序，用于处理客户端的HTTP请求并生成HTTP响应。Tomcat负责管理和运行这些Servlet，以便它们可以响应客户端请求。

2. **JSP容器**：Tomcat还充当JavaServer Pages（JSP）容器。JSP是一种Java技术，用于在服务器上动态生成Web页面。Tomcat编译和执行JSP页面，将它们转换为Servlet并在服务器上执行。

3. **静态文件服务**：Tomcat能够处理静态资源，如HTML文件、图像、CSS和JavaScript文件，使它们能够通过HTTP访问。这使得Tomcat成为Web应用程序的静态资源服务器的理想选择。

4. **HTTP服务器**：Tomcat还可以充当独立的HTTP服务器，用于提供Web应用程序的HTTP访问。虽然它的主要目的是作为Servlet容器，但它可以配置为仅提供静态内容，或者与其他HTTP服务器（如Apache HTTP Server）一起协同工作，以提供完整的Web服务。

5. **连接池和线程管理**：Tomcat具有内置的连接池和线程管理功能，用于处理来自多个客户端的并发请求。这有助于优化Web应用程序的性能和稳定性。

6. **安全性**：Tomcat提供了一系列的安全功能，包括用户身份验证、访问控制、SSL支持等，用于保护Web应用程序的数据和资源。

7. **管理和部署工具**：Tomcat提供了Web界面和命令行工具，用于管理和部署Web应用程序。管理员可以轻松部署、停止、启动或重新加载Web应用程序，以及监视Tomcat的性能。

Tomcat在Java Web中扮演了一个重要的角色，它提供了一个运行Servlet和JSP的环境，使开发人员能够构建动态的、可扩展的Web应用程序。Tomcat的开源性质和广泛的社区支持使其成为Java Web开发的首选容器之一。

### （二）Tomcat下载

| **Apache Tomcat Version 阿帕奇雄猫版本** | **Latest Released Version 最新发布版本** | **Supported Java Versions 支持的 Java 版本**                 |
| :--------------------------------------- | :--------------------------------------- | :----------------------------------------------------------- |
| 11.0.x                                   | 11.0.0-M11 (alpha) 11.0.0-M11（阿尔法）  | 21 and later 21 及更高版本                                   |
| 10.1.x                                   | 10.1.13                                  | 11 and later 11 及更高版本                                   |
| 10.0.x (superseded) 10.0.x（已取代）     | 10.0.27 (superseded) 10.0.27（已取代）   | 8 and later 8 及更高版本                                     |
| 9.0.x                                    | 9.0.80                                   | 8 and later 8 及更高版本                                     |
| 8.5.x                                    | 8.5.93                                   | 7 and later 7 及更高版本                                     |
| 8.0.x (superseded) 8.0.x（已取代）       | 8.0.53 (superseded) 8.0.53（已取代）     | 7 and later 7 及更高版本                                     |
| 7.0.x (archived)                         | 7.0.109 (archived) 7.0.109（已存档）     | 6 and later 6 及更高版本 (7 and later for WebSocket) （7 及更高版本适用于 WebSocket） |

[Apache Tomcat® - Apache Tomcat 9 Software Downloads --- Apache Tomcat - Apache Tomcat® 9 软件下载](https://tomcat.apache.org/download-90.cgi)

### （三）Tomcat目录结构

| 目录    | 作用                                                       |
| ------- | ---------------------------------------------------------- |
| bin     | 命令中心（启动命令，关闭命令）                             |
| conf    | 配置中心（端口号，内存大小, 数据源, 日志配置）             |
| lib     | Tomcat 的库文件。Tomcat 运行时需要的 jar 包所在的目录。    |
| logs    | 存放日志文件。                                             |
| temp    | 存储临时产生的文件，即缓存。                               |
| webapps | 存放项目的文件，web 应用放置到此目录下浏览器可以直接访问。 |
| work    | 编译以后的 class 文件。                                    |

### （四）Tomcat启动

一般来说，进入`tomcat`的`bin`目录内，执行`startup.bat`即可启动。

此外还有如下几种方式：

#### 1. 命令行启动：

   使用命令行工具进入Tomcat的`bin`目录，然后执行以下命令：

   - 在Windows下：

     ```bash
     startup.bat
     ```

   - 在Linux或Mac下：

     ```bash
     ./startup.sh
     ```

   这将启动Tomcat，并在控制台上显示Tomcat的输出。你可以使用Ctrl+C来停止Tomcat。

#### 2. 服务启动（仅适用于Windows）

   在Windows操作系统中，你可以将Tomcat配置为服务，以便它可以作为Windows服务自动启动和停止。要安装Tomcat服务，可以使用以下命令：

   ```bat
   service.bat install
   ```

   安装完成后，可以使用以下命令启动Tomcat服务：

   ```bat
   net start Tomcat9
   ```

   要停止Tomcat服务，可以使用以下命令：

   ```bat
   net stop Tomcat9
   ```

   其中，`Tomcat9`是你的Tomcat版本的服务名称，具体名称可能会有所不同。

#### 3. IDE集成启动(推荐)：

   如果你使用集成开发环境（IDE）如Eclipse、IntelliJ IDEA等，通常可以通过IDE来启动和管理Tomcat。在IDE中，你可以配置Tomcat服务器，然后使用IDE的界面启动和停止Tomcat。**这是开发Java Web应用程序时常用的方式，因为IDE能够提供更多的工具和调试支持。**

### （五）乱码问题

1. 首先配置Idea的编码格式：

<img src=".\第七节_WEB入门md.assets\image-20230925141054548.png" alt="image-20230925141054548"  />

   在底部加入如下命令：

   ```bash
   -Dfile.encoding=UTF-8
   ```

2. 修改`tomcat`/`bin`目录内的`catalina.bat`

   在`setlocal`下一行，插入

   ```bash
   set "JAVA_OPTS=%JAVA_OPTS% %JSSE_OPTS%" -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8
   ```

3. 修改`tomcat`/`conf`目录内的`server.xml`

   在其尾部添加：`URIEncoding="UTF-8"`

   ```xml
   <Connector connectionTimeout="20000" port="8081" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8"/>
   ```

4. （JSP）

   在JSP页面内的头部，添加如下代码：

   ```jsp
   <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   ```

5. (Idea控制台乱码)修改`tomcat`/`conf`目录内的`logging.properties`

   以上步骤后如果仍然乱码，则将该文件内的`UTF-8`替换为`GBK`

   **没有乱码的话此步不要执行。**

### （六）IDEA配置Tomcat

1. 添加运行配置

![image-20230925142039471](.\第七节_WEB入门md.assets\image-20230925142039471.png)

在弹出的选项框内，点击左上角的`加号`，找到`Tomcat` -`本地`

![image-20230925142124639](.\第七节_WEB入门md.assets\image-20230925142124639.png)

确认后将展示如下页面：

![image-20230925142223819](.\第七节_WEB入门md.assets\image-20230925142223819.png)

在`应用程序服务器`右侧点击`配置`

![image-20230925142351212](.\第七节_WEB入门md.assets\image-20230925142351212.png)

在`Tomcat主目录`内，选择你本地Tomcat的目录，如：C:\...\apache-tomcat-9.0.80

Idea会自动识别Tomcat的版本号。

![image-20230925142539142](.\第七节_WEB入门md.assets\image-20230925142539142.png)

点击确定后，即可正常使用Tomcat，你也可以为Tomcat配置启动的`HTTP端口`号，如`8080`

![image-20230925142731346](.\第七节_WEB入门md.assets\image-20230925142731346.png)

点击确定后，在底部的服务窗口即可看到Tomcat

![image-20230925142858466](.\第七节_WEB入门md.assets\image-20230925142858466.png)

## 三、JavaWeb

### （一）Servlet的概念和生命周期

**Servlet**是Java编写的服务器端程序，用于处理客户端（通常是浏览器）发送的HTTP请求并生成HTTP响应。Servlet通常用于构建动态的Web应用程序。

#### 1. 概念
   - Servlet是Java类，它通过Java EE容器（如Tomcat）运行在服务器上。
   - Servlet用于处理HTTP请求和响应，通常用于生成动态的Web内容。
   - Servlet遵循Java EE规范，并实现了`javax.servlet.Servlet`接口。
#### 2. 生命周期
   - Servlet的生命周期包括初始化、服务请求和销毁三个主要阶段。
   - **初始化**：当容器第一次加载Servlet时，会调用`init()`方法，用于执行初始化任务，例如加载配置或创建数据库连接。初始化只会在Servlet第一次被加载时执行。
   - **服务请求**：每当有HTTP请求到达与Servlet关联的URL时，容器会调用`service()`方法，该方法根据请求类型（GET、POST等）调用适当的方法（如`doGet()`、`doPost()`等）来处理请求并生成响应。
   - **销毁**：当容器需要卸载Servlet或关闭应用程序时，会调用`destroy()`方法，用于执行清理工作，例如关闭数据库连接或释放资源。销毁只会在Servlet被卸载时执行。

### （二）JSP（JavaServer Pages）的基础知识

**JavaServer Pages（JSP）**是一种用于构建动态Web页面的Java技术。JSP允许在HTML中嵌入Java代码，以便生成动态内容。

1. **概念**：
   - JSP是一种服务器端技术，它允许开发人员将Java代码嵌入到HTML页面中，以便在服务器上动态生成内容。
   - 你可以理解Jsp为Java + html
   - JSP页面的扩展名通常是`.jsp`。
2. **语法**：
   - 在JSP页面中，使用`<% %>`标签来包含Java代码块。
   - 使用`${}`表达式来输出Java变量或表达式的值。
   - 使用`<%= %>`标签来输出表达式的值。
3. **指令**：
   - 使用`<%@ %> `指令来设置页面的属性，如页面的语言、导入的Java包、错误页面等。
4. **标签库**：
   - JSP允许使用自定义标签库（Tag Libraries）来扩展页面的功能。常见的标签库包括JSTL和自定义标签库。

### （三）请求和响应处理

在Servlet和JSP中，请求和响应是核心概念：

1. **请求处理**：
   - 客户端通过HTTP请求发送到服务器的内容，包括URL、HTTP方法（GET、POST等）、请求头、请求体等。
   - Servlet通过获取请求对象（`HttpServletRequest`）来访问请求的所有信息，包括参数、头部、Cookie等。
   
2. **响应生成**：
   - Servlet和JSP用于生成HTTP响应，响应包括HTTP状态码、响应头、响应体等。
   
   - Servlet通过获取响应对象（`HttpServletResponse`）来设置响应的内容，例如响应头、内容类型、响应体等。
   

### （四）JavaWeb项目

#### 1. 目录结构与意义

1. **WEB-INF目录**：
   - `WEB-INF` 目录是Web应用程序中的一个关键目录，它通常包含不希望直接对外部访问的资源。该目录下的内容对外部不可见。
   - `WEB-INF/classes`：用于存放编译后的Java类文件。这些类通常是Servlet、Filter等Web组件的实现。
   - `WEB-INF/lib`：用于存放Web应用程序所需的外部JAR文件（Java库）。
   - `WEB-INF/web.xml`：Web应用程序的部署描述文件（Deployment Descriptor），其中配置了Servlet、Filter、监听器等Web组件的信息。
2. **META-INF目录**：
   - `META-INF` 目录是Java应用程序的元数据目录。在JavaWeb项目中，它通常位于Web应用程序的WAR文件中，用于存放应用程序的元信息。
   - `META-INF/MANIFEST.MF`：用于存放JAR文件的清单信息，包括版本信息、依赖关系等。
3. **静态资源目录**：
   - `css`、`js`、`images` 等目录用于存放Web应用程序的静态资源，如样式表、JavaScript文件和图像。
4. **JSP目录**：
   - `jsp` 目录用于存放JSP（JavaServer Pages）文件，这些文件包含Web页面的视图层代码。
5. **自定义目录**：
   - 除了上述标准目录外，项目可能包括自定义目录，用于组织其他类型的资源、配置文件或Java类。例如，你可以创建 `src` 目录用于存放Java源代码，或者创建 `lib` 目录用于存放自定义的Java库。

```vbscript
webapp
├── css/
│   ├── style.css
│   └── ...
├── js/
│   ├── script.js
│   └── ...
├── images/
│   ├── logo.png
│   └── ...
├── jsp/
│   ├── index.jsp
│   ├── login.jsp
│   └── ...
├── WEB-INF/
│   ├── web.xml
│   ├── classes/
│   │   ├── com/
│   │   │   ├── example/
│   │   │   │   ├── servlets/
│   │   │   │   │   ├── MyServlet.class
│   │   │   │   │   └── ...
│   │   │   │   └── ...
│   │   └── ...
│   └── lib/
│       ├── library1.jar
│       ├── library2.jar
│       └── ...
└── ...

```

#### 2. 创建Javaweb项目

##### （1）直接创建项目（推荐）

注意：不同版本的idea在操作流程上略有不同。

![image-20230925161623457](.\第七节_WEB入门md.assets\image-20230925161623457.png)

根据上图所示，在新建项目时，选择`Java Enterprise`，并在右侧项目模板选择`Web 应用程序`。其他字段按需编辑。点击`下一步`、`完成`即可创建该项目。

部分Idea展示与上图不一致，如：

![img](.\第七节_WEB入门md.assets\format,png.png)

按照图中所示的内容勾选即可。

创建完成后，项目结构如下图所示：

![image-20230925162256460](.\第七节_WEB入门md.assets\image-20230925162256460.png)

##### （2）通过Maven原型创建项目

![image-20230925162434652](.\第七节_WEB入门md.assets\image-20230925162434652.png)

根据上图所示，在新建项目时，选择`Maven`，并在右侧勾选`从原型创建`，在原型列表内选择`org.apache.maven.archetypes:maven-archetype-webapp`。点击`下一步`，按照标准maven流程创建项目即可。

##### （3）创建完成后需要在pom.xml添加对应依赖：

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.3</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
```

#### 3. 部署JavaWeb项目

点击idea右上角的`运行/调试配置`，点击`编辑配置`，在弹出的选项框内左侧选择你添加的`Tomcat`，右侧`部署`内点击`+`号，选择`工件`,将带`exploded`的项目添加进来。

点击确定即可。

![image-20230925171229947](.\第七节_WEB入门md.assets\image-20230925171229947.png)

之后可在`服务`标签内启动、关闭服务器。

### （五）创建一个Servlet

#### servlet 三种创建方式

1. （传统）实现 javax.servlet.Servlet 接口，重写其全部方法。

   - javax.servlet.GenericServlet 实现了 Servlet 接口，并提供了除 service() 方法以外的其他四个方法的简单实现。通过继承 GenericServlet 类创建 Servlet ，只需要重写 service() 方法即可，大大减少了创建 Servlet 的工作量。

   ```java
   package cn.edu.qdu;
   
   import javax.servlet.*;
   import java.io.IOException;
   
   public class MyServlet implements Servlet {
   
       @Override
       public void init(ServletConfig config) throws ServletException {
           // 初始化操作，例如读取配置参数
           String value1 = config.getInitParameter("param1");
           // 建立数据库连接等
           // 这里的代码在Servlet初始化时执行，不会在每个请求中重复执行
       }
   
       @Override
       public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
           // 设置字符编码
           res.setCharacterEncoding("UTF-8");
           // 设置内容类型和字符编码
           res.setContentType("text/html;charset=UTF-8");
           // 处理请求
           PrintWriter writer = res.getWriter();
           writer.write("<h1>你好啊111</h1>");
       }
   
       @Override
       public void destroy() {
           // 清理操作，例如关闭数据库连接、释放资源等
           // 这里的代码在Servlet销毁时执行
       }
   
       @Override
       public ServletConfig getServletConfig() {
           return null;
       }
   
       @Override
       public String getServletInfo() {
           //方法返回一个描述Servlet的字符串，通常用于提供有关Servlet的简要信息。
           //这个方法的返回值可以包括有关Servlet的作者、版本、用途等信息，以帮助开发人员了解和使用该Servlet。
           //返回值不会影响Servlet的行为，它主要用于文档和调试目的。
           return null;
       }
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <servlet>
           <servlet-name>MyServlet</servlet-name>
           <servlet-class>cn.edu.qdu.MyServlet</servlet-class>
           <init-param>
               <param-name>param1</param-name>
               <param-value>value1</param-value>
           </init-param>
           <init-param>
               <param-name>param2</param-name>
               <param-value>value2</param-value>
           </init-param>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>MyServlet</servlet-name>
           <url-pattern>/myservlet</url-pattern>
       </servlet-mapping>
   
   </web-app>
   ```

   

2. （传统）继承 javax.servlet.GenericServlet 抽象类，重写 service() 方法。	



   ```java
   package cn.edu.qdu;
   
   import javax.servlet.*;
   import java.io.IOException;
   
   public class MyGenericServlet extends GenericServlet {
   
       @Override
       public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
           // 设置字符编码
           res.setCharacterEncoding("UTF-8");
           // 设置内容类型和字符编码
           res.setContentType("text/html;charset=UTF-8");
           // 处理客户端请求的代码
           res.setContentType("text/html");
           ServletOutputStream out = res.getOutputStream();
           out.println("<html>");
           out.println("<head><title>My Generic Servlet</title></head>");
           out.println("<body>");
           out.println("<h1>Hello, World! This is a Generic Servlet</h1>");
           out.println("</body>");
           out.println("</html>");
       }
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <servlet>
           <servlet-name>MyGenericServlet</servlet-name>
           <servlet-class>cn.edu.qdu.MyGenericServlet</servlet-class>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>MyGenericServlet</servlet-name>
           <url-pattern>/mygenericservlet</url-pattern>
       </servlet-mapping>
   
   </web-app>
   ```

   

3. （注解）继承 javax.servlet.http.HttpServlet 抽象类，重写 doGet() 或 doPost() 方法。

   - 在 HTTP/1.1 协议中共定义了 7 种请求方式，即 GET、POST、HEAD、PUT、DELETE、TRACE 和 OPTIONS。

   - HttpServlet 针对这 7 种请求方式分别定义了 7 种方法，即 doGet()、doPost()、doHead()、doPut()、doDelete()、doTrace() 和 doOptions()。

   - HttpServlet 重写了 service() 方法，该方法会先获取客户端的请求方式，然后根据请求方式调用对应 doXxx 方法。

```java
package cn.edu.qdu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "MyAnnotationServlet", urlPatterns = {"/myannotationservlet"})
public class MyAnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Hello, World!");
    }
}
```

在这种情况下，不需要显式配置 `web.xml`，因为Servlet使用了 `@WebServlet` 注解来自动配置映射。但要确保项目支持Servlet 3.0或更高版本，以启用注解配置。

**总结:**
上面演示了三种创建 Servlet 的方式，那么在实际开发中，我们究竟该选择哪一种呢？下面我们就来分析和对比一下。

1) Servlet 接口
   通过实现 Servlet 接口创建 Servlet 类，需要重写其全部的方法，比较繁琐，所以我们很少使用该方法创建 Servlet。
2) GenericServlet 类
   GenericServlet 抽象类实现了 Servlet 接口，并对 Servlet 接口中除 service() 方法外的其它四个方法进行了简单实现。通过继承 GenericServlet 创建 Servlet，只需要重写 service() 方法即可，大大减少了创建 Servlet 的工作量。

Generic 是“通用”的意思，正如其名，GenericServlet 是一个通用的 Servlet 类，并没有针对某种场景进行特殊处理，尤其是 HTTP 协议，我们必须手动分析和封装 HTTP 协议的请求信息和响应信息。

3) HttpServlet 类
   HttpServlet 是 GenericServlet 的子类，它在 GenericServlet 的基础上专门针对 HTPP 协议进行了处理。HttpServlet 为 HTTP 协议的每种请求方式都提供了对应的方法，名字为 doXX()，例如：
   处理 GET 请求的方法为 doGet()；
   处理 POST 请求的方法为 doPost()。

正如其名，HttpServlet 就是专为 HTTP 协议而量身打造的 Servlet 类。

在互联网上，人们都是通过 HTTP 协议来访问动态网页的，其中使用最频繁的就是 GET 方式和 POST 方式，因此，我们通常基于 HttpServlet 来创建 Servlet 类，这样就省去了处理 HTTP 请求的过程。

**注意事项：**

- 通过实现 Serlvet 接口或继承 GenericServlet 创建的 Servlet 类无法使用 @WebServlet 注解。

- 使用 @WebServlet 注解配置的 Servlet 类，不要在 web.xml 文件中再次配置该 Servlet 相关属性。若同时使用 web.xml 与 @WebServlet 配置同一 Servlet 类，则 web.xml 中 <servlet-name> 的值与注解中 name 取值不能相同，否则容器会忽略注解中的配置。

#### web.xml与servlet

Servlet 和 web.xml 之间的关系是关于在 Java Web 应用程序中配置和管理 Servlet 的方式。Servlet 是 Java 编写的服务器端程序，用于处理客户端的 HTTP 请求。而 web.xml 是一个 XML 配置文件，用于描述和配置 Servlet 和其他 Web 应用程序组件。

1. web.xml 文件的作用

web.xml 是一个 Web 应用程序的配置文件，它包含了关于 Servlet、过滤器、监听器以及其他 Web 组件的配置信息。它的主要作用是告诉 Web 服务器如何处理不同的 URL 请求，并将它们映射到相应的 Servlet 或其他处理程序。web.xml 文件通常位于 WEB-INF 目录下。

2. 在 web.xml 中配置 Servlet

在 web.xml 中配置 Servlet 可以使用 `<servlet>` 和 `<servlet-mapping>` 标签。

```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>cn.edu.qdu.MyServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/myservlet</url-pattern>
</servlet-mapping>

```

`<servlet>` 标签用于定义 Servlet，包括名称和对应的 Java 类。

`<servlet-mapping>` 标签用于将 Servlet 映射到 URL 路径。

### （六）创建一个JSP

在JSP中，可以使用 `<% %>` 标签来包含Java代码块，以实现动态生成HTML内容。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP内嵌Java代码示例</title>
</head>
<body>
    <h1>通过JSP展示中文内容</h1>
    
    <%-- 在这里插入Java代码 --%>

    <%  // 这是一个Java代码块
        String greeting = "你好，JSP！";
        out.println("<p>" + greeting + "</p>");
    %>

    <%-- 使用Java表达式 --%>
    <p>当前日期和时间是： <%= new java.util.Date() %></p>

    <%-- 使用条件语句 --%>
    <% if (true) { %>
        <p>这是一个条件块。</p>
    <% } else { %>
        <p>这个块不会被显示。</p>
    <% } %>
    
</body>
</html>
```

在上面的示例中，我们使用了 `<% %>` 标签来包含Java代码块，其中包括了一个简单的字符串变量赋值、输出内容、以及条件语句。

在JSP中，内置了一个名为 `out` 的隐式对象，它允许你向输出流中写入内容，这样可以将动态生成的内容显示在HTML页面上。通过 `<%= ... %>` 标签，你可以直接在HTML中嵌入Java表达式，并将其结果显示在页面上。

#### JSP标签（基础）

1. `<%@ page ... %>`：这个标记用于设置JSP页面的指令，通常位于JSP页面的开头。它允许你配置页面的一些属性，如编码、会话管理、缓存等。以下是一些常用的`<%@ page`指令：

   - `<%@ page language="java" %>`：指定JSP页面使用的编程语言，通常是Java。
   - `<%@ page contentType="text/html; charset=UTF-8" %>`：指定页面的内容类型和字符编码。
   - `<%@ page session="true" %>`：启用会话（Session）支持。
   - `<%@ page import="package.class" %>`：导入Java类或包。
   - `<%@ page errorPage="error.jsp" %>`：指定错误页面。
   - `<%@ page isELIgnored="true" %>`：禁用EL（Expression Language）。

2. `<%= ... %>`：这是用于在JSP页面中插入Java表达式的标记。表达式的结果将被自动转义，然后输出到HTML页面上。

```jsp
<p>当前时间：<%= new java.util.Date() %></p>
```

3. `<% ... %>`：这是用于插入Java代码块的标记。在这些标记之间可以包含任意Java代码。

```jsp
<% for (int i = 0; i < 5; i++) { %>
    <p>循环次数：<%= i %></p>
<% } %>
```

### （七）使用Javaweb开发一套学生管理系统

#### 1.创建项目

![image-20230926122305496](.\第七节_WEB入门md.assets\image-20230926122305496.png)

#### 2.引入核心依赖

```xml
<!-- servlet -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

<!-- jsp -->
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.3</version>
    <scope>provided</scope>
</dependency>

<!-- jstl -->
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>

<!-- lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>

<!-- log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>

<!-- derby数据库JDBC -->
<dependency>
    <groupId>org.apache.derby</groupId>
    <artifactId>derbyclient</artifactId>
    <version>10.14.2.0</version>
</dependency>

<!-- druid连接池 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.18</version>
</dependency>

<!-- mybatis持久层框架 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.11</version>
</dependency>
```

#### 3.配置Mybatis

##### 编写DruidDataSourceFactory

位置：src/main/java/cn/edu/qdu/config/DruidDataSourceFactory.java

```java
package cn.edu.qdu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

/**
 * Druid数据库连接池工厂类-提供于mtbatis
 * @author 冀忠祥
 * @date 2023/9/26 12:27
 */
public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        // 设置连接池配置，如url、用户名、密码等
        dataSource.setUrl("jdbc:derby://localhost:22023/qdu");

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

##### 编写单例模式SQL会话工厂类

位置：src/main/java/cn/edu/qdu/utils/SqlSessionFactoryUtil.java

```java
package cn.edu.qdu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;

/**
 * 单例 获取 SqlSessionFactory 的工具类
 * @author 冀忠祥
 * @date 2023/9/26 12:32
 */
public class SqlSessionFactoryUtil {

    /**
     * 饿汉式单例模式，保证在类加载时就初始化
     */
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        //日志log4j初始化
        String log4jResource = "log4j/log4j.properties";
        // MyBatis配置文件的路径
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            PropertyConfigurator.configure(Resources.getResourceAsStream(log4jResource));
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis配置文件加载失败: " + e.getMessage());
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 私有构造方法，防止外部实例化
     */
    private SqlSessionFactoryUtil() {
    }

    /**
     * 提供获取SqlSessionFactory的方法
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}

```

##### 编写MyBatis配置文件

位置：src/main/resources/mybatis/mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!-- 配置日志的具体实现类 -->
        <setting name="logImpl" value="LOG4J"/>
    </settings>
    
    <!-- 数据源配置 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="cn.edu.qdu.config.DruidDataSourceFactory" />
        </environment>
    </environments>

</configuration>

```

##### 编写日志配置文件

位置：src/main/resources/log4j/log4j.properties

```properties
log4j.rootLogger=DEBUG, console

log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-5p %c{1} - %m%n
```



#### 4.编写学生表domain、接口与映射文件

##### domain：

位置：src/main/java/cn/edu/qdu/domain/Student.java

```java
package cn.edu.qdu.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 冀忠祥
 * @date 2023/9/26 12:39
 */
@Data
public class Student {

    /**
     * 学生ID 
     */
    private int id;

    /**
     * 学生姓名 
     */
    private String name;
    
    /**
     * 学生出生年月
     */
    private Date dateOfBirth;

    /**
     * 学生邮箱 
     */
    private String email;

    /**
     * 学生电话 
     */
    private String phoneNumber;

    /**
     * 学生住址 
     */
    private String address;
    
}

```

##### 接口：

位置：src/main/java/cn/edu/qdu/mapper/StudentsMapper.java

```java
package cn.edu.qdu.mapper;

import cn.edu.qdu.domain.Student;

import java.util.List;

/**
 * @author 冀忠祥
 * @date 2023/9/26 12:42
 */
public interface StudentsMapper {

    /**
     * 查询所有学生 
     * @return 学生集合
     */
    List<Student> selectStudents();

    /**
     * 根据学生ID查询学生信息 
     * @param id 学生ID
     * @return 学生对象
     */
    Student getStudentById(int id);

    /**
     * 插入一条学生记录 
     * @param student 学生对象
     * @return 受影响的行数
     */
    int insertStudent(Student student);

    /**
     * 根据学生ID更新学生信息 
     * @param student 学生对象
     * @return 受影响的行数
     */
    int updateStudent(Student student);

    /**
     * 根据学生ID删除学生记录 
     * @param id 学生ID
     * @return 受影响的行数
     */
    int deleteStudentById(int id);
    
}

```

##### 映射文件：

位置：src/main/resources/mapper/StudentsMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.edu.qdu.mapper.StudentsMapper">

    <resultMap id="studentResultMap" type="cn.edu.qdu.domain.Student">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="dateOfBirth" column="date_of_birth"/>
        <result property="email" column="email"/>
        <result property="phoneNumber" column="phone_number"/>
        <result property="address" column="address"/>
    </resultMap>

    <!-- 查询所有学生 -->
    <select id="selectStudents" resultMap="studentResultMap">
        SELECT *
        FROM STUDENTS
    </select>

    <!-- 根据学生ID查询学生信息 -->
    <select id="getStudentById" parameterType="int" resultType="cn.edu.qdu.domain.Student">
        SELECT id, name, date_of_birth, email, phone_number, address
        FROM students
        WHERE id = #{id}
    </select>

    <!-- 插入一条学生记录 -->
    <insert id="insertStudent" parameterType="cn.edu.qdu.domain.Student">
        INSERT INTO students
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="name != null and name != '' ">
                name,
            </if>
            <if test="dateOfBirth != null ">
                date_of_birth,
            </if>
            <if test="email != null and email != '' ">
                email,
            </if>
            <if test="phoneNumber != null and phoneNumber != '' ">
                phone_number,
            </if>
            <if test="address != null and address != '' ">
                address,
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="name != null and name != '' ">
                #{name},
            </if>
            <if test="dateOfBirth != null ">
                #{dateOfBirth},
            </if>
            <if test="email != null and email != '' ">
                #{email},
            </if>
            <if test="phoneNumber != null and phoneNumber != '' ">
                #{phoneNumber},
            </if>
            <if test="address != null and address != '' ">
                #{address},
            </if>
        </trim>
    </insert>

    <!-- 更新学生信息 -->
    <update id="updateStudent" parameterType="cn.edu.qdu.domain.Student">
        UPDATE students
        <trim prefix="SET" suffixOverrides=",">
            <if test="name != null and name != '' ">
                name = #{name},
            </if>
            <if test="dateOfBirth != null ">
                date_of_birth = #{dateOfBirth},
            </if>
            <if test="email != null and email != '' ">
                email = #{email},
            </if>
            <if test="phoneNumber != null and phoneNumber != '' ">
                phone_number = #{phoneNumber},
            </if>
            <if test="address != null and address != '' ">
                address = #{address}
            </if>
        </trim>
        WHERE id = #{id}
    </update>

    <!-- 根据学生ID删除学生记录 -->
    <delete id="deleteStudentById" parameterType="int">
        DELETE
        FROM students
        WHERE id = #{id}
    </delete>

</mapper>

```

##### 维护`映射文件路径`入Mybatis配置文件

位置：src/main/resources/mybatis/mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    
    <settings>
        <!-- 配置日志的具体实现类 -->
        <setting name="logImpl" value="LOG4J"/>
    </settings>

    <!-- 数据源配置 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="cn.edu.qdu.config.DruidDataSourceFactory" />
        </environment>
    </environments>

    <!-- 映射文件路径 -->
    <mappers>
        <mapper resource="mapper/StudentsMapper.xml" />
    </mappers>

</configuration>

```

#### 5.编写index.jsp

```jsp
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="cn.edu.qdu.utils.SqlSessionFactoryUtil" %>
<%@ page import="cn.edu.qdu.mapper.StudentsMapper" %>
<%@ page import="cn.edu.qdu.domain.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生管理</title>
</head>
<body>
<h1>学生管理</h1>
<div style="margin-bottom: 20px;">
    <button onclick="jumpToAdd()">新增学生</button>
</div>
<table border="1">
    <tr>
        <th>学生ID</th>
        <th>学生姓名</th>
        <th>学生出生年月</th>
        <th>学生邮箱</th>
        <th>学生电话</th>
        <th>学生住址</th>
        <th>操作</th>
        <th>操作</th>
    </tr>
    <%
        try(SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession()){
            StudentsMapper mapper = sqlSession.getMapper(StudentsMapper.class);
            List<Student> students = mapper.selectStudents();
            StringBuffer tr = new StringBuffer();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            students.forEach(student -> {
                tr.append("<tr>");
                tr.append("<td>").append(student.getId()).append("</td>");
                tr.append("<td>").append(student.getName()).append("</td>");
                tr.append("<td>").append(sdf.format(student.getDateOfBirth())).append("</td>");
                tr.append("<td>").append(student.getEmail()).append("</td>");
                tr.append("<td>").append(student.getPhoneNumber()).append("</td>");
                tr.append("<td>").append(student.getAddress()).append("</td>");
                tr.append("<td><button onclick=\"edit("+student.getId()+")\" >修改</button></td>");
                tr.append("<td><button onclick=\"remove("+student.getId()+")\" >删除</button></td>");
                tr.append("</tr>");
            });
            out.print(tr);
        }
    %>
</table>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    function jumpToAdd(){
        window.location.href = "add.jsp";
    }
    function edit(studentId){
        // 使用Ajax发送PUT请求到后台Servlet
        window.location.href = "edit.jsp?id="+studentId;
    }
    function remove(studentId){
        // 使用Ajax发送DELETE请求到后台Servlet
        $.ajax({
            type: "DELETE",
            url: "removeStudent?id="+studentId, // 学生Servlet的URL
            success: function (response) {
                // 处理成功响应
                alert("删除成功！");
                // 刷新页面或执行其他操作
                location.reload();
            },
            error: function () {
                // 处理请求错误
                alert("删除失败！");
            }
        });
    }
</script>
</html>

```

呈现效果：

![image-20230926153556755](.\第七节_WEB入门md.assets\image-20230926153556755.png)

#### 6.编写add.jsp

```jsp
<%--
  Created by IntelliJ IDEA.
  User: JohnsonChi
  Date: 2023/9/26
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加学生信息</title>
</head>
<body>
<h1>添加学生信息</h1>
<form id="myForm" action="addStudent" method="post" onsubmit="return false">
    <label for="name">学生姓名：</label>
    <input type="text" id="name" name="name" required><br>

    <label for="dateOfBirth">学生出生年月：</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" required><br>

    <label for="email">学生邮箱：</label>
    <input type="email" id="email" name="email" required><br>

    <label for="phoneNumber">学生电话：</label>
    <input type="tel" id="phoneNumber" name="phoneNumber" required><br>

    <label for="address">学生住址：</label>
    <input type="text" id="address" name="address" required><br>

    <input type="submit" value="提交">
</form>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function (){
        $("#myForm").submit(function () {
            var data = {
                name: $("#name").val(),
                dateOfBirth: $("#dateOfBirth").val(),
                email: $("#email").val(),
                phoneNumber: $("#phoneNumber").val(),
                address: $("#address").val()
            }
            // 使用Ajax发送POST请求到后台Servlet
            $.ajax({
                type: 'POST',
                url: 'addStudent',
                data: data,
                success: function (response) {
                    // 处理成功响应
                    alert('添加成功！');
                    // 刷新页面或执行其他操作
                    self.location=document.referrer;
                },
                error: function () {
                    // 处理请求错误
                    alert('添加失败！');
                }
            });
        });
    })
</script>
</html>
```

#### 7.编写edit.jsp

```jsp
<%@ page import="cn.edu.qdu.domain.Student" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="cn.edu.qdu.utils.SqlSessionFactoryUtil" %>
<%@ page import="cn.edu.qdu.mapper.StudentsMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="jdk.nashorn.internal.runtime.regexp.RegExp" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: JohnsonChi
  Date: 2023/9/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
</head>
<body>
<h1>修改学生信息</h1>
<form id="myForm" action="editStudent" method="post" onsubmit="return false">
    <%
        String id = request.getParameter("id");
        try(SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession()) {
            StudentsMapper mapper = sqlSession.getMapper(StudentsMapper.class);
            Student student = mapper.getStudentById(Integer.parseInt(id));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (student.getDateOfBirth() == null) {
                student.setDateOfBirth(new Date());
            }
    %>
    <input id="id" name="id" hidden value="<%=student.getId()%>"><br>

    <label for="name">学生姓名：</label>
    <input type="text" id="name" name="name" value="<%=student.getName()%>" required><br>

    <label for="dateOfBirth">学生出生年月：</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%=sdf.format(student.getDateOfBirth())%>" required><br>

    <label for="email">学生邮箱：</label>
    <input type="email" id="email" name="email" value="<%=student.getEmail()%>" required><br>

    <label for="phoneNumber">学生电话：</label>
    <input type="tel" id="phoneNumber" name="phoneNumber" value="<%=student.getPhoneNumber()%>" required><br>

    <label for="address">学生住址：</label>
    <input type="text" id="address" name="address" value="<%=student.getAddress()%>" required><br>
<%         }        %>
    <input type="submit" value="提交">
</form>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $.urlParam = function (name) {
        var results = new RegExp('[?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results === null) {
            return null;
        } else {
            return decodeURIComponent(results[1]) || 0;
        }
    };
    $(function (){
        $("#myForm").submit(function () {
            var data = {
                id: $("#id").val(),
                name: $("#name").val(),
                dateOfBirth: $("#dateOfBirth").val(),
                email: $("#email").val(),
                phoneNumber: $("#phoneNumber").val(),
                address: $("#address").val()
            }
            // 使用Ajax发送POST请求到后台Servlet
            $.ajax({
                type: 'POST',
                url: 'editStudent',
                data: data,
                success: function (response) {
                    // 处理成功响应
                    alert('修改成功！');
                    // 刷新页面或执行其他操作
                    self.location=document.referrer;
                },
                error: function () {
                    // 处理请求错误
                    alert('修改失败！');
                }
            });
        });
    })
</script>
</html>

```

#### 8.编写AddStudentServlet.java

```java
package cn.edu.qdu.servlet;

import cn.edu.qdu.domain.Student;
import cn.edu.qdu.mapper.StudentsMapper;
import cn.edu.qdu.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author 冀忠祥
 * @date 2023/9/26 14:33
 */
@WebServlet(name = "addStudentServlet", urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {

    /**
     * 新增
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端提交的表单字段值
        String name = req.getParameter("name");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession()) {
            sqlSession.getConnection().setAutoCommit(true);
            StudentsMapper mapper = sqlSession.getMapper(StudentsMapper.class);
            int i = mapper.insertStudent(Student
                    .builder()
                    .name(name)
                    .dateOfBirth(sdf.parse(dateOfBirth))
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build());
            if (i > 0) {
                resp.getWriter().println("添加成功！");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加失败！");
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

}

```

#### 9.编写EditStudentServlet.java

```java
package cn.edu.qdu.servlet;

import cn.edu.qdu.domain.Student;
import cn.edu.qdu.mapper.StudentsMapper;
import cn.edu.qdu.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author 冀忠祥
 * @date 2023/9/26 14:34
 */
@WebServlet(name = "editStudentServlet", urlPatterns = "/editStudent")
public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端提交的表单字段值
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession()) {
            sqlSession.getConnection().setAutoCommit(true);
            StudentsMapper mapper = sqlSession.getMapper(StudentsMapper.class);
            int i = mapper.updateStudent(Student
                    .builder()
                    .id(Integer.parseInt(id))
                    .name(name)
                    .dateOfBirth(sdf.parse(dateOfBirth))
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build());
            if (i > 0) {
                resp.getWriter().println("修改成功！");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "修改失败！");
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### 10.编写RemoveStudentServlet.java

```
package cn.edu.qdu.servlet;

import cn.edu.qdu.mapper.StudentsMapper;
import cn.edu.qdu.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 冀忠祥
 * @date 2023/9/26 14:33
 */
@WebServlet(name = "removeStudentServlet", urlPatterns = "/removeStudent")
public class RemoveStudentServlet extends HttpServlet {

    /**
     * 删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        resp.setCharacterEncoding("UTF-8");
        // 设置内容类型和字符编码
        resp.setContentType("text/html;charset=UTF-8");
        int studentId = Integer.parseInt(req.getParameter("id"));
        try (SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession()) {
            sqlSession.getConnection().setAutoCommit(true);
            StudentsMapper mapper = sqlSession.getMapper(StudentsMapper.class);
            int i = mapper.deleteStudentById(studentId);
            if (i > 0) {
                resp.getWriter().println("删除成功！");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
```

#### 最终项目包结构

![image-20230926154012291](.\第七节_WEB入门md.assets\image-20230926154012291.png)



































