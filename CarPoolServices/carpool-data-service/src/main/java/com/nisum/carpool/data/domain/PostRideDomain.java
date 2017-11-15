package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("cp_carpooldetails")
public class PostRideDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private Integer id;
	private Integer parentid;
	private String userid;
	private Integer vehicleType;
	private Integer noofseats;
	private String fromDate;
	private String toDate;
	private String fromtime;
	private String toTime;
	private Integer status;
	private Timestamp createddate;
	private Timestamp modifieddate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Integer getNoofseats() {
		return noofseats;
	}
	public void setNoofseats(Integer noofseats) {
		this.noofseats = noofseats;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getStartTime() {
		return fromtime;
	}
	public void setStartTime(String fromtime) {
		this.fromtime = fromtime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
		result = prime * result + ((fromtime == null) ? 0 : fromtime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((noofseats == null) ? 0 : noofseats.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
		PostRideDomain other = (PostRideDomain) obj;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		if (createddate == null) {
			if (other.createddate != null)
				return false;
		} else if (!createddate.equals(other.createddate))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (parentid == null) {
			if (other.parentid != null)
				return false;
		} else if (!parentid.equals(other.parentid))
			return false;
		if (fromtime == null) {
			if (other.fromtime != null)
				return false;
		} else if (!fromtime.equals(other.fromtime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		if (noofseats == null) {
			if (other.noofseats != null)
				return false;
		} else if (!noofseats.equals(other.noofseats))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PostRideDomain [id=" + id + ", parentid=" + parentid + ", userid=" + userid + ", vehicleType="
				+ vehicleType + ", noofseats=" + noofseats + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", fromtime=" + fromtime + ", toTime=" + toTime + ", status=" + status + ", createddate="
				+ createddate + ", modifieddate=" + modifieddate + "]";
	}
}
