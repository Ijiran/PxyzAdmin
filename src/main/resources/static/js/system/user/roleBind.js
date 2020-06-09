/**
 * roleBind.js 角色用户绑定js
 */

layui.use(['table', 'common', 'transfer'], function () {
    var $ = layui.jquery;
    var transfer = layui.transfer;
    var common = layui.common;
    var util = layui.util;

    $.ajax({
        type: "get",
        url: '/pxyzadmin/role/getRoles',
        cache: false,
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if(data.length > 0){
                var transferData = [];
                for (var i = 0; i < data.length; i++){
                    var m = {};
                    m["title"] = data[i].role_name;
                    m["value"] = data[i].id;
                    transferData.push(m);
                }
                //定义标题及数据源
                transfer.render({
                    elem: '#roleBind',
                    id: 'role',
                    showSearch: true,
                    width: 270,
                    height: 410,
                    title: ['未绑定角色', '已绑定角色'],
                    data: transferData
                });
                //初始化绑定用户
                initRoleBind();
            }
        }
    });

    $('#btn-auth').click(function () {
        var data = transfer.getData('role');
        var ids = common.mapToStr(data,"value");
        $.ajax({
            type: "get",
            url: '/pxyzadmin/user/saveRoleBind?roleId=' + ids + '&userId=' + id,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                window.parent.layer.closeAll();
            }
        });
    });

    //初始化绑定用户
    function initRoleBind(){
        $.ajax({
            type: "get",
            url: '/pxyzadmin/user/getRoleBind?id=' + id,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(data.length > 0){
                    var values = common.mapToArray(data,"role_id");
                    transfer.reload('role', {
                        value: values
                    });
                }
            }
        });
    }

});