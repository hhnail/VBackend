package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.TreeNode;

import java.util.List;

public interface TreeNodeService {


	List<TreeNode> getApiGroupTree();

	List<TreeNode> getHeaderMenu();

	List<TreeNode> getModule();
}
