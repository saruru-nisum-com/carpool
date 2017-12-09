package com.nisum.carpool.service.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageContextDto {
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approveSub == null) ? 0 : approveSub.hashCode());
		result = prime * result + ((cancelSub == null) ? 0 : cancelSub.hashCode());
		result = prime * result + ((msgApproveSuffix == null) ? 0 : msgApproveSuffix.hashCode());
		result = prime * result + ((msgCancelSuffix == null) ? 0 : msgCancelSuffix.hashCode());
		result = prime * result + ((msgNewPoolPrefix == null) ? 0 : msgNewPoolPrefix.hashCode());
		result = prime * result + ((msgNewPoolSuffix == null) ? 0 : msgNewPoolSuffix.hashCode());
		result = prime * result + ((msgPrefix == null) ? 0 : msgPrefix.hashCode());
		result = prime * result + ((msgRejectSuffix == null) ? 0 : msgRejectSuffix.hashCode());
		result = prime * result + ((msgWish == null) ? 0 : msgWish.hashCode());
		result = prime * result + ((msgWishApprove == null) ? 0 : msgWishApprove.hashCode());
		result = prime * result + ((rejectSub == null) ? 0 : rejectSub.hashCode());
		result = prime * result + ((riderCancelSub == null) ? 0 : riderCancelSub.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageContextDto other = (MessageContextDto) obj;
		if (approveSub == null) {
			if (other.approveSub != null)
				return false;
		} else if (!approveSub.equals(other.approveSub))
			return false;
		if (cancelSub == null) {
			if (other.cancelSub != null)
				return false;
		} else if (!cancelSub.equals(other.cancelSub))
			return false;
		if (msgApproveSuffix == null) {
			if (other.msgApproveSuffix != null)
				return false;
		} else if (!msgApproveSuffix.equals(other.msgApproveSuffix))
			return false;
		if (msgCancelSuffix == null) {
			if (other.msgCancelSuffix != null)
				return false;
		} else if (!msgCancelSuffix.equals(other.msgCancelSuffix))
			return false;
		if (msgNewPoolPrefix == null) {
			if (other.msgNewPoolPrefix != null)
				return false;
		} else if (!msgNewPoolPrefix.equals(other.msgNewPoolPrefix))
			return false;
		if (msgNewPoolSuffix == null) {
			if (other.msgNewPoolSuffix != null)
				return false;
		} else if (!msgNewPoolSuffix.equals(other.msgNewPoolSuffix))
			return false;
		if (msgPrefix == null) {
			if (other.msgPrefix != null)
				return false;
		} else if (!msgPrefix.equals(other.msgPrefix))
			return false;
		if (msgRejectSuffix == null) {
			if (other.msgRejectSuffix != null)
				return false;
		} else if (!msgRejectSuffix.equals(other.msgRejectSuffix))
			return false;
		if (msgWish == null) {
			if (other.msgWish != null)
				return false;
		} else if (!msgWish.equals(other.msgWish))
			return false;
		if (msgWishApprove == null) {
			if (other.msgWishApprove != null)
				return false;
		} else if (!msgWishApprove.equals(other.msgWishApprove))
			return false;
		if (rejectSub == null) {
			if (other.rejectSub != null)
				return false;
		} else if (!rejectSub.equals(other.rejectSub))
			return false;
		if (riderCancelSub == null) {
			if (other.riderCancelSub != null)
				return false;
		} else if (!riderCancelSub.equals(other.riderCancelSub))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageContextDto [subject=" + subject + ", approveSub=" + approveSub + ", rejectSub=" + rejectSub
				+ ", cancelSub=" + cancelSub + ", riderCancelSub=" + riderCancelSub + ", msgNewPoolPrefix="
				+ msgNewPoolPrefix + ", msgNewPoolSuffix=" + msgNewPoolSuffix + ", msgPrefix=" + msgPrefix
				+ ", msgApproveSuffix=" + msgApproveSuffix + ", msgRejectSuffix=" + msgRejectSuffix
				+ ", msgCancelSuffix=" + msgCancelSuffix + ", msgWishApprove=" + msgWishApprove + ", msgWish=" + msgWish
				+ "]";
	}
	
}
