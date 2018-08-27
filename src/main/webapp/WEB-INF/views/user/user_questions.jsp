<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/8/26
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>试题展示</title>
    <jsp:include page="/resources/layout/_css.jsp"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-table/bootstrap-table.min.css"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                试题展示
            </div>
            <div class="ibox-content">
                <table id="table"> </table>
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
        url:'${ctx}/question/listAll',
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
            field: 'questionChapter',
            title: '试题单元'
        }, {
            field: 'questionDifficult',
            title: '试题难度',
            formatter:function(v1){
                if(v1==1){
                    return '很简单';
                }else if(v1==2){
                    return '简单';
                }else if(v1==3){
                    return '适中';
                }else if(v1==4){
                    return '难';
                }else if(v1==5){
                    return '很难';
                }else{
                    return v1;
                }
            }
        }, {
            field: 'questionTitle',
            title: '题目'
        },{
            field: 'questionTime',
            title: '出题时间'
        },{
            field: 'questionAuthor',
            title: '出题人',
        },{
            field: 'questionAnswer',
            title: '参考答案',
        },{
            field: 'questionPara1',
            title: '测试数据1',
        },{
            field: 'questionPara2',
            title: '测试数据2',
        },{
            field: 'questionPara3',
            title: '测试数据3',
        },{
            field: 'caozuo',
            title: '答题',
            formatter:function(v1,v2,v3){
                return ['<a class="edit" href="javascript:void(0)" title="Like">',
                    '<i class="fa fa-pencil"></i>',
                    '</a>',
                    ].join('');
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
        'click .edit': function (e, value, row) {
            layer.open({
                type: 2,
                area: ['800px', '500px'],
                content: '${ctx}/user/preUpdate/' + row['id'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            });
        },
    };
</script>
</html>
