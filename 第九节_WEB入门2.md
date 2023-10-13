# WEB入门2

[TOC]

## 一、请求转发与重定向

### 1.请求转发

请求转发是将请求从一个Servlet发送到另一个Servlet，不经过浏览器。

- `getRequestDispatcher(String path)`：获取用于请求转发的`RequestDispatcher`对象。

```java
RequestDispatcher dispatcher = request.getRequestDispatcher("/anotherServlet");
dispatcher.forward(request, response);
```

### 2.重定向

重定向是通过浏览器重新发送一个新的HTTP请求，将用户导向不同的URL地址。

- `sendRedirect(String location)`：将请求重定向到指定的URL。

```java
response.sendRedirect("https://www.example.com");
```

### 3.完整示例

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet("/redirectAndForwardDemo")
public class RedirectAndForwardDemoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 重定向到另一个网页
        response.sendRedirect("http://jwc.qdu.edu.cn");

        // 2. 请求转发到另一个Servlet
        // 获取RequestDispatcher对象
        RequestDispatcher dispatcher = request.getRequestDispatcher("/anotherServlet");
        // 执行请求转发
        dispatcher.forward(request, response);
    }
}
```

**在同一次请求中，只能选择使用重定向或请求转发其中之一，不能同时使用两者。**选择使用哪种方法取决于需求和目标。重定向适用于跳转到不同的站点或处理不同的HTTP请求，而请求转发适用于在同一应用程序内的不同Servlet之间共享请求和数据。

## 二、会话（Session）

### 1.会话的类型

在JavaWeb中，会话（Session）通常分为三种类型，根据其范围（Scope）的不同，分别是：

#### (1) 应用程序会话（Application Session）：
   - 范围：全局范围
   - 生命周期：与Web应用程序的生命周期相同，通常与应用程序的启动和关闭一致。
   - 存储位置：通常存储在ServletContext中，可以在整个应用程序中共享。
   - 用途：用于存储全局性的数据，对所有用户和会话可见，适用于整个应用程序的配置信息、共享数据等。
#### (2) 会话会话（Session Session）：
   - 范围：会话范围
   - 生命周期：从会话创建开始，到会话过期或销毁为止。会话通常在用户登录到应用程序时创建，在用户注销或会话超时后销毁。
   - 存储位置：存储在HttpSession对象中，每个用户会话都有一个独立的HttpSession对象。
   - 用途：用于存储与特定用户相关的会话数据，可以在用户在应用程序中导航时保持状态。
#### (3) 请求会话（Request Session）：
   - 范围：请求范围
   - 生命周期：与单个HTTP请求-响应周期相同，即一次请求后销毁。
   - 存储位置：通常存储在HttpServletRequest对象中。
   - 用途：用于在一次HTTP请求中传递数据，数据仅在当前请求周期内有效。

这些会话范围可帮助开发者根据数据的生命周期和可见性需求来选择适当的会话类型。应用程序会话用于全局数据，会话会话用于用户特定的数据，而请求会话用于临时数据的传递。

### 2.会话会话（Session Session）

会话（Session）是一个在Web应用程序中用于跟踪用户状态的机制，以便在多个HTTP请求之间共享数据。会话管理是JavaWeb开发中非常重要的一部分，其中会话会话是一种常见的会话管理方式。会话会话的底层原理涉及到如何创建、维护、注销会话以及在会话中存储和检索数据。

#### (1) 创建会话会话：

   当用户访问Web应用程序时，**服务器**会为每个用户创建一个**唯一的会话会话**。这通常通过Cookie或URL重写来实现。主要步骤包括：

   - 用户第一次访问应用程序时，服务器生成一个唯一的会话标识（Session ID）。
   - 服务器将Session ID发送给用户，通常以**Cookie**的形式存储在用户的浏览器中，或者作为URL参数附加到每个链接中。

#### (2) 维护会话会话：

   一旦会话会话被创建，它可以在多个HTTP请求之间保持状态。服务器会维护一个会话管理器来跟踪每个会话的状态，主要任务包括：

   - 根据Session ID识别用户会话。
   - 为每个会话维护一个会话对象，用于存储会话数据。
   - 确保会话的安全性，以防止会话劫持等攻击。

#### (3) 在会话中存储和检索数据：

   会话会话允许开发者在会话对象中存储和检索数据。这些数据可以在整个会话的生命周期内保持不变，用于跟踪用户状态和存储用户特定信息。

   - 存储数据：通过`session.setAttribute(key, value)`方法将数据存储在会话对象中。
   - 检索数据：通过`session.getAttribute(key)`方法检索存储在会话中的数据。

#### (4) 注销会话：

   当用户退出或会话超时时，会话会话需要被注销，以释放资源并清除相关数据。主要步骤包括：

   - 用户点击注销按钮或者会话超时触发。
   - 服务器通过Session ID找到相应的会话对象。
   - 会话对象被销毁，清除相关数据，释放资源。

在JavaWeb中，会话会话通常通过`HttpSession`对象来实现。每个`HttpSession`对象都有一个唯一的Session ID，用于标识与用户关联的会话。`HttpSession`对象提供了用于存储和检索数据的方法，例如`setAttribute`和`getAttribute`。

在底层，Servlet容器（如Tomcat）负责管理会话会话。它会处理Session ID的生成和传递，以及会话的创建、维护和销毁。Servlet容器通常使用Cookie来存储Session ID，也可以通过URL重写的方式传递Session ID。

### 3.设置/获取会话数据

#### (1) 应用程序会话（Application Session）：

在Servlet中设置和获取应用程序会话数据：

```java
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setAppSession")
public class SetAppSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String appName = "MyWebApp";
        context.setAttribute("appName", appName);
    }
}

