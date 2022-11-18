package utils.makedeposite;

import java.time.LocalDate;


public class MakeDepositeForm {
	private String cardNameInput;
	private String cardNumberValue;
	private String issuingBankValue;
	private String transDescValue;
	private String expirationDateValue;
	private String securityCodeValue;
	
	public MakeDepositeForm(String cardNameInput, String cardNumberValue, String issuingBankValue,
			String transDescValue, String expirationDateValue, String securityCodeValue) {
		super();
		this.cardNameInput = cardNameInput;
		this.cardNumberValue = cardNumberValue;
		this.issuingBankValue = issuingBankValue;
		this.transDescValue = transDescValue;
		this.expirationDateValue = expirationDateValue;
		this.securityCodeValue = securityCodeValue;
	}
	
	public String getCardNameInput() {
		return cardNameInput;
	}
	public void setCardNameInput(String cardNameInput) {
		this.cardNameInput = cardNameInput;
	}
	public String getCardNumberValue() {
		return cardNumberValue;
	}
	public void setCardNumberValue(String cardNumberValue) {
		this.cardNumberValue = cardNumberValue;
	}
	public String getIssuingBankValue() {
		return issuingBankValue;
	}
	public void setIssuingBankValue(String issuingBankValue) {
		this.issuingBankValue = issuingBankValue;
	}
	public String getTransDescValue() {
		return transDescValue;
	}
	public void setTransDescValue(String transDescValue) {
		this.transDescValue = transDescValue;
	}
	public String getExpirationDateValue() {
		return expirationDateValue;
	}
	public void setExpirationDateValue(String expirationDateValue) {
		this.expirationDateValue = expirationDateValue;
	}
	public String getSecurityCodeValue() {
		return securityCodeValue;
	}
	public void setSecurityCodeValue(String securityCodeValue) {
		this.securityCodeValue = securityCodeValue;
	}
}
