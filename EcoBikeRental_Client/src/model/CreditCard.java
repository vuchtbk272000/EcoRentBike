package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

public class CreditCard {
	@JsonProperty("cardId")
	private long cardId;
	@JsonProperty("cardName")
	private String cardName;
	@JsonProperty("cardNumber")
	private String cardNumber;
	@JsonProperty("issuingBank")
	private String issuingBank;
	@JsonProperty("expirationDate")
	private String expirationDate;
	@JsonProperty("securityCode")
	private String securityCode;
	@JsonProperty("balance")
	private Double balance;

	public CreditCard() {
		super();

	}

	public CreditCard(String cardName, String cardNumber, String issuingBank, String expirationDate,
			String securityCode) {
		super();
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.issuingBank = issuingBank;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
