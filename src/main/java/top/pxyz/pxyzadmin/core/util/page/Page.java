package top.pxyz.pxyzadmin.core.util.page;

import com.alibaba.fastjson.JSONArray;

/**
 * @date 2020-02-09 19:58
 */
public class Page {

    private String code = "0";

    private String msg = "";

    private int count = 0;

    private JSONArray data = null;

    public Page(){}

    public Page(String code, String msg, int count, JSONArray data){
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }
}
