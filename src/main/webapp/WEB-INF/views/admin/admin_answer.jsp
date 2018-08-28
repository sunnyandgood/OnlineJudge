<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/27
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户答案展示</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-table/bootstrap-table.min.css"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                用户答案展示
            </div>
            <div class="ibox-content">
                <table id="table"> </table>
                <div class="btn btn-primary" onclick="removeAll()">批量删除</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="btn btn-primary" onclick="addToExcel()">导出到Excel</div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/resources/layout/_script.jsp"/>
<script src="${ctx}/resources/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script>
    $('#table').bootstrapTable({
        url:'${ctx}/answer/listAll',
        columns: [{
            checkbox:true
        }, {
            title: '序号',
            formatter:function(value,row,index){
                //return index+1; //序号正序排序从1开始
                var pageSize=$('#table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
            }
        }, {
            field: 'user.nuserNumber',
            title: '用户名'
        }, {
            field: 'user.userName',
            title: '姓名'
        }, {
            field: 'question.questionChapter',
            title: '试题单元'
        }, {
            field: 'question.questionDifficult',
            title: '试题难度'
        }, {
            field: 'question.questionTitle',
            title: '题目'
        }, {
            field: 'answerResult',
            title: '用户答案'
        }, {
            field: 'answerTime',
            title: '答题时间'
        },{
            field: 'caozuo',
            title: '操作',
            formatter:function(v1,v2,v3){
                return ['<a class="remove" href="javascript:void(0)" title="Remove">',
                    '<i class="glyphicon glyphicon-remove"></i>',
                    '</a>'].join('');
            },
            events:'caocuoEvents'
        }],
        pagination:true,
        pageSize:5,
        pageList:[5,10,15,20,25,30,],
        search:true,
        showRefresh:true,
        showColumns:true,
        clickToSelect:true,
        sidePagination:'server',
        queryParamsType:''
    });
    window.caocuoEvents = {
        'click .remove': function (e, value, row) {
            if(confirm('是否删除')){
                $.post('${ctx}/answer/delete/' + row['answerId'],function(r){

                    if(r.code==200){
                        $('#table').bootstrapTable('refresh');
                    }
                    layer.msg(r.message);
                });
            }
        }
    };

    function removeAll() {
        if (confirm("全部删除？")){
            arr = $('#table').bootstrapTable('getSelections');
            str = "";
            for(i in arr){
                str += arr[i]['answerId'] + ",";
            }
            ids = str.substring(0,str.length-1);

            $.post('${ctx}/answer/removeAll',{'ids':ids},function (r) {
                if(r.code==200){
                    $('#table').bootstrapTable('refresh');
                }
                layer.msg(r.message);
            });
        }
    }


    function addToExcel() {

    }
</script>
</html>
