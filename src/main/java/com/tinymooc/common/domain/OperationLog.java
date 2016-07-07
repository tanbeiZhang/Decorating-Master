package com.tinymooc.common.domain;

// Generated 2013-9-27 23:42:58 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OperationLog generated by hbm2java
 */
@Entity
@Table(name = "operation_log", catalog = "tinymooc")
public class OperationLog implements java.io.Serializable {

	private String logId;
	private String logExplorer;
	private String logUserIp;
	private String logOperation;
	private Date logDate;
	private String userId;

	public OperationLog() {
	}

	public OperationLog(String logId) {
		this.logId = logId;
	}

	public OperationLog(String logId, String logExplorer, String logUserIp,
			String logOperation, Date logDate, String userId) {
		this.logId = logId;
		this.logExplorer = logExplorer;
		this.logUserIp = logUserIp;
		this.logOperation = logOperation;
		this.logDate = logDate;
		this.userId = userId;
	}

	@Id
	@Column(name = "LOG_ID", unique = true, nullable = false, length = 32)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "LOG_EXPLORER", length = 50)
	public String getLogExplorer() {
		return this.logExplorer;
	}

	public void setLogExplorer(String logExplorer) {
		this.logExplorer = logExplorer;
	}

	@Column(name = "LOG_USER_IP", length = 50)
	public String getLogUserIp() {
		return this.logUserIp;
	}

	public void setLogUserIp(String logUserIp) {
		this.logUserIp = logUserIp;
	}

	@Column(name = "LOG_OPERATION", length = 50)
	public String getLogOperation() {
		return this.logOperation;
	}

	public void setLogOperation(String logOperation) {
		this.logOperation = logOperation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_DATE", length = 19)
	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
