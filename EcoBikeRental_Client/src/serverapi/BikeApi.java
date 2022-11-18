package serverapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.Bike;
import model.Transaction;

public class BikeApi {
	public static final String PATH = "http://localhost:8081/";
	
	private javax.ws.rs.client.Client client;
	
	public BikeApi() {
		client = ClientBuilder.newClient();
	}
	
	public Bike getBikeByID(long bikeId) {
		WebTarget webTarget = client.target(PATH).path("bike").path("get-by-id");
		webTarget = webTarget.queryParam("id", bikeId);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		Bike res = response.readEntity(new GenericType<Bike>() {});
		return res;
	}
	
	public List<Bike> getAllBikes() {
		WebTarget webTarget = client.target(PATH).path("bike").path("get-all");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		List<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {});
		return res;
	}
    
    public Bike addBike(Bike bike) {
    	WebTarget webTarget = client.target(PATH).path("bike").path("admin").path("add");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));

		Bike res = response.readEntity(new GenericType<Bike>() {});
		return res;
    }
    
    public Bike updateBike(Bike bike) {
    	WebTarget webTarget = client.target(PATH).path("bike").path("admin").path("update-by-id");
    	webTarget = webTarget.queryParam("id", bike.getBikeId());
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));

		Bike res = response.readEntity(new GenericType<Bike>() {});
		if(res.getBikeId() != 0) { // server return an emptry json object
			System.out.println("update successfully!");
		}
		else System.out.println("update failed!");
		return res;
    }
    
    public boolean deleteBike(Bike bike) {
    	WebTarget webTarget = client.target(PATH).path("bike").path("admin").path("delete-by-id");
    	webTarget = webTarget.queryParam("id", bike.getBikeId());
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));

		boolean res = response.readEntity(new GenericType<Boolean>() {});
		if(res == true) {
			System.out.println("delete successfully!");
		}
		else System.out.println("delete failed!");
		return res;
    }
}
