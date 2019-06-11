package com.cn.wisdom.spv.model;

import java.io.Serializable;

import com.cn.wisdom.utils.Column;
import com.cn.wisdom.utils.TableInfoAnnotation;

/**
 * @author mr.zhao
 * @date 2019-06-06
 */
@SuppressWarnings("serial")
@TableInfoAnnotation(tableName = "info_report", primaryKey = "id")
public class InfoReport implements Serializable{
	
	
	@Column(name = "id")
    private String id;

	@Column(name = "tel")
    private String tel;

	@Column(name = "card_no")
    private String cardNo;

	@Column(name = "report_name")
    private String reportName;

	@Column(name = "reported_name")
    private String reportedName;

	@Column(name = "company")
    private String company;

	@Column(name = "job")
    private String job;

	@Column(name = "title")
    private String title;

	@Column(name = "content")
    private String content;

	@Column(name = "report_type")
    private String reportType;
	
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName == null ? null : reportedName.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }
    
    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}