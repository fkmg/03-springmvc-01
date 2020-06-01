<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>spouse tree</title>
    <base href="<%=basePath%>">
    <script language="javascript" src="${ctx}/js/jquery-3.3.1.min.js"/>
    <script type="text/javascript" src="${ctx}/js/ztree-all.js"></script>
    <script type="text/javascript" src="${ctx}/js/ztree-core.js"></script>
    <link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <SCRIPT LANGUAGE="JavaScript">
        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {};
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes = [
            {name:"test1", open:true, children:[
                    {name:"test1_1"}, {name:"test1_2"}]},
            {name:"test2", open:true, children:[
                    {name:"test2_1"}, {name:"test2_2"}]}
        ];
        $(document).ready(function(){
            console.log('hello world');
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </SCRIPT>
</head>
<body>
<div>
    <ul id="treeDemo" class="ztree"></ul>
</div>
</body>
</html>
