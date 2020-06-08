/**
 * role.js 角色管理js
 */

layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        common = layui.common,
        table = layui.table;

    table.render({
        elem: '#roleTable',
        url: '/pxyzadmin/role/getRoleList',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print'],
        cols: [
            [
                {type: "checkbox"},
                {field: 'role_name', title: '角色名称'},
                {field: 'remark', title: '备注'},
                {
                    field: 'create_time', title: '创建时间', sort: true, templet:function (row) {
                        return util.toDateString(row.create_time);
                    }
                },
                {
                    field: 'update_time', title: '更新时间', sort: true, templet:function (row) {
                        return util.toDateString(row.update_time);
                    }
                },
                {title: '操作', minWidth: 150, toolbar: '#roleTableBar', align: "center"}
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 15,
        page: true,
        skin: 'line'
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        reload();
        return false;
    });

    /**
     * toolbar监听事件
     */
    table.on('toolbar(roleTableFilter)', function (obj) {
        var data = table.checkStatus('roleTable').data;
        if (obj.event === 'add') {
            toRoleInfo('');
        } else if (obj.event === 'menu') {//菜单绑定
            if(data.length != 1){
                layer.alert("请只勾选一行！");
                return false;
            }else{
                menuBind(data[0].id);
            }
        }else if(obj.event === 'delete'){
            if(data.length == 0){
                layer.alert("请至少勾选一行！");
                return false;
            }else{
                delRole(common.mapToStr(data,"id"));
            }
        }
    });

    //监听操作列
    table.on('tool(roleTableFilter)', function (obj) {
        var data = obj.data;
        var id = data.id;
        if (obj.event === 'edit') {
            toRoleInfo(id);
        } else if (obj.event === 'delete') {
            delRole(id);
        }
    });

    /**
     * 打开角色编辑页面
     * @param id
     */
    function toRoleInfo(id){
        layer.open({
            title: '编辑角色',
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['30%', '43%'],
            content: '/pxyzadmin/role/toRoleInfo?id='+id,
            end: function () {
                reload();
            }
        });
    }

    /**
     * 删除角色
     * @param id
     */
    function delRole(id){
        layer.confirm('真的要删除吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                type: "get",
                url: '/pxyzadmin/role/delRole?id=' + id,
                cache: false,
                async: false,
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    if(data.result == 'success'){
                        layer.msg("删除成功");
                    }else{
                        layer.msg("删除失败");
                    }
                    //重载数据
                    reload();
                }
            });
        });
    }

    /**
     * 重载表格数据
     */
    function reload(){
        table.reload('roleTable', {
            page: {
                curr: 1
            },
            where: {
                roleName: $("input[name='roleName']").val()
            }
        });
    }

    /**
     * 打开菜单绑定页面
     * @param id
     */
    function menuBind(id){
        layer.open({
            title: '菜单绑定',
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['40%', '80%'],
            content: '/pxyzadmin/role/toMenuBind?id='+id,
            end: function () {
                reload();
            }
        });
    }

});