@WebServlet("/getAppSession")
public class GetAppSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String appName = (String) context.getAttribute("appName");
        response.getWriter().println("Application Name: " + appName);
    }
}
```

JSP中设置和获取应用程序会话数据：

```jsp
<!-- a.jsp -->
<% 
   String appName = "MyWebApp";
   application.setAttribute("appName", appName);
%>
<!-- b.jsp -->
<% 
   String appName = (String)application.getAttribute("appName");
   out.println("Application Name: " + appName);
%>
```

#### (2) 会话会话（Session Session）：

在Servlet中设置和获取会话会话数据：

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/setSession")
public class SetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = "john_doe"; // Replace with actual username
        session.setAttribute("username", username);
    }
}

@WebServlet("/getSession")
public class GetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        response.getWriter().println("Welcome, " + username);
    }
}
```

JSP中设置和获取应用程序会话数据：

```jsp
<!-- a.jsp -->
<%
   String username = "john_doe";
   session.setAttribute("username", username);
%>
<!-- b.jsp -->
<% 
   String username = (String)session.getAttribute("username");
   out.println("Welcome, " + username);
%>
```

#### (3) 请求会话（Request Session）：

在Servlet中设置和获取会话会话数据：

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setRequest")
public class SetRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "This is a request-level message.";
        request.setAttribute("message", message);
    }
}

@WebServlet("/getRequest")
public class GetRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = (String) request.getAttribute("message");
        response.getWriter().println("Request Message: " + message);
    }
}
```

JSP中设置和获取应用程序会话数据：

```jsp
<!-- a.jsp -->
<%
   String message = "This is a request-level message.";
   request.setAttribute("message", message);
%>
<!-- b.jsp -->
<% 
   String message = (String)request.getAttribute("message");
   out.println("Request Message: " + message);
%>
```

### 4.会话的高级使用

#### (1) Cookie

1. **创建一个新的Cookie**：

```java
Cookie cookie = new Cookie("cookieName", "cookieValue");
```

这将创建一个名为"cookieName"，值为"cookieValue"的新Cookie对象。

2. **设置Cookie的有效期**：

```java
// 设置有效期为1小时（以秒为单位）
cookie.setMaxAge(3600); 
```

使用`setMaxAge`方法可以设置Cookie的有效期；如果设置为负数，表示Cookie在浏览器关闭时过期（默认）；如果设置为0，表示立即过期。

3. **设置Cookie的路径**：

```java
// 设置Cookie的路径，通常设置为应用程序的根路径
cookie.setPath("/myapp"); 
```

使用`setPath`方法可以指定Cookie的可见路径，通常设置为应用程序的根路径（默认），以便在整个应用程序中可用。

4. **设置Cookie的域**：

```java
// 设置Cookie的域，允许跨子域共享Cookie
cookie.setDomain(".example.com"); 
```

使用`setDomain`方法可以设置Cookie的域，以允许跨子域共享Cookie。

如果设置一个Cookie的域为 `.qdu.edu.cn`，则表示该Cookie可以在 `qdu.edu.cn` 及其所有子域名中共享，包括 `jwc.qdu.edu.cn`，`example.qdu.edu.cn`，`another.qdu.edu.cn` 等等。

如果将Cookie的域设置为 `.edu.cn`，那么Cookie将在 `edu.cn` 及其所有子域名中共享，如 `example.edu.cn` 也可以访问该Cookie，但不包括 `qdu.edu.cn` 的父级域名。

最后，如果将Cookie的域设置为 `.cn`，那么Cookie将在整个 `.cn` 顶级域名中共享，包括 `edu.cn` 、`com.cn`、`net.cn` 等等。

5. **添加Cookie到响应**：

```java
response.addCookie(cookie);
```

使用`addCookie`方法将Cookie添加到响应中，以便它可以发送给客户端。

6. **获取客户端发送的所有Cookie**：

```java
Cookie[] cookies = request.getCookies();
```

使用`getCookies`方法可以获取客户端发送的所有Cookie数组。

7. **遍历Cookie数组并检索Cookie的值**：

```java
if (cookies != null) {
    for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        // 处理Cookie的名称和值
    }
}
```

遍历Cookie数组并使用`getName`和`getValue`方法来检索每个Cookie的名称和值。

8. **删除Cookie**：

```java
// 设置有效期为0，表示删除Cookie
cookie.setMaxAge(0); 
// 发送带有0有效期的Cookie，使之过期并被删除
response.addCookie(cookie); 
```

要删除Cookie，可以将其有效期设置为0，并再次将其添加到响应中。

#### (2) 实现会话注销：

- 获取当前会话对象（`HttpSession`）。
- 调用`invalidate()`方法来销毁会话。
- 清除与会话相关的任何自定义Cookie信息（如果有的话）。

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话对象
        HttpSession session = request.getSession(false); // 使用false参数表示如果会话不存在不创建新会话

        if (session != null) {
            // 销毁会话
            session.invalidate();

            // 清除自定义Cookie信息（如果有的话）
            // 例如，清除名为"myCustomCookie"的Cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("myCustomCookie".equals(cookie.getName())) {
                        cookie.setMaxAge(0); // 将Cookie的有效期设为0，表示立即过期
                        response.addCookie(cookie); // 将更新后的Cookie发送给客户端
                    }
                }
            }

            // 重定向到登录页面或其他页面
            response.sendRedirect("login.jsp");
        }
    }
}

```

