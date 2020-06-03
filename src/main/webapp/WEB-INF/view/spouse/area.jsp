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
    <title>地域树的展示</title>
    <base href="<%=basePath%>">
    <script language="javascript" src="${ctx}/js/jquery-3.3.1.min.js"/>
    <script type="text/javascript" src="${ctx}/js/ztree-all.js"></script>
    <script type="text/javascript" src="${ctx}/js/ztree-core.js"></script>
    <link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <SCRIPT LANGUAGE="JavaScript">
        function zTreeOnClick(event, treeId, treeNode) {
            alert(treeNode.areaId + ", " + treeNode.name);
        };

        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            async: {
                enable: true,
                dataType: "json",
                url: "spouse/areatree",
                autoParam: ["id"]
            },
            callback: {
                onClick: zTreeOnClick
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: { "Y": "", "N": "" }
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        $(document).ready(function(){
            console.log('hello world');
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
        });
    </SCRIPT>
</head>
<body>
<div>
    <ul id="treeDemo" class="ztree"></ul>
</div>
</body>
</html>
