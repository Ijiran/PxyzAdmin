/**
 * menuBind.js 角色菜单授权js
 */

layui.use(['table', 'treetable', 'common'], function () {
    var $ = layui.jquery;
    var treetable = layui.treetable;
    var common = layui.common;

    var menuTree = treetable.render({
        elem: '#munu-table',
        url: '/pxyzadmin/menu/getMenuList',
        tree: {
            iconIndex: 2,
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
                {type: 'checkbox'},
                {field: 'title', minWidth: 200, title: '菜单名称'},
                {field: 'remark', minWidth: 200, title: '备注'},
                {
                    field: 'status', title: '状态(是否生效)', templet: function(row){
                        var result = "";
                        if(row.status == '1'){
                            result = "<input type='checkbox' name='status' value='"+row.status+"' menu-id='"+row.id+"' lay-skin='switch' lay-text='是|否' lay-filter='switchStatus' checked disabled/>";
                        }else{
                            result = "<input type='checkbox' name='status' value='"+row.status+"' menu-id='"+row.id+"' lay-skin='switch' lay-text='是|否' lay-filter='switchStatus'  disabled/>";
                        }
                        return result;
                    }
                }
            ]
        ],
        done: function () {
            menuTree.expandAll();
            //加载已绑定菜单，自动选中
            initMenuBind();
        }
    });

    $('#btn-auth').click(function () {
        var checkData = common.mapToStr(menuTree.checkStatus(),"id");
        $.ajax({
            type: "get",
            url: '/pxyzadmin/role/saveMenuBind?roleId='+id+"&menuIds="+checkData,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                window.parent.layer.closeAll();
            }
        });
    });

    //初始化绑定菜单
    function initMenuBind(){
        $.ajax({
            type: "get",
            url: '/pxyzadmin/role/getMenuBind?id=' + id,
            cache: false,
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(data.length > 0){
                    var ids = common.mapToArray(data,"id");
                    menuTree.setChecked(ids);
                }
            }
        });
    }

});