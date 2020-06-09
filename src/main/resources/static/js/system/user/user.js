/**
 * user.js 用户管理js
 */

layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        common = layui.common,
        table = layui.table;

    table.render({
        elem: '#currentTableId',
        url: '/pxyzadmin/user/getUserList',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print'],
        cols: [
            [
                {type: "checkbox"},
                {field: 'username', title: '用户名'},
                {field: 'email', title: '邮箱'},
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
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
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
    table.on('toolbar(currentTableFilter)', function (obj) {
        var data = table.checkStatus('currentTableId').data;
        if (obj.event === 'add') {
            toUserInfo('');
        } else if (obj.event === 'role') {
            if(data.length != 1){
                layer.alert("请只勾选一行！");
                return false;
            }else{
                roleBind(data[0].id);
            }
        }else if(obj.event === 'password'){
            if(data.length == 0){
                layer.alert("请至少勾选一行！");
                return false;
            }else{
                resetPwd(data[0].id);
            }
        }else if(obj.event === 'delete'){
            if(data.length == 0){
                layer.alert("请至少勾选一行！");
                return false;
            }else{
                delUser(common.mapToStr(data,"id"));
            }
        }
    });

    //监听操作列
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        var id = data.id;
        if (obj.event === 'edit') {
            toUserInfo(id);
        } else if (obj.event === 'delete') {
            delUser(id);
        }
    });

    /**
     * 打开用户编辑页面
     * @param id
     */
    function toUserInfo(id){
        layer.open({
            title: '编辑用户',
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['30%', '43%'],
            content: '/pxyzadmin/user/toUserInfo?id='+id,
            end: function () {
                reload();
            }
        });
    }

    /**
     * 删除用户
     * @param id
     */
    function delUser(id){
        layer.confirm('真的要删除吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                type: "get",
                url: '/pxyzadmin/user/delUser?id=' + id,
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
     * 角色绑定
     */
    function roleBind(id){
        layer.open({
            title: '角色绑定',
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['700px', '600px'],
            content: '/pxyzadmin/user/toRoleBind?id='+id,
            end: function () {
                reload();
            }
        });
    }

    /**
     * 重置密码
     * @param id
     */
    function resetPwd(id){
        $.ajax({
            type: "get",
            url: '/pxyzadmin/user/resetPwd?id=' + id,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(data.result == 'success'){
                    layer.msg("重置成功，新密码为123456！");
                }else{
                    layer.msg("重置失败");
                }
            }
        });
    }

    /**
     * 重载表格数据
     */
    function reload(){
        table.reload('currentTableId', {
            page: {
                curr: 1
            },
            where: {
                username: $("input[name='username']").val(),
                email: $("input[name='email']").val()
            }
        });
    }

});