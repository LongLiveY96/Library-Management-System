<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>图书分类管理</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="/img/lizhi.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<div style="position: relative;padding-top: 100px">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>

<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            分类列表
        </h3>
    </div>
    <div class="panel-body">
        <form action="class_add_do.html" method="post" id="addClass">
            <input type="text" name="className" id="className" placeholder="请输入想添加的分类名">
            <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
            <script>
                $("#addClass").submit(function () {
                    if ($("#className").val() == '') {
                        alert("不能为空！");
                        return false;
                    }
                })
            </script>
        </form>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>分类号</th>
                <th>分类名</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${classInfo}" var="c">
                <tr>
                    <td><c:out value="${c.classId}"/></td>
                    <td><c:out value="${c.className}"/></td>
                    <td>
                        <a href="editClass.html?classId=<c:out value="${c.classId}"/>">
                            <button type="button" class="btn btn-info btn-xs">编辑</button>
                        </a>
                    </td>
                    <td>
                        <a href="deleteClass.html?classId=<c:out value='${c.classId}'/>">
                            <c:if test="${c.count == 0}">
                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                            </c:if>
                            <c:if test="${c.count != 0}">
                                <button type="button" class="btn btn-default btn-xs" disabled="disabled">删除</button>
                            </c:if>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
