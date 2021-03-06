package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class InternalResearchCollaborator {

	private ParticipationType participationType;
	private Professor professor;
	
	
	public InternalResearchCollaborator(ParticipationType participationType, Professor professor) throws NullException {
		if (participationType.equals(null) || professor.equals(null)) 
			throw new NullException("participationType or professor is NULL");
		
		this.participationType = participationType;
		this.professor = professor;
	}
	
	public ParticipationType getParticipationType() {
		return participationType;
	}
	public void setParticipationType(ParticipationType participationType) throws NullException {
		if (participationType.equals(null)) 
			throw new NullException("participationType is NULL");
		this.participationType = participationType;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) throws NullException {
		if (professor.equals(null)) 
			throw new NullException("participationType or professor is NULL");
		this.professor = professor;
	}
	
	
}
