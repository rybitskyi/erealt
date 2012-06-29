package biz.rageintegro.erealt.domain;

import java.io.Serializable;

import biz.rageintegro.erealt.helpers.ToStringHelper;
import biz.rageintegro.erealt.reference.RoomCountType;

public class SearchCriteria implements Serializable{
	private OperationType operationType;
	@Deprecated
    private String objectType;
	private District district;
	private Region region; 
	private Street street;
	private RoomCountType roomCount;
	private Integer minPrice;
	private Integer maxPrice;

	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Region getRegion() {
		return region;
	}

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setRegion(Region region) {
		this.region = region;
	}
	public RoomCountType getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(RoomCountType roomCount) {
		this.roomCount = roomCount;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public boolean isEmpty() {
		return district == null && region == null && roomCount == null && minPrice == null && maxPrice == null;
	}

    @Deprecated
    public String getObjectType() {
		return objectType;
	}

    @Deprecated
    public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return ToStringHelper.getStringRepresentation(this);
    }

    public void clear() {
        operationType = null;
        objectType = null;
        district = null;
        region = null;
        street = null;
        roomCount = null;
        minPrice = null;
        maxPrice = null;
    }
}
