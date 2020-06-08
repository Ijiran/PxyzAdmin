/**
 * menu.js 菜单管理js
 */

layui.use(['table', 'treetable', 'common'], function () {
    var $ = layui.jquery;
    var form = layui.form;
    var treetable = layui.treetable;
    var common = layui.common;

    layer.load(2);
    // 渲染表格
    var menuTree = treetable.render({
        elem: '#munu-table',
        url: '/pxyzadmin/menu/getMenuList',
        tree: {
            iconIndex: 1,
            arrowType: 'arrow1',
            isPidData: true,
            idName: 'id',
            pidName: 'pid',
            getIcon: function (d) {
                if (d.children) {
                    return '<i class="ew-tree-icon ew-tree-icon-folder"></i>';
                } else {
                    return '<i class="ew-tree-icon ew-tree-icon-file"></i>';
                }
            }
        },
        cols: [
            [
                {type: 'numbers'},
                {field: 'title', minWidth: 200, title: '菜单名称'},
                {field: 'icon', minWidth: 200, title: '菜单图标'},
                {field: 'remark', minWidth: 200, title: '备注'},
                {
                    field: 'status', minWidth: 200, title: '状态（是否生效）', templet: function(row){
                        var result = "";
                        if(row.status == '1'){
                            result = "<input type='checkbox' name='status' value='"+row.status+"' menu-id='"+row.id+"' lay-skin='switch' lay-text='是|否' lay-filter='switchStatus' checked/>";
                        }else{
                            result = "<input type='checkbox' name='status' value='"+row.status+"' menu-id='"+row.id+"' lay-skin='switch' lay-text='是|否' lay-filter='switchStatus'/>";
                        }
                        return result;
                    }
                },
                {templet: '#auth-state', width: 200, align: 'center', title: '操作'}
            ]
        ],
        done: function () {
            layer.closeAll('loading');
            menuTree.expandAll();
        }
    });

    $('#btn-expand').click(function () {
        menuTree.expandAll();
    });

    $('#btn-fold').click(function () {
        menuTree.foldAll();
    });

    //监听工具条
    treetable.on('tool(munu-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'del') {
            layer.confirm('确定要删除吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                //调用删除方法
                delMenu(data.id);
                menuTree.reload();
            },function () {
                //取消方法
            });
        } else if (layEvent === 'edit') {
            toMenuInfo(data.id,'2');
        } else if (layEvent === 'add'){
            toMenuInfo(data.id,'1');
        }
    });

    //监听状态变化
    form.on('switch(switchStatus)', function(obj){
        var data = menuTree.getData();
        var id = $(obj.elem).attr("menu-id");
        //判断是否存在子节点，如果存在则提示是否关闭，如果不存在则不提示。
        var children = getChildren(data,id);
        if(this.value == '1' && !obj.elem.checked && children){
            layer.confirm('这个菜单存在子菜单，如果关闭，将一并关闭所有子菜单，确认继续吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                var ids = common.mapToStr(children,"id") + id + ",";
                for(var i = 0; i <children.length; i++){
                    debugger
                    var children1 = getChildren(data,children[i].id);
                    if(children1){
                        ids += common.mapToStr(children1,"id");
                    }
                }
                //更新数据状态
                updateStatus(ids, "0");
            }, function(){
                menuTree.reload();
            });
        }else if(this.value == '1') {
            updateStatus(id, "0");
        }else{
            updateStatus(id, "1");
        }
    });

    /**
     * 跳转菜单编辑页面
     * @param type 1：新增；2：编辑
     */
    function toMenuInfo(id,type){
        layer.open({
            type: 2,
            title: '菜单编辑',
            shadeClose: true,
            shade: 0.8,
            area: ['60%', '73%'],
            content: '/pxyzadmin/menu/toMenuInfo?id='+id+'&type='+type,
            end: function () {
                menuTree.reload();
            }
        });
    }

    /**
     * 删除菜单
     * @param id
     */
    function delMenu(id){
        $.ajax({
            type: "get",
            url: '/pxyzadmin/menu/delMenu?id=' + id,
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
                menuTree.reload();
            }
        });
    }

    /**
     * 更新状态
     */
    function updateStatus(id, status){
        $.ajax({
            type: "get",
            url: '/pxyzadmin/menu/updateMenuStatus?id=' + id + '&status=' + status,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(data.result == 'success'){
                    layer.msg("更新成功");
                }else{
                    layer.msg("更新失败");
                }
                //重载数据
                menuTree.reload();
            }
        });
    }

    /**
     * 获取当前节点子元素
     */
    function getChildren(data,id){
        var children;
        for (var i = 0; i < data.length; i++){
            var item = data[i];
            if(item.id === id){
                return item.children?item.children:null;
            }else if(item.children && (children = getChildren(item.children,id))){
                return children;
            }
        }
        return null;
    }
});