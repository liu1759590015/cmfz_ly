<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                //alert('编辑按钮')
                $('#dd_banner').dialog('open');
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: "修改",
            handler: function () {
                var selectedRow = $("#dg_banner").datagrid("getSelected");
                if (selectedRow == null) {
                    $.messager.alert('提示消息', '请选择要修改项');
                    return;
                }
                console.log(selectedRow);
                //带上原数据
                $("#uu").form('load', selectedRow);
                $("#bannerImg").prop("src", "${pageContext.request.contextPath}/" + selectedRow.imgPath);
                $("#ud_banner").dialog('open');
                //alert('帮助按钮')
            }
        }, '-', {
            iconCls: 'icon-delete',
            text: "删除",
            handler: function () {
                //alert('帮助按钮')
                $('#dg_banner').edatagrid('destroyRow');
                //$('#dg_banner').edatagrid('load');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "保存",
            handler: function () {
                //alert('帮助按钮')
                $('#dg_banner').edatagrid('saveRow');
            }
        }];
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
                {field: 'chapterSize', title: '章节大小', width: 60},
                {field: 'duration', title: '章节的时长', width: 80}
            ]]
        });
    })
</script>
<table id="tt_album"></table>
