package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vapi")
public class TreeNodeController {

	@Autowired
	TreeNodeService treeNodeService;


	@PostMapping(value = "/getApiGroupTree")
	public List<TreeNode> getApiGroupTree() {

		List<TreeNode> list = treeNodeService.getApiGroupTree();


		return list;
	}


}
