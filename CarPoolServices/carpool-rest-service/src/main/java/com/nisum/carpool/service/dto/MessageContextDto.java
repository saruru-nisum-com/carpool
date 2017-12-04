package com.nisum.carpool.service.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageContextDto {
	
	@Value("${header.filepath}")
	private String headerPath;
	
	@Value("${footer.filepath}")
	private String footerpath;
	
	@Value("${gmail.subject}")
	private String subject;
	
	@Value("${gmail.carpool.driver.approve.subject}")
	private String approveSub;
	
	@Value("${gmail.carpool.driver.reject.subject}")
	private String rejectSub;
  
	@Value("${gmail.carpool.driver.cancel.subject}")
	private String cancelSub;
	
	@Value("${gmail.carpool.rider.cancel.subject}")
	private String riderCancelSub;

	@Value("${mail.message.newpool.suffix}")
	private String msgNewPoolPrefix;
	
	@Value("${mail.message.newpool.suffix}")
	private String msgNewPoolSuffix;
	
	@Value("${mail.message.prefix}")
	private String msgPrefix;
	
	@Value("${mail.message.approve.suffix}")
	private String msgApproveSuffix;
	
	@Value("${mail.message.reject.suffix}")
	private String msgRejectSuffix;
	
	@Value("${mail.message.cancel.suffix}")
	private String msgCancelSuffix;
	
	@Value("${mail.wish.approve}")
	private String msgWishApprove;
	
	@Value("${mail.wish.reject.cancel}")
	private String msgWish;

	public String getHeaderPath() {
		return headerPath;
	}

	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
	}

	public String getFooterpath() {
		return footerpath;
	}

	public void setFooterpath(String footerpath) {
		this.footerpath = footerpath;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getApproveSub() {
		return approveSub;
	}

	public void setApproveSub(String approveSub) {
		this.approveSub = approveSub;
	}

	public String getRejectSub() {
		return rejectSub;
	}

	public void setRejectSub(String rejectSub) {
		this.rejectSub = rejectSub;
	}

	public String getCancelSub() {
		return cancelSub;
	}

	public void setCancelSub(String cancelSub) {
		this.cancelSub = cancelSub;
	}

	public String getRiderCancelSub() {
		return riderCancelSub;
	}

	public void setRiderCancelSub(String riderCancelSub) {
		this.riderCancelSub = riderCancelSub;
	}

	public String getMsgNewPoolPrefix() {
		return msgNewPoolPrefix;
	}

	public void setMsgNewPoolPrefix(String msgNewPoolPrefix) {
		this.msgNewPoolPrefix = msgNewPoolPrefix;
	}

	public String getMsgNewPoolSuffix() {
		return msgNewPoolSuffix;
	}

	public void setMsgNewPoolSuffix(String msgNewPoolSuffix) {
		this.msgNewPoolSuffix = msgNewPoolSuffix;
	}

	public String getMsgPrefix() {
		return msgPrefix;
	}

	public void setMsgPrefix(String msgPrefix) {
		this.msgPrefix = msgPrefix;
	}

	public String getMsgApproveSuffix() {
		return msgApproveSuffix;
	}

	public void setMsgApproveSuffix(String msgApproveSuffix) {
		this.msgApproveSuffix = msgApproveSuffix;
	}

	public String getMsgRejectSuffix() {
		return msgRejectSuffix;
	}

	public void setMsgRejectSuffix(String msgRejectSuffix) {
		this.msgRejectSuffix = msgRejectSuffix;
	}

	public String getMsgCancelSuffix() {
		return msgCancelSuffix;
	}

	public void setMsgCancelSuffix(String msgCancelSuffix) {
		this.msgCancelSuffix = msgCancelSuffix;
	}

	public String getMsgWishApprove() {
		return msgWishApprove;
	}

	public void setMsgWishApprove(String msgWishApprove) {
		this.msgWishApprove = msgWishApprove;
	}

	public String getMsgWish() {
		return msgWish;
	}

	public void setMsgWish(String msgWish) {
		this.msgWish = msgWish;
	}
}
