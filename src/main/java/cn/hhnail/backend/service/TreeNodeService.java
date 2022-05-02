package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.vo.request.UpdateModuleReqVO;

import java.util.List;

public interface TreeNodeService {


	List<TreeNode> getApiGroupTree();

	List<TreeNode> getHeaderMenu();

	List<TreeNode> getModule();

	List<TreeNode> getSidebar(String pid);

	void addModule(UpdateModuleReqVO reqVO);

	void deleteModule(Integer id);

	void updateModule(UpdateModuleReqVO reqVO);

	List<TreeNode> getTableGroup();
}
