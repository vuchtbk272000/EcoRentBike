package utils.addbike;

public class AddBikeForm {
	String typeInput;
	String weightInput;
	String costInput;
	String licensePlateInput;
	String parkingLotIdInput;
	String manufacturingDateInput;
	
	public AddBikeForm(String typeInput, String weightInput, String costInput, String licensePlateInput,
			String parkingLotIdInput, String manufacturingDateInput) {
		super();
		this.typeInput = typeInput;
		this.weightInput = weightInput;
		this.costInput = costInput;
		this.licensePlateInput = licensePlateInput;
		this.parkingLotIdInput = parkingLotIdInput;
		this.manufacturingDateInput = manufacturingDateInput;
	}
	public String getTypeInput() {
		return typeInput;
	}
	public void setTypeInput(String typeInput) {
		this.typeInput = typeInput;
	}
	public String getWeightInput() {
		return weightInput;
	}
	public void setWeightInput(String weightInput) {
		this.weightInput = weightInput;
	}
	public String getCostInput() {
		return costInput;
	}
	public void setCostInput(String costInput) {
		this.costInput = costInput;
	}
	public String getLicensePlateInput() {
		return licensePlateInput;
	}
	public void setLicensePlateInput(String licensePlateInput) {
		this.licensePlateInput = licensePlateInput;
	}
	public String getParkingLotIdInput() {
		return parkingLotIdInput;
	}
	public void setParkingLotIdInput(String parkingLotIdInput) {
		this.parkingLotIdInput = parkingLotIdInput;
	}
	public String getManufacturingDateInput() {
		return manufacturingDateInput;
	}
	public void setManufacturingDateInput(String manufacturingDateInput) {
		this.manufacturingDateInput = manufacturingDateInput;
	}
}
