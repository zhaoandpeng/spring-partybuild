package com.cn.wisdom.pwm.model;

import java.io.Serializable;

import com.cn.wisdom.utils.Column;
import com.cn.wisdom.utils.TableInfoAnnotation;
/**
 * @author mr.zhao
 * @date 2019-06-06
 */
@SuppressWarnings("serial")
@TableInfoAnnotation(tableName = "party_pay_fees", primaryKey = "id")
public class PayPartyFees implements Serializable{

	@Column(name = "id")
	private String id;

	@Column(name = "title")
    private String title;

	@Column(name = "org_id")
    private String orgId;

	@Column(name = "info_type")
    private Integer infoType;

	@Column(name = "create_time")
    private String createTime;

	@Column(name = "update_time")
    private String updateTime;

	@Column(name = "order_no")
    private Integer orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
} 
