package cr.ac.ucr.ie.sigie.domain;

public class AcademicDegree {

	private int degreeId;
	private String degreeName;
	private String institutionName;
	private String degreeAcronym;
	private Specialization specialization;

	public AcademicDegree() {
		super();
		this.specialization=new Specialization();
	}

	public AcademicDegree(int degreeId, String degreeName, String institutionName, String degreeAcronym,
			Specialization specialization) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
		this.institutionName = institutionName;
		this.degreeAcronym = degreeAcronym;
		this.specialization = specialization;
	}

	public int getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegreeAcronym() {
		return degreeAcronym;
	}

	public void setDegreeAcronym(String degreeAcronym) {
		this.degreeAcronym = degreeAcronym;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "AcademicDegree [degreeId=" + degreeId + ", degreeName=" + degreeName + ", institutionName="
				+ institutionName + ", degreeAcronym=" + degreeAcronym + ", specialization=" + specialization + "]";
	}

}
