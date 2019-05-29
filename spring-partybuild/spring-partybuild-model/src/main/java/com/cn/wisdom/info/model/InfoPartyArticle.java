package com.cn.wisdom.info.model;

import java.io.Serializable;

import com.cn.wisdom.utils.TableInfoAnnotation;

@TableInfoAnnotation(tableName = "info_partyArticle", primaryKey = "id")
public class InfoPartyArticle implements Serializable{

	private static final long serialVersionUID = -560950972519467999L;
	
	private String id; //主键
	
	private String title; //文章标题

	private String checkerName; //审核员名称
	
	private String checkerUserNo; //审核员工号
	
	private String articleSource; //文章来源
	
	private String updateTime; //文章更新时间
	
	private String createTime; //文章创建时间
	
	private String createName; //文章创建人名称
	
	private String createUserNo; //文章创建人工号
	
	private String content; //文章内容
	
	private String read; //是否必读
	
	private String receiveOrgId; //下发机构主键
	
	private String status = "2"; //文章状态  [0=已删除 1=被驳回  2=审核中 3=已发布]= "2"
	
	private Integer orderNo; //自增排序号
	
	private String type; //文章类型  [通知公告=0]
	
	private String delFlag = "0"; //删除标识  [未删除=0 1=已删除]

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName == null? null: checkerName.trim();
	}

	public String getCheckerUserNo() {
		return checkerUserNo;
	}

	public void setCheckerUserNo(String checkerUserNo) {
		this.checkerUserNo = checkerUserNo == null? null: checkerUserNo.trim();
	}

	public String getArticleSource() {
		return articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource == null? null: articleSource.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null? null: updateTime.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null? null: createTime.trim();
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName == null? null: createName.trim();
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo == null? null: createUserNo.trim();
	}

	public String getContent() { return content; }
	

	public void setContent(String content) { 
		this.content = content == null? null: content;
	}
	 

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getReceiveOrgId() {
		return receiveOrgId;
	}

	public void setReceiveOrgId(String receiveOrgId) {
		this.receiveOrgId = receiveOrgId == null? null: receiveOrgId.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status  == null? this.status: status.trim();
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null? null: type.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
