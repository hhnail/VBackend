package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.service.TreeNodeService;
import cn.hhnail.backend.util.TreeNodeUtil;
import cn.hhnail.backend.vo.request.UpdateModuleReqVO;
import cn.hhnail.backend.vo.response.AppResponse;
import cn.hhnail.backend.vo.response.HeaderMenuRespVO;
import cn.hhnail.backend.vo.response.ModuleRespVO;
import cn.hhnail.backend.vo.response.TreeNodeRespVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.lastIndexOf;

@RestController
@RequestMapping("/vapi")
public class TreeNodeController {

	Logger logger = LoggerFactory.getLogger(TreeNodeController.class);

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


	/**
	 * 获取顶部菜单——module level == 1
	 * @return
	 */
	@PostMapping(value = "/getHeaderMenu")
	public List<HeaderMenuRespVO> getHeaderMenu() {
		List<TreeNode> list = treeNodeService.getHeaderMenu();
		List<HeaderMenuRespVO> treeNodeRespVOs = TreeNodeUtil.parseHeaderMenuDTO2VO(list);
		return treeNodeRespVOs;
	}

	/**
	 * 获取系统侧边栏——module level >= 2
	 * @return
	 */
	@PostMapping(value = "/getSidebar")
	public List<TreeNodeRespVO> getSidebar(@RequestParam("pid") String pid) {
		try {
			List<TreeNode> list = treeNodeService.getSidebar(pid);
			List<TreeNodeRespVO> voList = TreeNodeUtil.parseDTO2VO(list);
			List<TreeNodeRespVO> treeNodeRespVOs = TreeNodeUtil.buildAscOrderdVOTree(voList);
			return treeNodeRespVOs;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	/**
	 * 模块管理——获取模块信息
	 * @return
	 */
	@PostMapping(value = "/getModule")
	public List<ModuleRespVO> getModule() {
		List<ModuleRespVO> voList = new ArrayList<>();

		// DTO 转化为 VO
		List<TreeNode> list = treeNodeService.getModule();
		list.forEach(node -> {
			ModuleRespVO vo = new ModuleRespVO();
			BeanUtils.copyProperties(node, vo);
			vo.setKey(node.getId());
			// 设置父模块路径
			String currentRoutingAddress = node.getRoutingAddress();
			int endIndex = currentRoutingAddress.lastIndexOf("/");
			vo.setParentRoutingAddress(currentRoutingAddress.substring(0,endIndex));
			voList.add(vo);
		});

		List<ModuleRespVO> treeNodeRespVOs = TreeNodeUtil.buildAscOrderdModuleRespVOTree(voList);

		return treeNodeRespVOs;
	}


	/**
	 * 模块管理——添加模块
	 * @param reqVO
	 * @return
	 */
	@PostMapping(value = "/addModule")
	public String addModule(@RequestBody @Validated UpdateModuleReqVO reqVO) {
		logger.info(reqVO.toString());
		treeNodeService.addModule(reqVO);

		return "";
	}

	/**
	 * 模块管理——更新模块信息
	 * @param reqVO
	 * @return
	 */
	@PostMapping(value = "/updateModule")
	public AppResponse<String> updateModule(@RequestBody UpdateModuleReqVO reqVO) {
		try {
			logger.info(reqVO.toString());
			treeNodeService.updateModule(reqVO);
			return AppResponse.ok("");
		} catch (Exception e) {
			e.printStackTrace();
			return AppResponse.fail("");
		}
	}



	/**
	 * 模块管理——删除模块
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/deleteModule")
	public String deleteModule(@RequestParam("id") Integer id) {
		logger.info("=v 删除的id", id);
		treeNodeService.deleteModule(id);
		return "";
	}


	/**
	 * 数据重构——查询系统表分组
	 */
	@PostMapping(value = "/getTableGroup")
	public AppResponse<List<TreeNodeRespVO>> getTableGroup() {
		List<TreeNode> list = treeNodeService.getTableGroup();
		List<TreeNodeRespVO> voList = TreeNodeUtil.parseDTO2VO(list);
		List<TreeNodeRespVO> resp = TreeNodeUtil.buildAscOrderdVOTree(voList);
		return AppResponse.ok(resp);
	}

	/**
	 * 角色管理——查询角色分组
	 */
	@PostMapping(value = "/getRoleGroup")
	public AppResponse<List<TreeNodeRespVO>> getRoleGroup() {
		List<TreeNode> list = treeNodeService.getRoleGroup();
		List<TreeNodeRespVO> voList = TreeNodeUtil.parseDTO2VO(list);
		List<TreeNodeRespVO> resp = TreeNodeUtil.buildAscOrderdVOTree(voList);
		return AppResponse.ok(resp);
	}

}
