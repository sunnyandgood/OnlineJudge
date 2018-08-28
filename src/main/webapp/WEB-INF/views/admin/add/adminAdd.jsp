<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/27
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/resources/css/datapicker/bootstrap-datepicker.css">
</head>
<body style="padding-top: 40px">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form  class="form-horizontal" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="adminName">
                                <div id="nameMsg"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="adminPassword">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <div class="btn btn-primary" onclick="insert()">保存内容</div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/resources/layout/_script.jsp"/>
<script src="${ctx}/resources/js/datapicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/js/datapicker/bootstrap-datepicker.zh-CN.min.js"></script>
<script>

    function insert(){

        alert($('[name=adminName]').val());
        alert($('[name=adminPassword]').val());

        if($('[name=adminName]').val()=='') {
            layer.msg("用户名不能为空");
        }else if($('[name=adminPassword]').val()==''){
            layer.msg("密码不能为空");
        }else {
            $.post('${ctx}/admin/insert',$('form').serialize(),function (r) {
                if(r.code==200){
                    parent.$('#table').bootstrapTable('refresh');
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
                layer.msg(r.message);
            });
        }
    }
</script>
</html>