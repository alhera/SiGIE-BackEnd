package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class ExternalSocialActionCollaborator {
	
	private ExternalParticipant externalParticipant;
	
	private ParticipationType participationType;
	
	public ExternalSocialActionCollaborator() {}
	
	public ExternalSocialActionCollaborator(ExternalParticipant externalParticipant, ParticipationType participationtype) {
		
		setExternalParticipant(externalParticipant);
		
		setParticipationtype(participationtype);
		
	}
	
	public ExternalParticipant getExternalParticipant() {
		
		return externalParticipant;
		
	}
	
	public void setExternalParticipant(ExternalParticipant externalParticipant) {
		
		if (externalParticipant.equals(null)) {
			throw new NullException("The external participant must be different than null objects");
		}
			
		this.externalParticipant = externalParticipant;
	}
	
	public ParticipationType getParticipationType() {
		
		return participationType;
		
	}
	public void setParticipationtype(ParticipationType participationType) {
		
		if (participationType.equals(null)) {
			throw new NullException("The participation type must be different than null objects");
		}
		
		this.participationType = participationType;
	}

	@Override
	public String toString() {
		return "ExternalSocialActionCollaborator ["
				+ "\nExternalParticipant=" + externalParticipant.toString()
				+ "\nParticipationType="+ participationType.toString()
				+ "\n]";
	}
	
	
	

}
