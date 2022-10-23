package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 树节点
 */
@Getter
@AllArgsConstructor
public enum TreeNodeType {

    TABLE_GROUP(Arrays.asList("TABLE_GROUP"), "表分组"),
    ROLE_GROUP(Arrays.asList("ROLE_GROUP"), "角色分组"),
    SYSTEM_MENU(Arrays.asList("HEADER_MENU", "SIDEBAR"), "系统菜单"),
    HEADER_MENU(Arrays.asList("HEADER_MENU"), "顶部菜单"),
    SIDEBAR(Arrays.asList("SIDEBAR"), "侧边栏");

    private List<String> type;

    private String label;

    public String getType() {
        return String.join(",", type);
    }
}
