package com.nisum.carpool.service.dto;

import org.springframework.data.cassandra.mapping.PrimaryKey;

public class CarpoolRiderNotificationsDTO {
	@PrimaryKey
	Integer id;
	Integer cpid;
	String emailid;
	boolean notified;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public boolean isNotified() {
		return notified;
	}
	public void setNotified(boolean notified) {
		this.notified = notified;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpid == null) ? 0 : cpid.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (notified ? 1231 : 1237);
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
		CarpoolRiderNotificationsDTO other = (CarpoolRiderNotificationsDTO) obj;
		if (cpid == null) {
			if (other.cpid != null)
				return false;
		} else if (!cpid.equals(other.cpid))
			return false;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notified != other.notified)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CarpoolRiderNotificationsDTO [id=" + id + ", cpid=" + cpid + ", emailid=" + emailid + ", notified="
				+ notified + "]";
	}
	
}
