package cn.hhnail.backend.util;

import cn.hhnail.backend.bean.TreeNode;
import cn.hhnail.backend.vo.response.HeaderMenuRespVO;
import cn.hhnail.backend.vo.response.ModuleRespVO;
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

		// 过滤出1级节点
		List<TreeNode> nodesLevel1 = nodes.stream()
				.filter(node -> node.getLevel() == 1)
				.collect(Collectors.toList());

		// 过滤出2级节点
		List<TreeNode> nodesLevel2 = nodes.stream()
				.filter(node -> node.getLevel() == 2)
				.collect(Collectors.toList());

		// 过滤出3级节点
		List<TreeNode> nodesLevel3 = nodes.stream()
				.filter(node -> node.getLevel() == 3)
				.collect(Collectors.toList());

		nodesLevel3.forEach(node3 -> {
			nodesLevel2.forEach(node2 -> {
				if (node3.getPid() == node2.getId()) {
					node2.getChildren().add(node3);
				}
			});
		});

		nodesLevel2.forEach(node2 -> {
			nodesLevel1.forEach(node1 -> {
				if (node2.getPid() == node1.getId()) {
					node1.getChildren().add(node2);
				}
			});
		});

		return nodesLevel1;
	}


	/**
	 * 将平级节点构建为树结构
	 * 暂时只支持3层
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
		nodesLevel3.forEach(node3 -> {
			nodesLevel2.forEach(node2 -> {
				if (node3.getPid() == node2.getKey()) {
					node2.getChildren().add(node3);
				}
			});
		});

		// 将2级节点添加到1级节点
		nodesLevel2.forEach(node2 -> {
			nodesLevel1.forEach(node1 -> {
				if (node2.getPid() == node1.getKey()) {
					node1.getChildren().add(node2);
				}
			});
		});

		return nodesLevel1;
	}


	/**
	 * 将升序的平面节点构建为树
	 * 理论上支持无数层级
	 *
	 * @param nodes
	 * @return
	 */
	public static List<TreeNodeRespVO> buildAscOrderdVOTree(List<TreeNodeRespVO> nodes) {
		// 从最深的层级开始分组
		Integer currentLevel = nodes.get(nodes.size() - 1)
				.getLevel();

		// 参数校验、异常处理
		if (nodes == null || nodes.size() < 1) {
			throw new RuntimeException("节点集合为空");
		}
		if (currentLevel == null || currentLevel < 1) {
			throw new RuntimeException("节点【所处层级】字段维护有误");
		}


		// 存储每个层级的节点
		List<List<TreeNodeRespVO>> nodesByLevel = new ArrayList<>();
		while (currentLevel > 0) {
			// 将对应层级的节点分组
			List<TreeNodeRespVO> currentLevelNodes = new ArrayList<>();
			for (int i = 0; i < nodes.size(); i++) {
				TreeNodeRespVO current = nodes.get(i);
				if (currentLevel == current.getLevel()) {
					// 将节点添加到某个分组
					currentLevelNodes.add(current);
					// 分好组的节点不需要再遍历了，从原集合移除。动态规划......
					// 动态规划个鸡毛，遍历只遍历一边，移除了有什么用，还让下标错了
					// nodes.remove(i);
				}
			}
			// 存储按所处层级分组后的节点
			nodesByLevel.add(currentLevelNodes);
			currentLevel--;
		}

		// 构建树节点
		for (int i = 0; i < nodesByLevel.size() - 1; i++) {
			List<TreeNodeRespVO> currentLevelNodes = nodesByLevel.get(i);
			List<TreeNodeRespVO> parentNodes = nodesByLevel.get(i + 1);
			// 遍历子节点
			for (int j = 0; j < currentLevelNodes.size(); j++) {
				TreeNodeRespVO node = currentLevelNodes.get(j);
				// TODO 将子节点添加到父节点 考虑用map优化？
				for (int k = 0; k < parentNodes.size(); k++) {
					TreeNodeRespVO parent = parentNodes.get(k);
					if (parent.getKey() == node.getPid()) {
						if (parent.getChildren() == null) {
							parent.setChildren(new ArrayList<>());
						}
						parent.getChildren().add(node);
					}
				}
			}
		}

		// 获取顶部节点的level
		// 不一定都是1。例如侧边栏是属于某个模块的，在数据库维护的level是2
		Integer topLevel = nodes.get(0)
				.getLevel();

		// 返回顶层节点
		return nodesByLevel.get(nodesByLevel.size() - topLevel);
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
			vo.setLabel(node.getName());
			vo.setKey(node.getId());
			vo.setUrl(node.getRoutingAddress());
			voList.add(vo);
		});
		return voList;
	}


	/**
	 *
	 */
	public static List<HeaderMenuRespVO> parseHeaderMenuDTO2VO(List<TreeNode> nodes) {
		List<HeaderMenuRespVO> voList = new ArrayList<>();
		nodes.forEach(node -> {
			HeaderMenuRespVO vo = new HeaderMenuRespVO();
			BeanUtils.copyProperties(node, vo);
			vo.setLabel(node.getName());
			vo.setKey(String.valueOf(node.getId()));
			vo.setUrl(node.getRoutingAddress());
			voList.add(vo);
		});
		return voList;
	}


	/**
	 * 构建【模块信息】树
	 */
	public static List<ModuleRespVO> buildAscOrderdModuleRespVOTree(List<ModuleRespVO> nodes) {

		// 参数校验、异常处理
		if (nodes == null || nodes.size() < 1) {
			throw new RuntimeException("节点集合为空");
		}

		// 从最深的层级开始分组
		Integer currentLevel = nodes.get(nodes.size() - 1).getLevel();

		if (currentLevel == null || currentLevel < 1) {
			throw new RuntimeException("节点【所处层级】字段维护有误");
		}


		// 存储每个层级的节点
		List<List<ModuleRespVO>> nodesByLevel = new ArrayList<>();
		while (currentLevel > 0) {
			// 将对应层级的节点分组
			List<ModuleRespVO> currentLevelNodes = new ArrayList<>();
			for (int i = 0; i < nodes.size(); i++) {
				ModuleRespVO current = nodes.get(i);
				if (currentLevel == current.getLevel()) {
					// 将节点添加到某个分组
					currentLevelNodes.add(current);
				}
			}
			// 存储按所处层级分组后的节点
			nodesByLevel.add(currentLevelNodes);
			currentLevel--;
		}

		// 构建树节点
		for (int i = 0; i < nodesByLevel.size() - 1; i++) {
			List<ModuleRespVO> currentLevelNodes = nodesByLevel.get(i);
			List<ModuleRespVO> parentNodes = nodesByLevel.get(i + 1);
			// 遍历子节点
			for (int j = 0; j < currentLevelNodes.size(); j++) {
				ModuleRespVO node = currentLevelNodes.get(j);
				// TODO 将子节点添加到父节点 考虑用map优化？
				for (int k = 0; k < parentNodes.size(); k++) {
					ModuleRespVO parent = parentNodes.get(k);
					if (parent.getKey() == node.getPid()) {
						// 如果为空，则初始化子节点。
						// 为什么不一来就new一个呢？因为前端只要children不是null，就算是空的，也会渲染展开的效果
						if (parent.getChildren() == null) {
							parent.setChildren(new ArrayList<>());
						}
						parent.getChildren().add(node);
					}
				}
			}
		}

		// 返回顶层节点
		return nodesByLevel.get(nodesByLevel.size() - 1);

	}
}
