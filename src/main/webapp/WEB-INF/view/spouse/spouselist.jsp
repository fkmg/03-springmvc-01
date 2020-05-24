<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>spouse list</title>
    <base href="<%=basePath%>">
    <script language="javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
    <script type="application/javascript">
        $(function($) {
            $('.update-spouse').click(function (event) {
                var trimg = $(event.target).parent().parent();
                var id = trimg.find('td').eq(0).text();
                location.href='spouse/toUpdateView?id='.concat(id);
            });
            
            
        });
    </script>
</head>
<body>
    <div style="float: right">
        <a href="spouse/add">新增</a>
    </div>
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
                        <a href="javascript:void(0);" class="update-spouse">修改</a>&nbsp;
                        <a href="javascript:void(0);" class="delete-spouse">删除</a>
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