<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>图书信息添加</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-group {
            margin-bottom: 0;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/timg.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header" style="padding-bottom: 80px"></div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">添加图书</h3>
        </div>
        <div class="panel-body">
            <form action="book_add_do.html" method="post" id="addbook" enctype="multipart/form-data">
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">书名</span>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">作者</span>
                    <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者名">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">出版社</span>
                    <input type="text" class="form-control" name="publish" id="publish" placeholder="请输入出版社">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">ISBN</span>
                    <input type="text" pattern="[0-9]{13}" maxlength="13" class="form-control" name="isbn" id="isbn" placeholder="请输入13位的ISBN">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">简介</span>
                    <textarea rows="6" class="form-control" name="introduction" id="introduction"
                              placeholder="请输入简介"></textarea>
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">语言</span>
                    <input type="text" class="form-control" name="language" id="language">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">价格</span>
                    <input type="number"  class="form-control" name="price" id="price">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">出版日期</span>
                    <input type="date" class="form-control" name="pubstr" id="pubstr">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">分类</span>
                    <select name="classId" id="classId" style="width: 150px;" class="form-control">
                        <option value="" disabled selected hidden>请选择图书类别</option>
                        <c:forEach items="${classInfo}" var="c">
                            <option name="classId" value="${c.classId}">${c.className}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">封面</span>
                    <input type="file" class="form-control" name="bookImg" id="bookImg">
                </div>
                <div class="input-group" style="padding-top: 20px;padding-bottom: 20px;">
                    <span class="input-group-addon">数量</span>
                    <input type="text" class="form-control" name="number" id="number">
                </div>
                <input style="align-items: center" type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    $("#addbook").submit(function () {
                        if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubstr").val() == '' || $("#classId").val() == null || $("#number").val() == '') {
                            alert("请填入完整图书信息！");
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
