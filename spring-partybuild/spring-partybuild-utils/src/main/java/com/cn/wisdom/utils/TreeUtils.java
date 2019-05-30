package com.cn.wisdom.utils;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ListTree implements Serializable{

	
	private String id;
	
	
	private String pid;
	
	
	private String name;
	
	
	private List<Object> childrenList;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public List<Object> getChildrenList() {
		return childrenList;
	}


	public void setChildrenList(List<Object> childrenList) {
		this.childrenList = childrenList;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
