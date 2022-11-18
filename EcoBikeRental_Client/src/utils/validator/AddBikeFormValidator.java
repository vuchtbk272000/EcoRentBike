package utils.validator;

import model.ParkingLot;
import serverapi.ParkingLotApi;
import utils.addbike.AddBikeForm;

public class AddBikeFormValidator implements IFormValidator{
	AddBikeForm form;
	public AddBikeFormValidator(AddBikeForm depositeForm) {
		this.form = depositeForm;
	}
	
	public boolean isPossitiveDouble(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	        if(d <= 0) return false;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public boolean isPossitiveLong(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        long d = Long.parseLong(strNum);
	        if(d <= 0) return false;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public boolean isValid() {
		boolean isValidType = isPossitiveLong(form.getTypeInput());
		System.out.println(isValidType);
		boolean isValidWeight = isPossitiveDouble(this.form.getWeightInput());
		System.out.println(isValidWeight);
		boolean isValidCost = isPossitiveDouble(this.form.getCostInput());
		System.out.println(isValidCost);
		boolean isValidParkingLotId = isPossitiveLong(this.form.getParkingLotIdInput());
		if(isValidParkingLotId) {
			long id = Long.valueOf(this.form.getParkingLotIdInput());
			ParkingLotApi parkingLotApi = new ParkingLotApi();
			ParkingLot res = parkingLotApi.getParkingLotById(id);
			System.out.println(res.getParkingLotId());
			if(res.getParkingLotId() == 0) {
				isValidParkingLotId = false;
			}
		}
		System.out.println(isValidParkingLotId);
		boolean isValid = isValidType|| isValidWeight||isValidCost||isValidParkingLotId;
		return isValid;
	}
}
