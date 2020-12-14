package cr.ac.ucr.ie.sigie.domain;


public class Emphasis {

	private String emphasisId;
	private String emphasisName;
	
	public Emphasis() {
		this.emphasisId = "";
		this.emphasisName = "";
	}
	
	public Emphasis(String emphasisId, String emphasisName) {
		this.emphasisId = emphasisId;
		this.emphasisName = emphasisName;
	}


	public String getEmphasisId() {
		return emphasisId;
	}


	public void setEmphasisId(String emphasisId) {
		this.emphasisId = emphasisId;
	}


	public String getEmphasisName() {
		return emphasisName;
	}


	public void setEmphasisName(String emphasisName) {
		this.emphasisName = emphasisName;
	}
	
	
}
