package cr.ac.ucr.ie.sigie.domain;

public class Specialization {
	private int specializationId;
	private String specialization;

	public Specialization() {
		super();
	}

	public Specialization(String specialization) {
		super();
		this.specialization = specialization;
	}

	public int getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(int specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

}
