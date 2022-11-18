package utils.validator;

import utils.makedeposite.MakeDepositeForm;

public class MakeDepositeFormValidator implements IFormValidator{
	MakeDepositeForm form;
	public MakeDepositeFormValidator(MakeDepositeForm depositeForm) {
		this.form = depositeForm;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		// check is empty
		boolean isEmptyCardName = this.form.getCardNameInput().isEmpty();
		boolean isEmptyCardNumber = this.form.getCardNumberValue().isEmpty();
//		boolean isEmptyCardExpiration = this.form.getExpirationDateValue().isEmpty();
		boolean isEmptyCardBank = this.form.getIssuingBankValue().isEmpty();
		boolean isEmptyCardSecurityCode = this.form.getSecurityCodeValue().isEmpty();
		boolean isEmptyDescription = this.form.getTransDescValue().isEmpty();

		boolean isExistEmptyField = isEmptyCardName || isEmptyCardNumber||isEmptyCardBank ||isEmptyCardSecurityCode||isEmptyDescription;
		if (isExistEmptyField) {
			
			return false;
		}
		return true;
	}
}
