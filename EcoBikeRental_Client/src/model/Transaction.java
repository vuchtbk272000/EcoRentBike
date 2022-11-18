package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.CreditCard;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	@JsonProperty("transactionId")
	private long transactionId;
	@JsonProperty("card")
	private CreditCard card;
	@JsonProperty("bike")
	private Bike bike;
	@JsonProperty("amount")
	private double amount;
	@JsonProperty("startTime")
	private String startTime;
	@JsonProperty("endTime")
	private String endTime;
	@JsonProperty("closed")
	private boolean isClosed;
	@JsonProperty("description")
	private String description;

	public Transaction(CreditCard card, Bike bike, double amount, String startTime, String description) {
		super();
		this.card = card;
		this.bike = bike;
		this.amount = amount;
		this.startTime = startTime;
		this.description = description;
		this.isClosed = false;
	}

	public Transaction() {
		super();
	}

	public long getTransactionId() {
		return transactionId;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	public Bike getBike() {
		return bike;
	}
	
	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}
}
