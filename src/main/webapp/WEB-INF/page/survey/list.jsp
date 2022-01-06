<%--
  Created by IntelliJ IDEA.
  User: LZJ
  Date: 2022/1/5
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>问卷管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>查询条件</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline">
                                <select name="state" lay-filter="state">
                                    <option value="">请选择</option>
                                    <option value="创建">创建</option>
                                    <option value="执行中">执行中</option>
                                    <option value="结束">结束</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn "  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-sm data-edit-btn" lay-event="edit"> 修改 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
                <button class="layui-btn layui-btn-sm layui-btn-sm data-editQuestion-btn" lay-event="editQuestion"> 设计问卷 </button>
                <button class="layui-btn layui-btn-sm layui-btn-sm data-editQuestion-btn" lay-event="preview">预览问卷 </button>
                <button class="layui-btn layui-btn-sm layui-btn-sm data-editQuestion-btn" lay-event="publish">发布问卷 </button>
                <button class="layui-btn layui-btn-sm layui-btn-sm data-editQuestion-btn" lay-event="query_detail">问卷详情 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: 'query',
            toolbar: '#toolbarDemo',
            contentType:'application/json',
            method:"post",
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'title', width: 180, title: '问卷标题'},
                {field: 'url', width: 440, title: '问卷链接'},
                {field: 'startTime', width: 280, title: '开始时间'},
                {field: 'endTime', width: 280, title: '结束时间'},
                {field: 'state', width: 80, title: '状态'},
                {field: 'name', width: 100, title: '创建人',templet:'<div>{{d.admin.name}}</div>'},
                {field: 'createTime',title: '创建时间'}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            // layer.alert(result, {
            //     title: '最终的搜索信息'
            // });

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                },
                contentType:'application/json',
                 where: data.field
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加问卷',
                    type: 2,
                    shade: 0.2,
                    maxmin:false,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'create',
                    end:function () {
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                //定义数组，将选中的id放入数组
                var arr=[];
                for(index in data){
                    arr.push(data[index].id)
                }
                if(arr.length<1){
                    layer.msg("请选择至少一行数据修改")
                    return ;
                }
                //使用Ajax请求调用接口进行删除
                $.ajax({
                    url:'delete',
                    type:'POST',
                    dataType:'json',
                    data:'ids='+arr.join(","),
                    success:function (data) {
                        layer.msg(data.msg,
                            {
                                time:1000
                            },
                            function(){
                                table.reload('currentTableId');
                            }
                        );
                    },
                    error:function (data) {
                        alert("error")
                    }
                })
            }else if (obj.event === 'edit') {  // 监听编辑操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                //定义数组，将选中的id放入数组
                var arr=[];
                for(index in data){
                    arr.push(data[index].id)
                }

                //判断选中的id是否只有一个
                if(arr.length!=1){
                    layer.msg("请选择一行数据修改")
                    return ;
                }

                var index = layer.open({
                    title: '修改问卷',
                    type: 2,
                    shade: 0.2,
                    maxmin:false,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'detail?id='+arr[0],
                    end:function () {
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if (obj.event === 'editQuestion') {  // 监听修改问卷操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                //定义数组，将选中的id放入数组
                var arr=[];
                for(index in data){
                    arr.push(data[index].id)
                }

                //判断选中的id是否只有一个
                if(arr.length!=1){
                    layer.msg("请选择一行数据修改")
                    return ;
                }

                var index = layer.open({
                    title: '修改问题',
                    type: 2,
                    shade: 0.2,
                    maxmin:false,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'question?id='+arr[0],
                    end:function () {
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if (obj.event === 'preview') {  // 监听修改问卷操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                //定义数组，将选中的id放入数组
                var arr=[];
                for(index in data){
                    arr.push(data[index].id)
                }

                //判断选中的id是否只有一个
                if(arr.length!=1){
                    layer.msg("请选择一行数据修改")
                    return ;
                }
                window.open("preview/"+arr[0]);
            }else if(obj.event === 'publish'){   //发布功能
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr=[];
                for(index in data){
                    arr.push(data[index].id);
                }

                if(arr.length !=1){
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }

                $.ajax({
                    url:"publish",
                    type:"POST",
                    dataType:'json',
                    data:'id='+arr[0],
                    success:function(data){
                        layer.msg(data.msg,{time:2000},
                            function(){
                                table.reload('currentTableId');
                            });
                    }
                });

            }else if(obj.event === 'query_detail'){  //问卷详细
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr=[];
                for(index in data){
                    arr.push(data[index].id);
                }
                if(arr.length !=1){
                    layer.msg("请选择一行数据查看",{time:1000});
                    return;
                }
                if(data[0].url == ""){
                    layer.msg("请先发布再查看详情",{time:1000});
                    return;
                }
                window.open("query_detail/"+arr[0]);
            }
            return false;
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../page/table/edit.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>