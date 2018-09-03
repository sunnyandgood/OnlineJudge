<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/9/3
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>题库上传</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/resources/css/Huploadify/Huploadify.css">
</head>
<body>
    <h1>上传题库</h1>
    <form action="${ctx}/question/addFromExcel">
        <div id="questionUpload" name="question"></div>
        <input type="submit" value="提交">
    </form>
</body>
<jsp:include page="/resources/layout/_script.jsp"/>
<script src="${ctx}/resources/js/Huploadify/jquery.Huploadify.js"></script>
<script src="${ctx}/resources/js/my.js"></script>
<script>
    upload($('#questionUpload'),false,'${ctx}/upload/upload','${ctx}');
</script>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
    <%--<jsp:include page="/resources/layout/_css.jsp"/>--%>
    <%--<link rel="stylesheet" href="${ctx}/resources/css/Huploadify/Huploadify.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>上传相册</h1>--%>
<%--<form action="${ctx}/uptest1">--%>
    <%--<div id="imageUpload" name="img"></div>    &lt;%&ndash;   (上传控件)&ndash;%&gt;--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>

<%--<h1>上传头像</h1>--%>
<%--<form action="${ctx}/uptest2">--%>
    <%--<div id="imageUpload2" name="img2"></div>    &lt;%&ndash;   (上传控件)&ndash;%&gt;--%>
    <%--<input type="submit" value="提交">--%>
<%--</form>--%>
<%--</body>--%>
<%--<jsp:include page="/resources/layout/_script.jsp"/>--%>
<%--<script src="${ctx}/resources/js/Huploadify/jquery.Huploadify.js"></script>--%>
<%--<script src="${ctx}/resources/js/my.js"></script>--%>
<%--<script>--%>
    <%--upload($('#imageUpload'),true,'${ctx}/upload2','${ctx}');--%>
    <%--upload($('#imageUpload2'),false,'${ctx}/upload2','${ctx}');--%>
<%--</script>--%>
<%--</html>--%>