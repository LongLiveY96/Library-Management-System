<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${admin==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<html>
<head>
    <title>借还日志</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/paginathing.js" ></script>
    <script src="js/jszip.min.js"></script>
    <script src="js/fileSaver.js"></script>
    <script src="js/excel-gen.js"></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
            excel = new ExcelGen({
                "src_id": "excelTable",
                "show_header": true
            });
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
            借还日志
        </h3>
    </div>
    <div class="panel-body">
        <button class="btn btn-primary btn-xs" id="generate-excel">导出为Excel</button>
        <table id="excelTable" class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>图书号</th>
                <th>书名</th>
                <th>读者证号</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="alog">
                <tr>
                    <td><c:out value="${alog.serNum}"/></td>
                    <td><c:out value="${alog.bookId}"/></td>
                    <td><c:out value="${alog.bookName}"/></td>
                    <td><c:out value="${alog.readerId}"/></td>
                    <td><fmt:formatDate value="${alog.lendDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${alog.backDate}" pattern="yyyy-MM-dd"/></td>
                    <td><c:choose>
                        <c:when test = "${alog.status==0}">借阅中</c:when>
                        <c:when test = "${alog.status==1}">已归还</c:when>
                        <c:when test = "${alog.status==2}">已逾期</c:when>
                        <c:when test = "${alog.status==3}">待处理</c:when>
                    </c:choose>
                    </td>
                    <td>
                        <c:if test="${alog.status==1}">
                            <a href="deletelend.html?serNum=<c:out value='${alog.serNum}'/>">
                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                            </a>
                        </c:if>
                        <c:if test="${alog.status==3}">
                            <a href="doLend.html?serNum=<c:out value='${alog.serNum}'/>">
                                <button type="button" class="btn btn-primary btn-xs">处理</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    $("#generate-excel").click(function () {
        excel.generate();
    });
    $('.table tbody').paginathing({
        perPage: 5,
        insertAfter: '.table',
        pageNumbers: true
    });
</script>
</body>
</html>
