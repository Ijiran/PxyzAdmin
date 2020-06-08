/** 公共方法，书写一些基础方法。因为使用ES5的原因，有些ES6中很实用的方法没法使用，所以也简单的手写一个方法 */

layui.define(['util'], function (exports) {
    var $ = layui.jquery;
    var util = layui.util;

    /** 对外提供的方法 */
    var common = {
        //map 转 字符串 以,间隔
        mapToStr : function(data, field){
            var values = "";
            for(var k in data){
                values += data[k][field] + ",";
            }
            return values;
        },
        //map 转 集合
        mapToArray : function(data, field){
            var values = [];
            for(var k in data){
                values.push(data[k][field]);
            }
            return values;
        }
    };

    exports('common', common);
});
