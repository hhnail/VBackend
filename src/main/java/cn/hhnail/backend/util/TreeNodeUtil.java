package cn.hhnail.backend.util;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.vo.response.TreeNodeRespVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNodeUtil {

	/**
	 * 将平级节点构建为树结构
	 *
	 * @param nodes
	 * @return
	 */
	public static List<TreeNode> buildDTOTree(List<TreeNode> nodes) {
		List<TreeNode> nodesLevel1 = nodes.stream()
				.filter(node -> node.getLevel() == 1)
				.collect(Collectors.toList());
		List<TreeNode> nodesLevel2 = nodes.stream()
				.filter(node -> node.getLevel() == 2)
				.collect(Collectors.toList());
		List<TreeNode> nodesLevel3 = nodes.stream()
				.filter(node -> node.getLevel() == 3)
				.collect(Collectors.toList());

		nodesLevel3.forEach(node3->{
			nodesLevel2.forEach(node2->{
				if(node3.getPid() == node2.getId()){
					node2.getChildren().add(node3);
				}
			});
		});

		nodesLevel2.forEach(node2->{
			nodesLevel1.forEach(node1->{
				if(node2.getPid() == node1.getId()){
					node1.getChildren().add(node2);
				}
			});
		});

		return nodesLevel1;
	}


	/**
	 * 将平级节点构建为树结构
	 *
	 * @param nodes
	 * @return
	 */
	public static List<TreeNodeRespVO> buildVOTree(List<TreeNodeRespVO> nodes) {
		// 过滤出1级节点
		List<TreeNodeRespVO> nodesLevel1 = nodes.stream()
				.filter(node -> node.getLevel() == 1)
				.collect(Collectors.toList());
		// 过滤出2级节点
		List<TreeNodeRespVO> nodesLevel2 = nodes.stream()
				.filter(node -> node.getLevel() == 2)
				.collect(Collectors.toList());
		// 过滤出3级节点
		List<TreeNodeRespVO> nodesLevel3 = nodes.stream()
				.filter(node -> node.getLevel() == 3)
				.collect(Collectors.toList());

		// 将3级节点添加到2级节点
		nodesLevel3.forEach(node3->{
			nodesLevel2.forEach(node2->{
				if(node3.getPid() == node2.getKey()){
					node2.getChildren().add(node3);
				}
			});
		});

		// 将2级节点添加到1级节点
		nodesLevel2.forEach(node2->{
			nodesLevel1.forEach(node1->{
				if(node2.getPid() == node1.getKey()){
					node1.getChildren().add(node2);
				}
			});
		});

		return nodesLevel1;
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
