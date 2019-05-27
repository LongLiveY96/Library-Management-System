<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>借阅角色管理</title>
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
            角色列表
        </h3>
    </div>
    <div class="panel-body">
        <form action="role_add_do.html" method="post" id="addRole">
            <input type="text" name="roleName" id="roleName" placeholder="请输入想添加的角色名">
            <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
            <script>
                $("#addRole").submit(function () {
                    if ($("#roleName").val() == '') {
                        alert("不能为空！");
                        return false;
                    }
                })
            </script>
        </form>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>权限号</th>
                <th>角色名</th>
                <th>可借阅图书数量</th>
                <th>可借阅最大天数</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${readerRoles}" var="r">
                <tr>
                    <td><c:out value="${r.roleId}"/></td>
                    <td><c:out value="${r.roleName}"/></td>
                    <td><c:out value="${r.borrowNum}"/></td>
                    <td><c:out value="${r.limitDay}"/></td>
                    <td>
                        <a href="editRole.html?roleId=<c:out value="${r.roleId}"/>">
                            <button type="button" class="btn btn-info btn-xs">编辑</button>
                        </a>
                    </td>
                    <td>
                        <a href="deleteRole.html?roleId=<c:out value='${r.roleId}'/>">
                            <c:if test="${r.count == 0}">
                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                            </c:if>
                            <c:if test="${r.count != 0}">
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
