package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.enums.TreeNodeType;
import cn.hhnail.backend.mapper.TreeNodeMapper;
import cn.hhnail.backend.service.TreeNodeService;
import cn.hhnail.backend.vo.request.UpdateModuleReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.hhnail.backend.enums.TreeNodeType.HEADER_MENU;
import static cn.hhnail.backend.enums.TreeNodeType.SIDEBAR;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {


	Logger logger = LoggerFactory.getLogger(TreeNodeServiceImpl.class);

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


	@Override
	@Transactional
	public void addModule(UpdateModuleReqVO reqVO) {
		TreeNode entity = new TreeNode();
		BeanUtils.copyProperties(reqVO, entity);
		// 新增节点肯定是末级节点（叶子节点）
		entity.setLeafy(1);

		logger.info("=v1", entity.toString());


		treeNodeMapper.insert(entity);
	}

	@Override
	public void deleteModule(Integer id) {
		TreeNode entity = new TreeNode();
		entity.setId(id);
		entity.setDeleted(1);
		treeNodeMapper.updateById(entity);
	}
}
