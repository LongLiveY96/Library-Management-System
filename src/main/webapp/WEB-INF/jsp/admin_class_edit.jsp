<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>分类修改</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/lizhi.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 80px"></div>
<div class="col-xs-5 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                分类修改
            </h3>
        </div>
        <div class="panel-body">
            <form action="class_edit_do.html" method="post" id="edit" >
                <div class="input-group">
                    <span  class="input-group-addon">分类号</span>
                    <input type="text" readonly="readonly" class="form-control" name="classId" id="classId" value="${classInf.classId}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">分类名称</span>
                    <input type="text" class="form-control" name="className" id="className" value="${classInf.className}" >
                </div>
                <br/>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    $("#edit").submit(function () {
                        if($("#className").val()==''){
                            alert("分类名称不能为空！");
                            return false;
                        }
                    })
                </script>
            </form>
        </div>
    </div>
</div>
</body>
</html>
