<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>编辑读者信息《 ${readerInfo.readerId}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 80px"></div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">编辑读者信息《 ${readerInfo.readerId}》</h3>
        </div>
        <div class="panel-body">
            <form action="reader_edit_do.html?readerId=${readerInfo.readerId}" method="post" id="readeredit" >
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${readerInfo.name}" readonly = "true">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">性别</span>
                    <input type="radio" name="sex" value="男" <c:if test="${readerInfo.sex=='男' }">checked="checked"</c:if>/>男
                    <input type="radio" name="sex" value="女" <c:if test="${readerInfo.sex=='女' }">checked="checked"</c:if>/>女
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">生日</span>
                    <input type="date" class="form-control" name="birth" id="birth" value="<fmt:formatDate value="${readerInfo.birth}" pattern="yyyy-MM-dd"/>">
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  value="${readerInfo.address}" >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">电话</span>
                    <input type="text" class="form-control" pattern="[1][3,4,5,7,8][0-9]{9}" minlength="11" maxlength="11" name="phone" id="phone" value="${readerInfo.phone}" >
                </div>
                <div class="input-group" style="padding-top: 20px;padding-bottom: 20px;">
                    <span class="input-group-addon">身份</span>
                    <select name="roleId" style="width: 150px;" class="form-control">
                        <option value="${reader.roleId}" selected = "selected">${reader.roleName}</option>
                        <c:forEach items="${readerRole}" var="r">
                            <c:if test="${reader.roleId !=r.roleId}">
                                <option name="roleId" value="${r.roleId}">${r.roleName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    $("#readeredit").submit(function () {
                        if($("#name").val()==''||$("#sex").val()==''||$("#birth").val()==''||$("#address").val()==''||$("#phone").val()==''){
                            alert("请填入完整读者信息！");
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
