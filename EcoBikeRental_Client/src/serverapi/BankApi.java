package serverapi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.CreditCard;
import model.ResponseObj;

public class BankApi implements IBillingSystem {
	public static final String PATH = "http://localhost:8081/";

	private Client client;

	public BankApi() {
		client = ClientBuilder.newClient();
	}

	/**
	 * Call API decrease balance credit card
	 * 
	 * @param card
	 * @param amount
	 * 
	 * @return <ResponseObj>
	 */

	@Override
	public ResponseObj processTransaction(CreditCard card, double amount) {
		WebTarget webTarget = client.target(PATH).path("/credit-card/decrease-balance");
		webTarget = webTarget.queryParam("amount", amount);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(card, MediaType.APPLICATION_JSON));

		ResponseObj res = response.readEntity(new GenericType<ResponseObj>() {
		});

		return res;
	}

	public CreditCard testGetCard(String cardNuBuilder) {
		WebTarget webTarget = client.target(PATH).path("/credit-card");
		webTarget = webTarget.queryParam("cardNumber", "77454027");
		System.out.println(webTarget);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		CreditCard res = response.readEntity(new GenericType<CreditCard>() {
		});
		System.out.println(res);
		return res;

	}

	

}