#### (3) 番外篇 - JWT

早期的Web应用通常使用基于Cookie的Session ID 来管理用户会话状态，这种方式有一些优点，例如：

1. **内置支持**：Web容器（如Tomcat、Java EE应用服务器等）通常提供了内置的会话管理功能，开发者可以轻松地使用它们来处理会话。
2. **扩展性**：使用Cookie来管理会话状态可以比较容易地在多台服务器之间进行扩展，因为所有服务器都可以共享Session ID，并且会话数据存储在共享的中央存储中。

然而，随着Web应用程序的发展和新的需求的出现，JWT（JSON Web Tokens）等无状态令牌认证机制逐渐流行起来，原因如下：

1. **分布式系统和微服务架构**：现代Web应用越来越倾向于使用分布式系统和微服务架构，其中会话状态的中心化管理变得复杂和不可行。JWT 可以轻松地在不同的服务之间传递信息，无需中央化的会话管理。
2. **移动应用和单页面应用（SPA）**：传统的基于Cookie的会话管理对于移动应用和单页面应用的支持相对较差。JWT 可以轻松地用于这些应用程序，并且在**前后端分离**的应用中特别有用。
3. **无状态和扩展性**：JWT 是一种无状态的令牌，令牌本身包含了用户的身份信息和其他相关数据。这意味着不需要在服务器端存储会话数据，这对于水平扩展和容器化应用程序非常有利。
4. **跨域资源共享（CORS）**：JWT 可以更容易地处理CORS，因为它可以在请求中包含令牌，从而允许客户端跨域访问受保护的资源。
5. **灵活性和自包含性**：JWT 是自包含的，包含了所有必要的信息，无需访问数据库或共享存储来验证令牌。这使得验证和授权变得高效。

## 三、基于会话的简单应用实践

需求：做一个简单的登录。

### 1、Servlet

首先，创建一个名为 "LoginServlet.java" 的Servlet，放在 "cn.edu.qdu.servlet" 包内，用于处理登录请求和验证用户身份。

```java
package cn.edu.qdu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 冀忠祥
 * @date 2023/10/10 11:17
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 检查用户名和密码是否匹配（固定值）
        if ("201440703100".equals(username) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", username);
            //登陆成功
            response.sendRedirect("welcome.jsp");
        } else {
            //登陆失败
            response.sendRedirect("error.jsp");
        }
    }
}
```

然后，创建一个名为 "LogoutServlet.java" 的Servlet，用于处理退出登录请求。

```java
package cn.edu.qdu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 冀忠祥
 * @date 2023/10/10 11:19
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        //回到登录页
        response.sendRedirect("login.jsp");
    }
}
```

### 2、JSP

首先，创建一个名为 "login.jsp" 的JSP页面，用于显示登录表单。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>青大教务登录</title>
</head>
<body>
<h1>青大教务登录</h1>
<form action="login" method="post">
    <label for="username">用户名:</label>
    <input type="text" name="username" id="username"><br>
    <label for="password">密码:</label>
    <input type="password" name="password" id="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
```

然后，创建一个名为 "welcome.jsp" 的JSP页面，用于显示登录后的内容。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆</title>
</head>
<body>
<%
    if (session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("error.jsp");
    }
%>
<h1>欢迎登陆，<%= session.getAttribute("loggedInUser") %></h1>
<a href="logout">退出登录</a>
</body>
</html>
```

最后，创建一个名为 "error.jsp" 的JSP页面，用于显示错误提示。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
<h1>错误</h1>
<h1>密码错误/未登录</h1>
<a href="login.jsp">返回登录</a>
</body>
</html>
```

