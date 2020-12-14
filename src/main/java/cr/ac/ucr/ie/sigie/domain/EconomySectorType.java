package cr.ac.ucr.ie.sigie.domain;

public class EconomySectorType {
	private int sectorID;
	private String typeDescription;

	public EconomySectorType() {
	}

	public EconomySectorType(int sectorID, String typeDescription) {
		this.sectorID = sectorID;
		this.typeDescription = typeDescription;
	}

	public int getSectorID() {
		return sectorID;
	}

	public void setSectorID(int sectorID) {
		this.sectorID = sectorID;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
