<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/28
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CRM-系统登录</title>

    <!-- Bootstrap Core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/js/metisMenu/metisMenu.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-coffee"></i> CRM系统登录</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty requestScope.errorMessage}">
                        <div class="alert alert-danger">
                                ${requestScope.errorMessage}
                        </div>
                    </c:if>
                    <form id="form-log" action="/user/log" method="post" role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入手机号" name="tel" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入密码" name="password" type="password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">记住用户名
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a id="a-log" href="javaScript:;" class="btn btn-lg btn-success btn-block">进入系统</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/static/js/jquery-1.12.2.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="/static/js/sb-admin-2.js"></script>
<script src="/static/js/jquery.validate.js"></script>

<script>
    $(function () {
        $("#form-log").validate({
            errorClass: "text-error",
            errorElement: "span",
            rules: {
                tel: {
                    required: true,
                },
                password: {
                    required: true
                }
            },
            messages: {
                tel: {
                    required: "请输入手机号登录！",
                },
                password: {
                    required: "请输入密码！"
                }
            }
        });
        $("#a-log").click(function () {
            $("#form-log").submit();
        })
    })
</script>


</body>
</html>
