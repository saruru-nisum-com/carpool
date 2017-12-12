package com.nisum.carpool.service.dto;

import java.sql.Timestamp;



public class CarpoolRiderDetailsDTO {
	
	 private  int	id; 
	 private int cpid;
	 private String emailid;
	 private int	status;
	 private double rewards;
	 private  boolean notifyme;
	 private int reason;
     private Timestamp  createddate;
	 private Timestamp modifieddate;
	 private String location;
	 
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the cpid
	 */
	public int getCpid() {
		return cpid;
	}
	/**
	 * @param cpid the cpid to set
	 */
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	/**
	 * @return the emailid
	 */
	public String getEmailid() {
		return emailid;
	}
	/**
	 * @param emailid the emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the rewards
	 */
	public double getRewards() {
		return rewards;
	}
	/**
	 * @param rewards the rewards to set
	 */
	public void setRewards(double rewards) {
		this.rewards = rewards;
	}
	/**
	 * @return the notifyme
	 */
	public boolean isNotifyme() {
		return notifyme;
	}
	/**
	 * @param notifyme the notifyme to set
	 */
	public void setNotifyme(boolean notifyme) {
		this.notifyme = notifyme;
	}
	/**
	 * @return the reason
	 */
	public int getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(int reason) {
		this.reason = reason;
	}
	/**
	 * @return the createddate
	 */
	public Timestamp getCreateddate() {
		return createddate;
	}
	/**
	 * @param createddate the createddate to set
	 */
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	/**
	 * @return the modifieddate
	 */
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	/**
	 * @param modifieddate the modifieddate to set
	 */
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpid;
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + id;
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + (notifyme ? 1231 : 1237);
		result = prime * result + reason;
		long temp;
		temp = Double.doubleToLongBits(rewards);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + status;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarpoolRiderDetailsDTO other = (CarpoolRiderDetailsDTO) obj;
		if (cpid != other.cpid)
			return false;
		if (createddate == null) {
			if (other.createddate != null)
				return false;
		} else if (!createddate.equals(other.createddate))
			return false;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (id != other.id)
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (notifyme != other.notifyme)
			return false;
		if (reason != other.reason)
			return false;
		if (Double.doubleToLongBits(rewards) != Double.doubleToLongBits(other.rewards))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarpoolRiderDetailsDTO [id=" + id + ", cpid=" + cpid + ", emailid=" + emailid + ", status=" + status
				+ ", rewards=" + rewards + ", notifyme=" + notifyme + ", reason=" + reason + ", createddate="
				+ createddate + ", modifieddate=" + modifieddate + "]";
	}

}
