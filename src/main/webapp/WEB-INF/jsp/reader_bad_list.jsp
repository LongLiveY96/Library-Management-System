<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${readerCard==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>惩罚日志</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('#header').load('reader_header.html');
        })
    </script>
</head>
<body background="img/u5.jpeg" style=" background-repeat:no-repeat ;
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
            惩罚记录
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>图书号</th>
                <th>书名</th>
                <th>读者证号</th>
                <th>姓名</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>逾期天数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="alog">
                <tr>
                    <td><c:out value="${alog.serNum}"/></td>
                    <td><c:out value="${alog.bookId}"/></td>
                    <td><c:out value="${alog.bookName}"/></td>
                    <td><c:out value="${alog.readerId}"/></td>
                    <td><c:out value="${alog.userName}"/></td>
                    <td><fmt:formatDate value="${alog.lendDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${alog.backDate}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${alog.overDay}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
