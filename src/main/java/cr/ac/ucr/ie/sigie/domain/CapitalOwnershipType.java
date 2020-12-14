package cr.ac.ucr.ie.sigie.domain;

public class CapitalOwnershipType {
	private int typeId;
	private String typeDescription;

	public CapitalOwnershipType() {
	}

	public CapitalOwnershipType(int typeId, String typeDescription) {
		this.typeId = typeId;
		this.typeDescription = typeDescription;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
