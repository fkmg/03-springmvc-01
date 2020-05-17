<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>spouse list</title>
    <base href="<%=basePath%>">
</head>
<body>
    <h2><a href="spouse/add">新增</a></h2>
    <table align="center" width="600" border="1" >
        <thead>
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>成绩</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${spouselist != null}">
            <c:forEach items="${spouselist}" var="spouse">
                <tr>
                    <td align="center">${spouse.id}</td>
                    <td align="center">${spouse.name}</td>
                    <c:if test="${spouse.sex == 0}">
                        <td align="center">男</td>
                    </c:if>
                    <c:if test="${spouse.sex == 1}">
                        <td align="center">女</td>
                    </c:if>
                    <td align="center">${spouse.birth}</td>
                    <td align="center">${spouse.score}</td>
                    <td align="center">
                        <a href="#" id="update">修改</a>&nbsp;
                        <a href="#" id="delete">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if >
        <c:if test="${spouselist == null}">
            <tr>
                <td colspan="6" align="center">
                    暂时没有数据
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</body>
</html>