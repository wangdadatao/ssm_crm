<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/5/5
  Time: 16:01
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

    <title>CRM-客户关系管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/js/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/js/datepicker/css/bootstrap-datepicker3.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>

        .text-ul {
            margin-top: 20px;
            list-style: none;
            padding: 0;
        }

        .text-ul li {
            margin: 6px 0;
        }

        .panel {
            border-radius: 0;
        }

        .panel-body {
            border-radius: 0;
        }

        .div-show-tasks {
            overflow-x: hidden;
        }
    </style>
</head>

<body>

<div id="wrapper">

    <%@ include file="../../public/public.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">
                        <i class="fa fa-tasks"></i> 待办任务
                        <button class="btn btn-primary btn-sm pull-right" id="showModal"><i class="fa fa-plus"></i> 新增
                        </button>
                        <div class="clearfix"></div>
                    </h4>


                    <c:if test="${not empty message}">
                        <div class="alert ${message.status}">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                                ${message.message}
                        </div>
                    </c:if>

                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                                  data-toggle="tab">未完成</a></li>
                        <li role="presentation"><a href="#done" aria-controls="profile" role="tab"
                                                   data-toggle="tab">已完成</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content div-show-tasks">
                        <div role="tabpanel" class="tab-pane active" id="home">

                            <ul class="text-ul">

                                <c:forEach items="${tasks}" var="task">
                                    <li class="text-li${task.id} undoneshow${task.status}">
                                        <div class="panel panel-default">

                                            <div class="panel-body">
                                                <c:choose>
                                                    <c:when test="${task.overTime()}">
                                                        <strong><span class="text-danger">${task.dayAndW} | (逾期)</span></strong>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <strong><span
                                                                class="text-muted">${task.dayAndW} </span></strong>
                                                    </c:otherwise>
                                                </c:choose>
                                                    ${task.task}
                                                <div class="pull-right">
                                                    <a href="javascript:;" class="a-done" rel="${task.id}">标记已完成</a>
                                                    <a href="javascript:;" class="exitLink" rel="${task.id}">编辑</a>
                                                    <a href="">删除</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>

                            </ul>

                        </div>
                        <div role="tabpanel" class="tab-pane" id="done">

                            <ul class="text-ul">

                                <c:forEach items="${tasks}" var="task">
                                    <li class="donetext-li${task.id} doneshow${task.status}">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <c:choose>
                                                    <c:when test="${task.overTime()}">
                                                        <strong><span class="text-danger">${task.dayAndW} | (逾期)</span></strong>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <strong><span
                                                                class="text-muted">${task.dayAndW} </span></strong>
                                                    </c:otherwise>
                                                </c:choose>
                                                    ${task.task}
                                                <div class="pull-right">
                                                    <a href="javascript:;" class="a-done" rel="${task.id}">撤销已完成</a>
                                                    <a href="">删除</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<div class="modal fade" id="newTaskModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增待办事项</h4>
            </div>
            <div class="modal-body">
                <form action="/task/new" method="post" id="newTaskForm">
                    <div class="form-group">
                        <label>待办内容</label>
                        <textarea name="task" class="form-control" rows="3"></textarea>
                        <label>待办时间</label>
                        <input type="text" name="worktime" class="form-control" id="datepicker">
                        <label>关联客户</label>
                        <select name="custid" class="form-control">
                            <option value=""></option>
                            <c:forEach items="${customers}" var="cust">
                                <option value="${cust.id}">${cust.company}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/static/js/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/static/js/sb-admin-2.js"></script>
<script src="/static/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/static/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<script>
    $(function () {

        $("#showModal").click(function () {
            $("#newTaskModal").modal("show");
        });

        $("#saveBtn").click(function () {
            $("#newTaskForm").submit();
        });

        $("#datepicker").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });

        $(".a-done").click(function () {
            $this = this;
            var id = $(this).attr("rel");
            $.post("/task/status", {"taskid": id}).done(function (result) {
                if (result.status == "success") {
                    var undoneLi = ".text-li" + id;
                    var doneli = ".donetext-li" + id;
                    if (result.ifdone == "true") {
                        $(undoneLi).hide();
                        $(doneli).fadeIn();
                    } else {
                        $(undoneLi).show();
                        $(doneli).hide(200);
                    }
                } else {
                    alert(result.errorMessage);
                }
            }).fail(function () {
                alert("参数错误,请稍后再试!");
            });
        });

        $(".undoneshowtrue").hide();
        $(".doneshowfalse").hide();

    });
</script>

</body>

</html>