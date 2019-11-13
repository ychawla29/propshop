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
public class ImageBean {

	private String imageId,propertyId,ImageURL;

	
	public ImageBean() {
		super();
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public ImageBean(String imageId, String propertyId, String imageURL) {
		super();
		this.imageId = imageId;
		this.propertyId = propertyId;
		ImageURL = imageURL;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageBean other = (ImageBean) obj;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}

	public String toString() {
		return "ImageBean [imageId=" + imageId + ", propertyId=" + propertyId + ", ImageURL=" + ImageURL + "]";
	}
	
	
}

