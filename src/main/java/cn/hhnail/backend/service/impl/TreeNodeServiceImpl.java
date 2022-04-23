package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.enums.TreeNodeType;
import cn.hhnail.backend.mapper.TreeNodeMapper;
import cn.hhnail.backend.service.TreeNodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.hhnail.backend.enums.TreeNodeType.HEADER_MENU;
import static cn.hhnail.backend.enums.TreeNodeType.SIDEBAR;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {

	@Autowired
	TreeNodeMapper treeNodeMapper;

	@Override
	public List<TreeNode> getApiGroupTree() {

		QueryWrapper<TreeNode> wrapper = new QueryWrapper<>();
		wrapper.eq("deleted", 0)
				.orderByAsc("level");
		List<TreeNode> treeNodes = treeNodeMapper.selectList(null);

		return treeNodes;
	}

	@Override
	public List<TreeNode> getHeaderMenu() {
		QueryWrapper<TreeNode> wrapper = new QueryWrapper<>();
		wrapper.eq("deleted", 0)
				.eq("type", HEADER_MENU.getType());
		List<TreeNode> treeNodes = treeNodeMapper.selectList(wrapper);
		return treeNodes;
	}

	@Override
	public List<TreeNode> getModule() {
		QueryWrapper<TreeNode> wrapper = new QueryWrapper<>();
		wrapper.eq("deleted", 0)
				.and(wp -> wp.eq("type", HEADER_MENU.getType())
						.or()
						.eq("type", SIDEBAR.getType()))
				.orderByAsc("level");
		List<TreeNode> treeNodes = treeNodeMapper.selectList(wrapper);
		return treeNodes;
	}
}
