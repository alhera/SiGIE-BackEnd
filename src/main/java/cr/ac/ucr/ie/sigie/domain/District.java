package cr.ac.ucr.ie.sigie.domain;

public class District {
	private int districtId;
	private String districtName;

	public District() {
	}

	public District(int districtId, String districtName) {
		this.districtId = districtId;
		this.districtName = districtName;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
