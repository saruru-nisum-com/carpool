package com.nisum.carpool.service.dto;

/**
 * 
 * @author durga manjari narni
 *
 */
public class VehicleTypesDTO {

	int id;
	int noofseats;
	String vehicletype; 
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
	 * @return the noofseats
	 */
	public int getNoofseats() {
		return noofseats;
	}
	/**
	 * @param noofseats the noofseats to set
	 */
	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}
	/**
	 * @return the vehicletype
	 */
	public String getVehicletype() {
		return vehicletype;
	}
	/**
	 * @param vehicletype the vehicletype to set
	 */
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + noofseats;
		result = prime * result + ((vehicletype == null) ? 0 : vehicletype.hashCode());
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
		VehicleTypesDTO other = (VehicleTypesDTO) obj;
		if (id != other.id)
			return false;
		if (noofseats != other.noofseats)
			return false;
		if (vehicletype == null) {
			if (other.vehicletype != null)
				return false;
		} else if (!vehicletype.equals(other.vehicletype))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VehicleDetails [id=" + id + ", noofseats=" + noofseats + ", vehicletype=" + vehicletype + "]";
	}
}
