package top.pxyz.pxyzadmin.system.menu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.system.vo
 * @date 2020-06-05 23:20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {

    private String id;

    private String pid;

    private String title;

    private String icon;

    private String href;

    private String target;

    private List<MenuVo> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<MenuVo> getChild() {
        return child;
    }

    public void setChild(List<MenuVo> child) {
        this.child = child;
    }
}
