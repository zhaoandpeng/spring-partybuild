package com.cn.wisdom.utils;

import java.util.ArrayList;
import java.util.List;




public class TreeUtils{

	public static <T> Tree<T> build(List<Tree<T>> nodes) {

		if(nodes == null){ return null; }
		
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

//			String pid = children.getPid();
			String pid = children.getParentId();
			
			if (pid.equals("0")) {
				
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				
				String id = parent.getId();
				
				if (id != null && id.equals(pid)) {
					
					parent.getChildren().add(children);
					
					children.setParent(true);
					
					parent.setIsChildren(true);

					continue;
				}
			}

		}

		Tree<T> root = new Tree<T>();
		
		if (topNodes.size() == 1) {
			
			root = topNodes.get(0);
			
		} else {
			
			root.setId("-1");
			
//			root.setPid("");
			root.setParentId("");
			
			root.setParent(false);
			
			root.setIsChildren(true);
			
			root.setChildren(topNodes);
			
//			root.setText("根节点");
			root.setTitle("根节点");

		}

		return root;
	}
	
	
//	public static String listToTree(List<?> list,String id,String pid,String child){
//		
//		
//	}
	
	
	public static void main(String[] args){
//		   List<Map<String,Object>> data = new ArrayList<>();
//		   Map<String,Object> map = new HashMap<>();
//		   map.put("id",1);
//		   map.put("pid",0);
//		   map.put("name","甘肃省");
//		   data.add(map);
//		   Map<String,Object> map2 = new HashMap<>();
//		   map2.put("id",2);
//		   map2.put("pid",1);
//		   map2.put("name","天水市");
//		   data.add(map2);
//		   Map<String,Object> map3 = new HashMap<>();
//		   map3.put("id",3);
//		   map3.put("pid",2);
//		   map3.put("name","秦州区");
//		   data.add(map3);
//		   Map<String,Object> map4 = new HashMap<>();
//		   map4.put("id",4);
//		   map4.put("pid",0);
//		   map4.put("name","北京市");
//		   data.add(map4);
//		   Map<String,Object> map5 = new HashMap<>();
//		   map5.put("id",5);
//		   map5.put("pid",4);
//		   map5.put("name","昌平区");
//		   data.add(map5);
//		   System.out.println(JSON.toJSONString(data));
//		   String result = listToTree(data,"id","pid","children");
//		   System.out.println(result);
		}
}
