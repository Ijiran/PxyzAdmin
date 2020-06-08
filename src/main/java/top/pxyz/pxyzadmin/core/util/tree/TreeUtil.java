package top.pxyz.pxyzadmin.core.util.tree;

import top.pxyz.pxyzadmin.system.menu.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.core.util.tree
 * @date 2020-06-05 23:22
 */
public class TreeUtil {

    /**
     * 转换树数据
     * @param treeList
     * @param pid
     * @return
     */
    public static List<MenuVo> toTree(List<MenuVo> treeList, String pid) {
        List<MenuVo> retList = new ArrayList<MenuVo>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

}
