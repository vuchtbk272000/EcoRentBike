package serverapi;

import model.Transaction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class TransactionApi {
    public static final String PATH = "http://localhost:8081/";

    private Client client;

    public TransactionApi() {
        client = ClientBuilder.newClient();
    }

    public Transaction getOpenTransactionRecordByBike(long bikeId) {
        WebTarget webTarget = client.target(PATH).path("transaction").path("get-open-transaction-by-id");
        webTarget = webTarget.queryParam("bikeId", bikeId);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Transaction res = response.readEntity(new GenericType<Transaction>() {});
        if (!Objects.isNull(res)) {
        	System.out.println(res);
        	return res;        	
        }
        return null;
    }
    

	/**
	 * Call API to save the transaction
	 * 
	 * @param transaction
	 * 
	 * @return <Transaction>
	 */

    public Transaction saveTransaction(Transaction transaction) {
    	WebTarget webTarget = client.target(PATH).path("transaction");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(transaction, MediaType.APPLICATION_JSON));

		Transaction res = response.readEntity(new GenericType<Transaction>() {});
		System.out.println(res);
		return res;
    }
}
	