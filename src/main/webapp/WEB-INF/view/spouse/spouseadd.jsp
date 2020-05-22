<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>spouse add</title>
    <base href="<%=basePath%>">
    <script language="javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
    <script type="application/javascript">
        $(function($) {
           console.log('hello world');
            $('#imgupload').click(function () {
                //获取文件的名字
                var val = $('#userimage').val();
                console.log('文件的名称为:'+val);
                var userdesc = $('#userdesc').val();
                if(val==undefined || val==null ||val.length<1){
                    $('#userimage').focus();
                    alert('请选择上传的图片');
                }
                //判断描述是否为空
                else if(userdesc == undefined || userdesc==null ||userdesc.length ==0){
                    $('#userdesc').focus();
                    alert('请先填写描述信息');
                }
                else {
                    //异步上传图片
                    var formData = new FormData();
                    // 获取第文件的内容
                    formData.append("userimage", $('#userimage').get(0).files[0]);
                    formData.append("userdesc",userdesc);
                    $.ajax({
                        url: 'spouse/uplodimage',
                        processData: false,  // 这个必须为false，不转换的信息
                        contentType: false, // 这个必须为false，不指定发送信息的编码类型
                        data: formData,
                        type: "POST",
                        success: function (data) {
                            // 你自己的回调方法
                            if(data == 200){
                                alert('上传成功');
                            }
                        }
                    });
                }
            });

        });
    </script>

</head>
<body>
<form action="addspousedo" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                姓名:
            </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>

        <tr>
            <td>
                性别:
            </td>
            <td>
                <input type="radio" name="sex" value="1" checked>Male
                <br>
                <input type="radio" name="sex" value="0">Female
            </td>
        </tr>


        <tr>
            <td>
                生日:
            </td>
            <td>
                <input type="datetime-local" name="birth">
            </td>
        </tr>

        <tr>
            <td>
                评分:
            </td>
            <td>
                <input type="number" name="score">
            </td>
        </tr>

        <tr>
            <td>
                图片:
            </td>
            <td>
               <input name="userimage" id="userimage" type="file">&nbsp;
                <input type="button" id="imgupload" value="上传">
            </td>
        </tr>

        <tr>
            <td>
                描述:
            </td>
            <td>
                <textarea name="userdesc" id="userdesc" rows="10" cols="50"></textarea>
            </td>
        </tr>
    </table>
</form>

<table align="center" width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <thead>
    <tr>
        <th>编号</th>
        <th>图片描述</th>
        <th>显示</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="imgdesc">

    </tbody>

</table>

</body>