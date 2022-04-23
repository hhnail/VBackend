package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.service.TreeNodeService;
import cn.hhnail.backend.util.TreeNodeUtil;
import cn.hhnail.backend.vo.response.HeaderMenuRespVO;
import cn.hhnail.backend.vo.response.ModuleRespVO;
import cn.hhnail.backend.vo.response.TreeNodeRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vapi")
public class TreeNodeController {

	@Autowired
	TreeNodeService treeNodeService;


	@PostMapping(value = "/getApiGroupTree")
	public List<TreeNodeRespVO> getApiGroupTree() {
		List<TreeNode> list = treeNodeService.getApiGroupTree();
		List<TreeNodeRespVO> treeNodeRespVOS = TreeNodeUtil.parseDTO2VO(list);
		List<TreeNodeRespVO> responseVOs = TreeNodeUtil.buildVOTree(treeNodeRespVOS);
		return responseVOs;
	}

	@PostMapping(value = "/getApiGroupTree2")
	public List<TreeNodeRespVO> getApiGroupTree2() {
		List<TreeNode> list = treeNodeService.getApiGroupTree();
		List<TreeNodeRespVO> treeNodeRespVOS = TreeNodeUtil.parseDTO2VO(list);
		List<TreeNodeRespVO> responseVOs = TreeNodeUtil.buildAscOrderdVOTree(treeNodeRespVOS);
		return responseVOs;
	}


	@PostMapping(value = "/getHeaderMenu")
	public List<HeaderMenuRespVO> getHeaderMenu() {
		List<TreeNode> list = treeNodeService.getHeaderMenu();
		List<HeaderMenuRespVO> treeNodeRespVOs = TreeNodeUtil.parseHeaderMenuDTO2VO(list);
		return treeNodeRespVOs;
	}



	@PostMapping(value = "/getModule")
	public List<ModuleRespVO> getModule() {
		List<ModuleRespVO> voList = new ArrayList<>();

		// DTO 转化为 VO
		List<TreeNode> list = treeNodeService.getModule();
		list.forEach(node->{
			ModuleRespVO vo = new ModuleRespVO();
			BeanUtils.copyProperties(node,vo);
			vo.setKey(node.getId());
			voList.add(vo);
		});

		List<ModuleRespVO> treeNodeRespVOs = TreeNodeUtil.buildAscOrderdModuleRespVOTree(voList);

		return treeNodeRespVOs;
	}




}
