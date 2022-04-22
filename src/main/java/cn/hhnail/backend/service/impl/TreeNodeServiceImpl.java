package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.mapper.TreeNodeMapper;
import cn.hhnail.backend.service.TreeNodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {

	@Autowired
	TreeNodeMapper treeNodeMapper;

	@Override
	public List<TreeNode> getApiGroupTree() {

		QueryWrapper<TreeNode> wrapper = new QueryWrapper<>();
		wrapper.eq("deleted",0)
				.orderByAsc("level");
		List<TreeNode> treeNodes = treeNodeMapper.selectList(null);

		return treeNodes;
	}
}
