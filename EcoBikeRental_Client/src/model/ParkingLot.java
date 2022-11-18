package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParkingLot {
    @JsonProperty("parkingLotId")
    private long parkingLotId;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("stationAddress")
    private String stationAddress;
    @JsonProperty("numberOfBikes")
    private int numberOfBikes;
    @JsonProperty("numberOfEBikes")
    private int numberOfEBikes;
    @JsonProperty("numberOfTwinBikes")
    private int numberOfTwinBikes;
    @JsonProperty("maxSlot")
    private int maxSlot;
    @JsonProperty("numberOfEmptySlot")
    private int numberOfEmptySlot;
    
    public long getParkingLotId() {
    	return parkingLotId;
    }

	public void setParkingLotId(long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationAddress() {
		return stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public int getNumberOfBikes() {
		return numberOfBikes;
	}

	public void setNumberOfBikes(int numberOfBikes) {
		this.numberOfBikes = numberOfBikes;
	}

	public int getNumberOfEBikes() {
		return numberOfEBikes;
	}

	public void setNumberOfEBikes(int numberOfEBikes) {
		this.numberOfEBikes = numberOfEBikes;
	}

	public int getNumberOfTwinBikes() {
		return numberOfTwinBikes;
	}

	public void setNumberOfTwinBikes(int numberOfTwinBikes) {
		this.numberOfTwinBikes = numberOfTwinBikes;
	}

	public int getMaxSlot() {
		return maxSlot;
	}

	public void setMaxSlot(int maxSlot) {
		this.maxSlot = maxSlot;
	}

	public int getNumberOfEmptySlot() {
		return numberOfEmptySlot;
	}

	public void setNumberOfEmptySlot(int numberOfEmptySlot) {
		this.numberOfEmptySlot = numberOfEmptySlot;
	}

	public void editInfo(String name, String address, int capa) {
		this.maxSlot = capa;
		this.stationName = name;
		this.stationAddress = address;
	}
	public ParkingLot() {
        super();
    }
}
