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
                var selectedRow = $("#dg_banner").datagrid("getSelected");
                if (selectedRow == null) {
                    $.messager.alert('提示消息', '请选择数据');
                    return;
                }
                $('#dg_banner').edatagrid('destroyRow');
                //$('#dg_banner').edatagrid('load');
                /*

                $.ajax({
                    url:'
                ${pageContext.request.contextPath}/banner/deleteBanner',
                        method:'POST',
                        dataType:'json',
                        data:'id='+selectedRow.id,
                        success:function(data){
                            data=JSON.parse(data)
                            if(data.isDelete){
                                console.log("删除成功");
                                $("#dg_banner").edatagrid("reload");
                            }else{
                                console.log("删除失败")
                            }
                        }
                    });*/
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "保存",
            handler: function () {
                //alert('帮助按钮')
                $('#dg_banner').edatagrid('saveRow');
            }
        }, '-', {
            iconCls: 'icon-redo',
            text: "导出",
            handler: function () {
                location.href = "${pageContext.request.contextPath}/banner/deriveExcel";
            }
        }];
        $("#dg_banner").edatagrid({
            method: 'get',
            url: '${pageContext.request.contextPath}/banner/selectAll',
            saveUrl: '${pageContext.request.contextPath}/banner/updateBanner',
            updateUrl: '${pageContext.request.contextPath}/banner/updateBanner',
            destroyUrl: '${pageContext.request.contextPath}/banner/deleteBanner',
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20, 25],
            columns: [[
                {field: 'title', title: '名字', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'imgPath', title: '路径', width: 100},
                {field: 'createDate', title: '时间', width: 100}
            ]],
            toolbar: toolbar,

            view: detailview,
            //rowIndex:行的索引
            //rowData ：行数据
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:100px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.title + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })

    function addBanner() {
        // call 'submit' method of form plugin to submit the form
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/banner/addBanner',
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $('#dd_banner').dialog('close');
                    $("#dg_banner").edatagrid("reload");
                    $("#name").val("");
                    $("#path").filebox("clear");
                } else {
                    console.log("添加失败");
                }
            }
        });
    }

    //修改的方法
    function toUpdateBanner() {
        //获取选中行的所有信息 以便下面发起请求获取数据id
        /*var selectedRow=$("#dg_banner").datagrid("getSelected");*/
        //表单提交 会获取表单的所有信息
        $('#uu').form('submit', {
            url: '${pageContext.request.contextPath}/banner/updateBanner', //selectedRow.empno 获取选中行的数据id
            success: function (data) {
                data = JSON.parse(data);
                if (data.isUpdate) {
                    $("#ud_banner").dialog('close');
                    $("#dg_banner").edatagrid("reload");
                } else {
                    console.log("修改失败");
                }
            }
        })
    }
</script>

<table id="dg_banner"></table>


<div id="dd_banner" class="easyui-dialog" title="添加轮播图" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addBanner();
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#dd_banner').dialog('close');
				}
			}]">
    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <input class="easyui-filebox" style="width:150px" name="file" id="path" data-options="buttonText:'选择文件'"/>
    </form>
</div>


<div id="ud_banner" class="easyui-dialog" title="修改轮播图" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        toUpdateBanner();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        $('#ud_banner').dialog('close');
                    }
                }]">
    <form id="uu" method="post" enctype="multipart/form-data">
        <input id="id" type="hidden" name="id" data-options="required:true"/>
        <div>
            <label for="title">标题:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <input class="easyui-filebox" style="width:150px" name="file" id="img" data-options="buttonText:'选择文件'"/>
        <div>

            <img src="" id="bannerImg" width="50px"/>
        </div>
    </form>
</div>
