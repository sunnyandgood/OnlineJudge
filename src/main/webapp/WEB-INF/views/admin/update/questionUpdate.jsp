<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/27
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试题修改</title>
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

                        <input type="hidden" name="questionId">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">试题单元</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="questionChapter">
                                <div id="nameMsg"></div>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">试题难度</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" value="1"  name="questionDifficult">
                                    很简单
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="2"  name="questionDifficult">
                                    简单
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="3"  name="questionDifficult">
                                    适中
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="4"  name="questionDifficult">
                                    难
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="5"  name="questionDifficult">
                                    很难
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">题目</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionTitle">
                                <div id="birthdayMsg"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">出题时间</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">出题人</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionAuthor">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">参考答案</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionAnswer">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">测试数据1</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionPara1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">测试数据2</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionPara2">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">测试数据3</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="questionPara3">
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
    questionId = arr[arr.length-1];

    //查询选择的用户信息
    $.get('${ctx}/question/'+ questionId ,function (r) {
        q = r.question;
        $('[name=questionId]').val(questionId);
        $('[name=questionChapter]').val(q.questionChapter);
        $('[name=questionTitle]').val(q.questionTitle);
        $('[name=questionTime]').val(q.questionTime);
        $('[name=questionAuthor]').val(q.questionAuthor);
        $('[name=questionAnswer]').val(q.questionAnswer);
        $('[name=questionPara1]').val(q.questionPara1);
        $('[name=questionPara2]').val(q.questionPara2);
        $('[name=questionPara3]').val(q.questionPara3);


        if(q.questionDifficult==1){
            //很简单：
            $("[name='questionDifficult']:eq(0)").prop('checked',true);
            $(':radio').next().prop('checked',false);
            $(':radio').next().prop('checked',false);
            $(':radio').next().prop('checked',false);
            $(':radio').last().prop('checked',false);

        }else if(q.questionDifficult==2){
            //简单:
            $("[name='questionDifficult']:eq(0)").prop('checked',false);
            $("[name='questionDifficult']:eq(1)").prop('checked',true);
            $("[name='questionDifficult']:eq(2)").prop('checked',false);
            $("[name='questionDifficult']:eq(3)").prop('checked',false);
            $("[name='questionDifficult']:eq(4)").prop('checked',false);
        }else if(q.questionDifficult==3){
            //适中:
            $("[name='questionDifficult']:eq(0)").prop('checked',false);
            $("[name='questionDifficult']:eq(1)").prop('checked',false);
            $("[name='questionDifficult']:eq(2)").prop('checked',true);
            $("[name='questionDifficult']:eq(3)").prop('checked',false);
            $("[name='questionDifficult']:eq(4)").prop('checked',false);
        }else if(q.questionDifficult==4){
            //难:
            $("[name='questionDifficult']:eq(0)").prop('checked',false);
            $("[name='questionDifficult']:eq(1)").prop('checked',false);
            $("[name='questionDifficult']:eq(2)").prop('checked',false);
            $("[name='questionDifficult']:eq(3)").prop('checked',true);
            $("[name='questionDifficult']:eq(4)").prop('checked',false);
        }else if(q.questionDifficult==5){
            //很难:
            $("[name='questionDifficult']:eq(0)").prop('checked',false);
            $("[name='questionDifficult']:eq(1)").prop('checked',false);
            $("[name='questionDifficult']:eq(2)").prop('checked',false);
            $("[name='questionDifficult']:eq(3)").prop('checked',false);
            $("[name='questionDifficult']:eq(4)").prop('checked',true);

        }
    });

    function save(){
        $.post('${ctx}/question/update',$('form').serialize(),function (r) {
            if(r.code==200){
                parent.$('#table').bootstrapTable('refresh');
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }
            layer.msg(r.message);
        });
    }

    //datepicker:
    $('[name=questionTime]').datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        orientation: "top left",
        autoclose: true,
        todayHighlight: true
    });
</script>
</html>

