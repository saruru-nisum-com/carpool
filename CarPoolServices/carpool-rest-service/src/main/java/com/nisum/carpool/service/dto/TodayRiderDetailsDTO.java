package com.nisum.carpool.service.dto;

import java.sql.Timestamp;

public class TodayRiderDetailsDTO {

	private int id;
	private int cpid;
	private String emailid;
	private int status;
	private double rewards;
	private boolean notifyme;
	private int reason;
	private String name;
	private String moblie;
	private Timestamp createddate;
	private Timestamp modifieddate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpid() {
		return cpid;
	}

	public void setCpid(int cpid) {
		this.cpid = cpid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getRewards() {
		return rewards;
	}

	public void setRewards(double rewards) {
		this.rewards = rewards;
	}

	public boolean isNotifyme() {
		return notifyme;
	}

	public void setNotifyme(boolean notifyme) {
		this.notifyme = notifyme;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public Timestamp getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpid;
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + id;
		result = prime * result + ((moblie == null) ? 0 : moblie.hashCode());
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (notifyme ? 1231 : 1237);
		result = prime * result + reason;
		long temp;
		temp = Double.doubleToLongBits(rewards);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + status;
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
		TodayRiderDetailsDTO other = (TodayRiderDetailsDTO) obj;
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
		if (moblie == null) {
			if (other.moblie != null)
				return false;
		} else if (!moblie.equals(other.moblie))
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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

	@Override
	public String toString() {
		return "TodayRiderDetailsDTO [id=" + id + ", cpid=" + cpid + ", emailid=" + emailid + ", status=" + status
				+ ", rewards=" + rewards + ", notifyme=" + notifyme + ", reason=" + reason + ", name=" + name
				+ ", moblie=" + moblie + ", createddate=" + createddate + ", modifieddate=" + modifieddate + "]";
	}

}
