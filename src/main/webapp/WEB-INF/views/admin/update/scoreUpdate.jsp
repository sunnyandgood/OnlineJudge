<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/28
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息修改</title>
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

                        <input type="hidden" name="scoreId">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">题目</label>
                            <div class="col-sm-10">
                                <select name="questionId" class="form-control"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分数</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="scoreDegree">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">answer表id</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="answerId">
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
<script src="${ctx}/resources/js/datapicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/js/datapicker/bootstrap-datepicker.zh-CN.min.js"></script>
<script>
    arr = location.href.split("/");
    scoreId = arr[arr.length-1];

    //查询所有题目
    $.get('${ctx}/question/list',function (r) {
        questionList = r['questionList'];
        $('select').empty();
        for(i in questionList){
            str = '<option value="'+questionList[i]['questionId']+'">'+questionList[i]['questionTitle']+'</option>';
            $('select').append(str);
        }
        //查询选择的用户信息
        $.get('${ctx}/score/'+scoreId,function (r) {
            s = r.score;
            $('[name=scoreId]').val(scoreId);
            $('[name=scoreDegree]').val(s.scoreDegree);
            $('[name=answerId]').val(s.answerId);
            $('select').val(s.questionId);
        });
    });


    function save(){
        $.post('${ctx}/score/update',$('form').serialize(),function (r) {
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
