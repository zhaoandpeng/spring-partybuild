package com.cn.wisdom.base.model;

import java.io.Serializable;
import java.util.Date;

import com.cn.wisdom.utils.Column;
import com.cn.wisdom.utils.TableInfoAnnotation;

/**
 * 
 * 
 * @author mr.zhao
 * 
 * @date 2019-04-23
 */
@TableInfoAnnotation(tableName = "base_menu", primaryKey = "id")
public class BaseMenu implements Serializable{
    
	private static final long serialVersionUID = -2077714120763534169L;

	@Column(name = "ID")
    private String id; //主键

	@Column(name = "PID")
    private String pid;//父级主键

	@Column(name = "MENU_NAME")
    private String menuName;//菜单名称

	@Column(name = "MENU_URL")
    private String menuUrl;//菜单链接

	@Column(name = "MENU_ICON")
    private String menuIcon;//菜单图标

	@Column(name = "ORDER_NO")
    private Integer orderNo;//排序号
    
	@Column(name = "IS_DISABLED")
    private String isDisabled;//排序号

	@Column(name = "CREATOR_ID")
    private String creatorId;//创建人主键

	@Column(name = "CREATOR_NAME")
    private String creatorName;//创建人名称

	@Column(name = "CREATE_DATE")
    private Date createDate;//创建日期

	
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}