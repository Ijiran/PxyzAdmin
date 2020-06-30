/**
 * oshi.js
 * */
layui.use(['jquery'], function () {
    var $ = layui.jquery;
    $.ajax({
        type: "get",
        url: '/pxyzadmin/oshi/getOshiParam',
        cache: false,
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if(data.result == 'success'){
                //循环赋值即可
                for (var key in data.data){
                    $("span[name='"+key+"']").text(data.data[key]);
                }
            }
        }
    });
});