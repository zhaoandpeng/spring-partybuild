package com.cn.wisdom.info.model;

import java.io.Serializable;

import com.cn.wisdom.utils.TableInfoAnnotation;
@TableInfoAnnotation(tableName = "info_partyArticleRead", primaryKey = "id")
public class InfoPartyArticleRead implements Serializable{

	private static final long serialVersionUID = -8583996879350489275L;
	
	private String id; //主键
	
	private String pid; //父键
	
	private String readUserName; //阅读人名称
	
	private String readUserNo; //阅读人工号
	
	private String readTime; //阅读时间
	
	private Integer orderNo; //默认排序号

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

	public String getReadUserName() {
		return readUserName;
	}

	public void setReadUserName(String readUserName) {
		this.readUserName = readUserName;
	}

	public String getReadUserNo() {
		return readUserNo;
	}

	public void setReadUserNo(String readUserNo) {
		this.readUserNo = readUserNo;
	}

	public String getReadTime() {
		return readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
