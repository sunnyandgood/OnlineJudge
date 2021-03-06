<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/9/7
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员导入</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/resources/css/Huploadify/Huploadify.css">
</head>
<body>
<h1>管理员导入</h1>
<form>
    <div id="adminUpload" name="adminPath"></div>
    <div class="btn btn-primary" onclick="add()">提交</div>
</form>
</body>
<jsp:include page="/resources/layout/_script.jsp"/>
<script src="${ctx}/resources/js/Huploadify/jquery.Huploadify.js"></script>
<script src="${ctx}/resources/js/my.js"></script>
<script>
    upload($('#adminUpload'),false,'${ctx}/upload/upload','${ctx}');

    function add() {
        $.post('${ctx}/admin/addFromExcel',$('form').serialize(),function (r) {
            if(r.code==200){
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }
            layer.msg(r.message);
        });
    }
</script>
</html>
