<%@page isELIgnored="false" pageEncoding="UTF-8" language="java" contentType="text/html;UTF-8" %>
<script>
    $(function () {
        var tb;
        tb = [{
            iconCls: 'icon-add',
            text: "添加用户",
            handler: function () {
                $("#add_user").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: "修改",
            handler: function () {
                var row = $("#t_user").datagrid("getSelected");
                if (row == null) {
                    alert("请选择要修改项");
                    return;
                }
                console.log(row);
                $("#updateUserForm").form("load", row);
                $("#update_user").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-delete',
            text: "删除",
            handler: function () {
                var row = $("#t_user").datagrid("getSelected");
                if (row == null) {
                    alert("请选择要删除项");
                    return;
                }
                $('#t_user').edatagrid('destroyRow');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "保存",
            handler: function () {
                $('#t_user').edatagrid('saveRow');
            }
        }, '-', {
            iconCls: 'icon-redo',
            text: "导出",
            handler: function () {
                location.href = "${pageContext.request.contextPath}/user/deriveUser";
            }
        }];

        $('#t_user').edatagrid({
            method: "get",
            url: '${pageContext.request.contextPath}/user/selectAll',
            saveUrl: '${pageContext.request.contextPath}/user/update',
            updateUrl: '${pageContext.request.contextPath}/user/update',
            destroyUrl: '${pageContext.request.contextPath}/user/delete',
            fit: true,
            fitColumns: true,
            pagination: true,
            columns: [[
                {field: 'id', title: '编号', width: 100},
                {field: 'name', title: '姓名', width: 100},
                {field: 'dharma', title: '法号', width: 100},
                {
                    field: 'sex', title: '性别', width: 100, formatter: function (value, row, index) {
                        if (row.sex == 1) {
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {field: 'province', title: '省份', width: 100},
                {field: 'city', title: '城市', width: 100},
                {field: 'sign', title: '签名', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'phone', title: '手机号', width: 100},
                {field: 'password', title: '密码', width: 100},
                {field: 'headImg', title: '头像', width: 100},
                {field: 'createDate', title: '注册日期', width: 100},
            ]],
            toolbar: tb
        });
    })

    function addUser() {
        $('#addUserForm').form('submit', {
            url: '${pageContext.request.contextPath}/user/insert',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#add_user").dialog('close');
                    $("#t_user").edatagrid("reload");
                    $("#addUserForm").form("clear");
                } else {
                    console.log("添加失败");
                }
            }
        })
    }

    function updateUser() {
        $('#updateUserForm').form('submit', {
            url: '${pageContext.request.contextPath}/user/update',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isUpdate) {
                    $("#update_user").dialog('close');
                    $("#t_user").datagrid("reload");
                } else {
                    console.log("修改失败");
                }
            }
        })
    }
</script>
<table id="t_user"></table>
<div id="add_user" class="easyui-dialog" title="添加用户" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addUser();
				}
			},{
				text:'重置',
				handler:function(){
				    $('#addUserForm').form('reset');
				}
			}]">
    <form id="addUserForm" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">用户名:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="dharma">法名:</label>
            <input id="dharma" class="easyui-validatebox" type="text" name="dharma" data-options="required:true"/>
        </div>
        <div>
            性别<input type="radio" value="1" name="sex" id="man">男
            <input type="radio" value="0" name="sex" id="feman">女
        </div>
        <div>
            <label for="province">省份:</label>
            <input id="province" type="text" name="province" data-options="required:true"/>
        </div>
        <div>
            <label for="city">城市:</label>
            <input id="city" type="text" name="city" data-options="required:true"/>
        </div>
        <div>
            <label for="sign">签名:</label>
            <input id="sign" type="text" name="sign" data-options="required:true"/>
        </div>
        <div>
            <label for="password">密码:</label>
            <input id="password" type="password" name="password" data-options="required:true"/>
        </div>
        <div>
            <label for="phone">电话号码:</label>
            <input id="phone" type="text" name="phone" data-options="required:true"/>
        </div>
        <div>
            <label for="headImg">头像:</label>
            <input id="headImg" class="easyui-filebox" name="file" style="width:300px" data-options="buttonText:'选择头像'">
        </div>
    </form>
</div>
<div id="update_user" class="easyui-dialog" title="修改用户" style="width:400px;height:200px;"
     data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    updateUser();
				}
			},{
				text:'重置',
				handler:function(){
				    $('#updateUserForm').form('reset');
				}
			}]">
    <form id="updateUserForm" method="post" enctype="multipart/form-data">
        <input id="id" type="hidden" name="id" data-options="required:true"/>
        <div>
            <label for="name1">用户名:</label>
            <input id="name1" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="dharma1">法名:</label>
            <input id="dharma1" class="easyui-validatebox" type="text" name="dharma" data-options="required:true"/>
        </div>
        <div>
            性别<input type="radio" value="1" name="sex" id="man1">男
            <input type="radio" value="0" name="sex" id="feman1">女
        </div>
        <div>
            <label for="province1">省份:</label>
            <input id="province1" type="text" name="province" data-options="required:true"/>
        </div>
        <div>
            <label for="city1">城市:</label>
            <input id="city1" type="text" name="city" data-options="required:true"/>
        </div>
        <div>
            <label for="sign1">签名:</label>
            <input id="sign1" type="text" name="sign" data-options="required:true"/>
        </div>
        <div>
            <label for="password1">密码:</label>
            <input id="password1" type="password" name="password" data-options="required:true"/>
        </div>
        <div>
            <label for="phone1">电话号码:</label>
            <input id="phone1" type="text" name="phone" data-options="required:true"/>
        </div>
        <div>
            <label for="headImg1">头像:</label>
            <input id="headImg1" class="easyui-filebox" name="file" style="width:300px"
                   data-options="buttonText:'选择头像'">
        </div>
    </form>
</div>
