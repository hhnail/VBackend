package cn.hhnail.backend.enums;

/**
 * 树节点
 */
public enum TreeNodeType {

	DATAxxx("test","数据xxx"),
	// 菜单
	HEADER_MENU("HEADER_MENU","顶部菜单"),
	SIDEBAR("SIDEBAR","侧边栏");

	private String type;

	private String label;

	TreeNodeType(String type,String label){
		this.type = type;
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
}
