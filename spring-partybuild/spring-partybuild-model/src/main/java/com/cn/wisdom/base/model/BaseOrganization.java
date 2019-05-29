package com.cn.wisdom.base.model;

import java.io.Serializable;

import com.cn.wisdom.utils.TableInfoAnnotation;

@SuppressWarnings("serial")
@TableInfoAnnotation(tableName = "base_organization", primaryKey = "id")
public class BaseOrganization implements Serializable{

	private String id;
	
	private String pid;
	
	private String orgName; //机构名称
	
	private int orderNo; //排序号默认自增
	
	private String address; //部门地址
	
	private String remark; //描述

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
