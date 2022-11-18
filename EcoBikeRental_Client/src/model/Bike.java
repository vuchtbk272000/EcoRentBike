package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bike {
	@JsonProperty("bikeId")
	private long bikeId;
	@JsonProperty("type")
    private int type;
	@JsonProperty("weight")
    private double weight;
	@JsonProperty("cost")
    private double cost;
	@JsonProperty("licensePlate")
    private String licensePlate;
	@JsonProperty("manufacturingDate")
//	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
    private String manufacturingDate;
	@JsonProperty("producer")
    private String producer;
    @JsonProperty("parkingLot")
    private ParkingLot parkingLot;

	public long getBikeId() {
		return bikeId;
	}
	public void setBikeId(long bikeId) {
		this.bikeId = bikeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Bike(int type, double weight, double cost, String licensePlate, String manufacturingDate,
			ParkingLot parkingLot) {
		super();
		this.type = type;
		this.weight = weight;
		this.cost = cost;
		this.licensePlate = licensePlate;
		this.manufacturingDate = manufacturingDate;
		this.parkingLot = parkingLot;
	}
	public Bike() {
		super();
		// TODO Auto-generated constructor stub
	}
}
