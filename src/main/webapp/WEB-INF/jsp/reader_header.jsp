<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${readerCard==null}">
    <c:redirect url="login.html"></c:redirect>
</c:if>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="reader_main.html"><p class="text-primary" style="font-family: 华文行楷; font-size: 200%; ">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <a href="reader_books.html">
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li >
                    <a href="myLend.html" >
                        我的借阅
                    </a>
                </li>
                <li >
                    <a href="myBadList.html" >
                        惩罚记录
                    </a>
                </li>
                <li >
                    <a href="reader_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="reader_info.html">${readerCard.roleName}:${readerCard.userName}, 已登录</a></li>
                <li><a href="logout.html">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="js/jquery-3.2.1.js"></script>
<script type='text/javascript'>
    $(document).ready(function (){// ready 事件
        $("li").each(function(index){//遍历对象
            $(this).click(function(){//点击触发事件
                $("li").removeClass("active");//删除当前元素的样式
                $("li").eq(index).addClass("active");//添加当前元素的样式
            });
        });
    });
</script>
