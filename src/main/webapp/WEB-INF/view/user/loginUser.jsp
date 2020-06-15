<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="zh-CN">
<head>
    <title>spouse list</title>
    <base href="<%=basePath%>">
    <script language="javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
    <title>登录</title>

    <script type="text/javascript">
        function toRegister() {
            window.open('/spouse/toRegister');
        }
    </script>
</head>
<body>
    <form action="/springmvc/user/land" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td>
                    <input type="password" required name= "password" pattern="[a-zA-Z0-9.]+">
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="login">
                </td>

                <td>
                    <input type="button" value="regiser" onclick="toRegister()">
                </td>
            </tr>

        </table>

    </form>
</body>
</html>
