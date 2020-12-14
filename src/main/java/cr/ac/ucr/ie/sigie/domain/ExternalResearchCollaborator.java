package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class ExternalResearchCollaborator {
	private ExternalParticipant externalParticipant;
	private ParticipationType participationtype;
	
	public ExternalResearchCollaborator() {}
	public ExternalResearchCollaborator(ExternalParticipant externalParticipant, ParticipationType participationtype) {
		setExternalParticipant(externalParticipant);
		setParticipationtype(participationtype);
	}
	public ExternalParticipant getExternalParticipant() {
		return externalParticipant;
	}
	public void setExternalParticipant(ExternalParticipant externalParticipant) {
		if (externalParticipant.equals(null))
			throw new NullException("The external participant must be different than null objects");
		this.externalParticipant = externalParticipant;
	}
	public ParticipationType getParticipationtype() {
		return participationtype;
	}
	public void setParticipationtype(ParticipationType participationtype) {
		if (participationtype.equals(null))
			throw new NullException("The participation type must be different than null objects");
		this.participationtype = participationtype;
	}
	
}
