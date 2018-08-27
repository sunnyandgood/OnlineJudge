<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/26
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>答题页面</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/datapicker/bootstrap-datepicker.css">
</head>
<body style="padding-top: 40px">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form  class="form-horizontal" method="post">

                        <input type="hidden" name="id">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="userName">
                                <div id="nameMsg"></div>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" value="1"  name="gender">
                                    男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="2"  name="gender">
                                    女
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">email</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="email">
                                <div id="birthdayMsg"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">生日</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="birthday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">班级</label>
                            <div class="col-sm-10">
                                <select name="cId" class="form-control"></select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <div class="btn btn-primary" onclick="save()">保存内容</div>
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
<script src="${ctx}/resources/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    arr = location.href.split("/");
    id = arr[arr.length-1];

    //查询所有班级
    $.get('${ctx}/classroom/list',function (r) {
        classrooms = r['classrooms'];
        $('select').empty();
        for(i in classrooms){
            str = '<option value="'+classrooms[i]['id']+'">'+classrooms[i]['name']+'</option>';
            $('select').append(str);
        }
        //查询选择的用户信息
        $.get('${ctx}/user/'+id,function (r) {
            u = r.user;
            // console.log(u);
            $('[name=id]').val(id);
            $('[name=userName]').val(u.userName);
            $('[name=email]').val(u.email);
            $('[name=birthday]').val(u.birthday);
            // $('select').val(u.cId);
            $('select').val(u.cId);
            if(u.gender==1){
                //男：
                $(':radio').first().prop('checked',true);
                $(':radio').last().prop('checked',false);
            }else{
                //女:
                $(':radio').first().prop('checked',false);
                $(':radio').last().prop('checked',true);
            }
        });
    });


    function save(){
        $.post('${ctx}/user/update',$('form').serialize(),function (r) {
            if(r.code==200){
                parent.$('#table').bootstrapTable('refresh');
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }
            layer.msg(r.message);
        });
    }
</script>
</html>

