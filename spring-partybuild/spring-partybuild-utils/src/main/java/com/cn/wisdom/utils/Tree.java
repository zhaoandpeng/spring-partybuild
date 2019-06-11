package com.cn.wisdom.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tree<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1942908845755282627L;
	
	
	private String id; //节点ID
	
//	private String text; //显示节点文本
	private String title; //显示节点文本

	private String state = "open"; //节点状态，open closed
	
	private String checkArr ; //节点是否被选中 true false
	
	private List<Map<String, Object>> attributes; //节点属性
	
	private List<Tree<T>> children = new ArrayList<Tree<T>>(); //节点的子节点
	
	private String parentId; //父ID
	
	private boolean isParent = false; //是否有父节点
	
	private boolean isChildren = false; //是否有子节点

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCheckArr() {
		return checkArr;
	}

	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}

	public List<Map<String, Object>> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Map<String, Object>> attributes) {
		this.attributes = attributes;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}

//	public String getPid() {
//		return pid;
//	}
//
//	public void setPid(String pid) {
//		this.pid = pid;
//	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean getIsChildren() {
		return isChildren;
	}

	public void setIsChildren(boolean isChildren) {
		this.isChildren = isChildren;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
