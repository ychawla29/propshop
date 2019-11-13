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
public class RatingsBean {

	private String userId;
	private int propertyId,ratings;
	public RatingsBean() {
	}
	public RatingsBean(String userId, int propertyId, int ratings) {
		this.userId = userId;
		this.propertyId = propertyId;
		this.ratings = ratings;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
        @Override
	public String toString() {
		return "RatingsBean [userId=" + userId + ", propertyId=" + propertyId + ", ratings=" + ratings + "]";
	}
        @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + propertyId;
		result = prime * result + ratings;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		RatingsBean other = (RatingsBean) obj;
		if (propertyId != other.propertyId)
			return false;
		if (ratings != other.ratings)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}

