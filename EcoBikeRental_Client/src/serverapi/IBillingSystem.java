package serverapi;

import model.CreditCard;
import model.ResponseObj;

public interface IBillingSystem {
	public ResponseObj processTransaction(CreditCard card, double amount);
}
