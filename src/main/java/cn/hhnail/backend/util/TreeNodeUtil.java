package cn.hhnail.backend.util;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.vo.response.TreeNodeRespVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeUtil {

	/**
	 * 将平级节点构建为树结构
	 *
	 * @param nodes
	 * @return
	 */
	public static List<TreeNode> buildTree(List<TreeNode> nodes) {
		List<TreeNode> tree = null;


		return tree;
	}

	/**
	 * 将DTO转化为VO
	 * <p>
	 * id -> key
	 * name -> title
	 *
	 * @param nodes
	 * @return
	 */
	public static List<TreeNodeRespVO> parseDTO2VO(List<TreeNode> nodes) {
		List<TreeNodeRespVO> voList = new ArrayList<>();
		nodes.forEach(node -> {
			TreeNodeRespVO vo = new TreeNodeRespVO();
			BeanUtils.copyProperties(node, vo);
			vo.setTitle(node.getName());
			vo.setKey(node.getId());
			voList.add(vo);
		});
		return voList;
	}

}
