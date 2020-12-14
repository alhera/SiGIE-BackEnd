package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class ParticipationType {
	
	private int idParticipation;
	private String description;
	
	
	
	public ParticipationType() {
		super();
	}

	public ParticipationType(int idParticipation, String description) throws NullException {
		
		if (idParticipation <= 0 || description.trim().isEmpty()) 
			throw new NullException("idParticipation or description is NULL");
		
		this.idParticipation = idParticipation;
		this.description = description;
	}
	
	public int getIdParticipation() {
		return idParticipation;
	}
	public void setIdParticipation(int idParticipation) throws NullException {
		if (idParticipation <= 0) 
			throw new NullException("idParticipation is NULL");
		this.idParticipation = idParticipation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) throws NullException {
		if (description.isEmpty()) 
			throw new NullException("idParticipation or description is NULL");
		this.description = description;
	}

}
