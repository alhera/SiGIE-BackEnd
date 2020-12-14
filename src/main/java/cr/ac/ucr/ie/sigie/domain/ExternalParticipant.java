package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class ExternalParticipant {
	private int externalParticipantId;
	private String name;
	private String lastName;
	private String academicDegree;
	private String email;
	
	private Organization organization;

	
	public ExternalParticipant() {}

	public ExternalParticipant(int externalParticipantId, String name, String lastName, String academicDegree,
			String email, Organization organization) {
		setExternalParticipantId(externalParticipantId);
		setName(name);
		setLastName(lastName);
		setAcademicDegree(academicDegree);
		setEmail(email);
		setOrganization(organization);
	}

	public int getExternalParticipantId() {
		return externalParticipantId;
	}

	public void setExternalParticipantId(int externalParticipantId) {
		if (externalParticipantId <= 0)
			throw new NullException("The external participant ID must be bigger than 0");
		this.externalParticipantId = externalParticipantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.equalsIgnoreCase("") || name.equalsIgnoreCase(" ")
				|| name.equals(null))
			throw new NullException("The external participant name must be different than just empty or null strings");
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(" ")
				|| lastName.equals(null))
			throw new NullException("The external participant last name must be different than just empty or null strings");
		this.lastName = lastName;
	}

	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		if (academicDegree.equalsIgnoreCase("") || academicDegree.equalsIgnoreCase(" ")
				|| academicDegree.equals(null))
			throw new NullException("The external participant academic degree must be different than just empty or null strings");
		this.academicDegree = academicDegree;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email.equalsIgnoreCase("") || email.equalsIgnoreCase(" ")
				|| email.equals(null))
			throw new NullException("The external participant email must be different than just empty or null strings");
		this.email = email;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		if (organization.equals(null))
			throw new NullException("The external participant organization must be different than null objects");
		this.organization = organization;
	}
	
	
}
