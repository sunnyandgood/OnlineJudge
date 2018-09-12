<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/9/7
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"  scope="session"/>
<html>
<head>
    <title>测试</title>
    <link rel="shortcut icon" href="${ctx}/resources/img/favicon.ico">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/font-awesome.css">
    <link rel="stylesheet" href="${ctx}/resources/css/codemirror/codemirror.css">
    <link rel="stylesheet" href="${ctx}/resources/css/codemirror/ambiance.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
</head>
<body class="gray-bg"><%-- --%>
<div class="wrapper wrapper-content  animated fadeInRight"> <%----%>
    <div class="row">
        <div><%-- class="col-sm-6"--%>
            <div class="ibox ">
                <div  class="ibox-content">
                    <form  class="form-horizontal" method="post">
                        <%--<p class="m-b-lg" id="questionTitle"></p>--%>
                        题目：<input type="text" class="form-control"  name="questionTitle" readonly="readonly">
                        <br>
                        在此编辑答案：
                        <textarea id="code1"></textarea>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div align="center" class="btn btn-primary" onclick="up()">提交</div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/jquery.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/peity/jquery.peity.min.js"></script>
<script src="${ctx}/resources/js/codemirror/codemirror.js"></script>
<script src="${ctx}/resources/js/codemirror/mode/javascript/javascript.js"></script>
<script src="${ctx}/resources/js/content.js"></script>
<script>
    arr = location.href.split("/");
    questionId = arr[arr.length-1];

    //查询选择的用户信息
    $.get('${ctx}/question/'+ questionId ,function (r) {
        q = r.question;
        $('[name=questionTitle]').val(q.questionTitle);
    });

    $(document).ready(function () {
        var editor_one = CodeMirror.fromTextArea(document.getElementById("code1"), {
            lineNumbers: true,
            matchBrackets: true,
            styleActiveLine: true,
            theme: "ambiance"
        })
    });
    function up() {
        $.post('${ctx}/answer/insertOrUpdate',$('form').serialize(),function (r) {
            if(r.code==200){
                parent.$('#table').bootstrapTable('refresh');
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }
            layer.msg(r.message);
        });
    }
</script>
</body>
</html>
