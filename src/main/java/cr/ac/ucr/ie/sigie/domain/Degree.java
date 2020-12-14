package cr.ac.ucr.ie.sigie.domain;

public class Degree {

	private int degreeCode;
	private String degreeName;

	public Degree() {
		this.degreeCode = 0;
		this.degreeName = "";
	}

	public Degree(int degreeCode, String degreeName) {
		this.degreeCode = degreeCode;
		this.degreeName = degreeName;
	}

	public int getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(int degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

}
