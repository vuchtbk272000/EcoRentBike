package serverapi;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.ParkingLot;

public class ParkingLotApi {
	public static final String PATH = "http://localhost:8081/";
	
	private javax.ws.rs.client.Client client;
	
	public ParkingLotApi() {
		client = ClientBuilder.newClient();
	}
	
	public ArrayList<ParkingLot> getParkingLot() {
		ArrayList<ParkingLot> arr = new ArrayList<ParkingLot>();
		WebTarget webTarget = client.target(PATH).path("parking").path("get-all");
//		webTarget = webTarget.queryParam("id", bikeId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

			arr = response.readEntity(new GenericType<ArrayList<ParkingLot>>() {});	
//		System.out.println(arr);
//		System.out.println(res.getParkingLotId());
		return arr;
	}

	public int deleteParkingLot(long id) {
		WebTarget webTarget = client.target(PATH).path("parking/admin/delete-by-id");
		webTarget = webTarget.queryParam("id", id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		int res = response.readEntity(new GenericType<Integer>() {});
		return res;
	}
	

	public int editParkingLot(ParkingLot parkingLot) {
		long id = parkingLot.getParkingLotId();
		WebTarget webTarget = client.target(PATH).path("parking/admin/update-by-id");
		webTarget = webTarget.queryParam("id", id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(parkingLot, MediaType.APPLICATION_JSON));
		System.out.println("alo");
		int res = response.readEntity(new GenericType<Integer>() {});
		return res;
	}
	
	public ParkingLot getParkingLotById(long id) {
		WebTarget webTarget = client.target(PATH).path("parking").path("get-by-id");
		webTarget = webTarget.queryParam("id", id);
 
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ParkingLot res = response.readEntity(new GenericType<ParkingLot>() {});
//		System.out.println(res.getParkingLotId());
//		System.out.println(res.getParkingLotId());
		return res;
	}

	public int addParkingLot(ParkingLot newParkingLot) {

		WebTarget webTarget = client.target(PATH).path("parking/admin/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newParkingLot, MediaType.APPLICATION_JSON));
		System.out.println("alo");
		int res = response.readEntity(new GenericType<Integer>() {});
		return res;
	}
}
