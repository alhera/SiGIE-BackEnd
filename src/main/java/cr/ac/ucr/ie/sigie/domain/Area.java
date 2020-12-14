package cr.ac.ucr.ie.sigie.domain;

public class Area {

	private String areaId;
	private String areaName;

	public Area() {

		this.areaId = "";
		this.areaName = "";
	}

	public Area(String areaId, String areaName) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
