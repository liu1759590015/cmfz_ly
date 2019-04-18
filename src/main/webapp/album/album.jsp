<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "查看详情",
            handler: function () {
                //alert('编辑按钮')
                var selectedRow = $('#tt_album').datagrid("getSelected");
                if (selectedRow == null) {
                    $.messager.alert('提示消息', '请选择要查看项');
                    return;
                }
                console.log(selectedRow);
                if (selectedRow.size == null) {
                    $("#albumForm").form('load', selectedRow);
                    $("#albumImg").prop("src", "${pageContext.request.contextPath}" + selectedRow.imgPath);
                    $("#selectAlbum").dialog('open');
                } else {
                    $("#chapterForm").form('load', selectedRow);
                    $("#selectChapter").dialog('open');
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加专辑",
            handler: function () {
                $("#addAlbum").dialog('open');
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加章节",
            handler: function () {
                $("#addChapter").dialog('open');
                $.ajax({
                    type: "get",
                    url: "",
                    success: function () {

                    }
                })
            }
        }, '-', {
            iconCls: 'icon-undo',
            text: "下载",
            handler: function () {

            }
        }];
        //展示数据
        $('#tt_album').treegrid({
            //后台Controller查询所有专辑以及对应的章节集合
            method: 'get',
            url: '${pageContext.request.contextPath}/album/queryAll',
            idField: 'id',//用来标识标识树节点   主干树与分支树节点  ID不能有相同  并且使用相同的字段  否则ID冲突
            treeField: 'title',//用来定义树节点   树形表格上要展示的信息   注意使用相同的字段 用来展示对应节点名称
            toolbar: toolbar,
            fit: true,
            fitColumns: true,
            columns: [[
                {field: 'title', title: '名字', width: 180},
                {field: 'size', title: '章节大小', width: 60},
                {field: 'duration', title: '章节的时长', width: 80}
            ]]
        });
    })

    //添加专辑
    function toAddAlbum() {
        // call 'submit' method of form plugin to submit the form
        $('#addAlbumForm').form('submit', {
            url: '${pageContext.request.contextPath}/album/addAlbum',
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#addAlbum").dialog('close');
                    $("#tt_album").datagrid("reload");
                    $("#title1").val("");
                    $("#author1").val("");
                    $("#boardCast1").val("");
                    $("#brief1").val("");
                    $("#path").filebox("clear");
                } else {
                    console.log("添加失败");
                }
            }
        });
    }
</script>
<table id="tt_album"></table>

<%--查看专辑详情--%>
<div id="selectAlbum" class="easyui-dialog" title="查看详情" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'关闭',
                    handler:function(){
                        $('#select').dialog('close');
                    }
                }]">
    <form id="albumForm" method="post">
        <div>
            <img src="" id="albumImg" width="100px"/>
        </div>
        <div>
            <label for="title">专题:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="count">集数:</label>
            <input id="count" class="easyui-validatebox" type="text" name="amount" data-options="required:true"/>
        </div>
        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <label for="boardCast">播音人:</label>
            <input id="boardCast" class="easyui-validatebox" type="text" name="boardCast" data-options="required:true"/>
        </div>
        <div>
            <label for="publishDate">出版时间:</label>
            <input id="publishDate" class="easyui-validatebox" type="text" name="publishDate"
                   data-options="required:true"/>
        </div>
        <div>
            <label for="brief">简介:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
    </form>
</div>
<%--查看章节详情--%>
<div id="selectChapter" class="easyui-dialog" title="查看详情" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'关闭',
                    handler:function(){
                        $('#select').dialog('close');
                    }
                }]">
    <form id="chapterForm" method="post">
        <div>
            <label for="title2">专题:</label>
            <input id="title2" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="size">大小:</label>
            <input id="size" class="easyui-validatebox" type="text" name="size" data-options="required:true"/>
        </div>
        <div>
            <label for="duration">长度:</label>
            <input id="duration" class="easyui-validatebox" type="text" name="duration" data-options="required:true"/>
        </div>
        <div>
            <label for="publishDate2">出版时间:</label>
            <input id="publishDate2" class="easyui-validatebox" type="text" name="publishDate"
                   data-options="required:true"/>
        </div>
    </form>
</div>

<%--添加专辑--%>
<div id="addAlbum" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        toAddAlbum();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        $('#select').dialog('close');
                    }
                }]">
    <form id="addAlbumForm" method="post" enctype="multipart/form-data">
        <div>
            <label for="title1">专题:</label>
            <input id="title1" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="author1">作者:</label>
            <input id="author1" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <label for="boardCast1">播音人:</label>
            <input id="boardCast1" class="easyui-validatebox" type="text" name="boardCast"
                   data-options="required:true"/>
        </div>
        <div>
            <label for="brief1">简介:</label>
            <input id="brief1" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <input class="easyui-filebox" style="width:150px" name="file" data-options="buttonText:'选择文件'" id="path"/>
    </form>
</div>
<%--添加章节--%>
<div id="addChapter" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        toAddAlbum();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        $('#select').dialog('close');
                    }
                }]">
    <form id="addChapterForm" method="post" enctype="multipart/form-data">
        <div>
            <label for="title3">专题:</label>
            <input id="title3" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <select>
                <option></option>
            </select>
        </div>
        <input class="easyui-filebox" style="width:150px" name="file" data-options="buttonText:'选择音频文件'" id="path"/>
    </form>
</div>

