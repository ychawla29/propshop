/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Yogesh Chawla
 */
public class PropertySoldBean {
	private String propertyId,userId;

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PropertySoldBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertySoldBean other = (PropertySoldBean) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public PropertySoldBean(String propertyId, String userId) {
		super();
		this.propertyId = propertyId;
		this.userId = userId;
	}

	public String toString() {
		return "PropertySold [propertyId=" + propertyId + ", userId=" + userId + "]";
	}
	

}

