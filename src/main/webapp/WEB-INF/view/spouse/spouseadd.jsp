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
        //获取表格
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
                            if(data !=undefined && data != null){
                                if(data.status == 200){
                                    console.log("上传文件成功");
                                    //拼接表格
                                    //获取当前表格的总行数
                                    var tab = document.getElementById("imgtable");
                                    var length = tab.rows.length;
                                    //拼接表单
                                    var imgs = "<tr data-imgid='"+data.imgeId+"'><td align='center'>"+length+"</td><td align='center'><a href='"+data.imgurl+"' class='imgdetail' target='_blank'>"+data.userdesc+"</a></td><td width='200px'><img src='"+data.imgurl+"' width='200px' height='300px'></td><td align='center'><a href='javascript:void(0)' onclick='deleteimg(this)'>删除</a></td><tr/>";
                                    console.log(imgs);
                                    $('#imgdesc').append(imgs);
                                    //将上传图片的框隐藏
                                    $('.imgs-desc').css('visibility','hidden');
                                    //拼接内容id
                                    $('#imgs').val(data.imgeId);
                                    //清空内容
                                    $('#userimage').val('');

                                }
                            }
                        }
                    });
                }
            });

        });

        function deleteimg(obj) {
            //获取点击事件的行号
            var trimg = $(obj).parent().parent();
            console.log(trimg.find('td').eq(0).text());
            console.log(trimg.attr('data-imgid'));
            //1、移除所在的行
            var tab = document.getElementById("imgtable");
            tab.deleteRow(trimg.rowIndex+1);
            //2、清空内容
            $('#imgs').val('');
            //3、重现上传文件框
            $('.imgs-desc').css('visibility','visible');
        }
    </script>

</head>
<body>
<c:forEach items="${errors}" var="err">
    ${err.defaultMessage }
</c:forEach>
<form action="spouse/addspousedo" method="post" enctype="multipart/form-data">
    <div style="float: right">
        <input type="submit" value="提交">
    </div>
    <table>
        <tr>
            <td>
                姓名:
            </td>
            <td>
                <input type="text" name="name">
                <input type="hidden" id="imgs" name="imgs">
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
                <input type="datetime" name="birth">
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

        <tr class="imgs-desc">
            <td>
                图片:
            </td>
            <td>
               <input name="userimage" id="userimage" type="file">&nbsp;
                <input type="button" id="imgupload" value="上传">
            </td>
        </tr>

        <tr class="imgs-desc">
            <td>
                描述:
            </td>
            <td>
                <textarea name="userdesc" id="userdesc" rows="10" cols="50"></textarea>
            </td>
        </tr>
    </table>
</form>

<table id="imgtable" align="center" width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <thead>
    <tr>
        <th>编号</th>
        <th>图片描述</th>
        <th>显示</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody id="imgdesc">
       <!-- <tr data-imgid="123456">
            <td align="center">1</td>
            <td align="center"><a href="http://192.168.2.129/spouse/banjing.jpeg" class="imgdetail">坂井泉水</a></td>
            <td width="100px"><img src="http://192.168.2.129/spouse/banjing.jpeg" width="100px" height="100px"></td>
            <td align="center"><a href="javascript:void(0)" onclick="deleteimg(this)">删除</a></td>
        </tr> -->
    </tbody>

</table>

</body>