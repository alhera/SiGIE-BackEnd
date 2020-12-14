package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class Organization {
	private int organizationId;
	private String organizationName;
	private String organizationDepartment;

	public Organization() {

	}

	public Organization(int organizationId, String organizationName, String organizationDepartment) {
		setOrganizationId(organizationId);
		setOrganizationName(organizationName);
		setOrganizationDepartment(organizationDepartment);
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		if (organizationId <= 0)
			throw new NullException("The organization ID must be bigger than 0");
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		if (organizationName.equalsIgnoreCase("") || organizationName.equalsIgnoreCase(" ")
				|| organizationName.equals(null))
			throw new NullException("The organization name must be different than just empty or null strings");
		this.organizationName = organizationName;
	}

	public String getOrganizationDepartment() {
		return organizationDepartment;
	}

	public void setOrganizationDepartment(String organizationDepartment) {
		if (organizationDepartment.equalsIgnoreCase("") || organizationDepartment.equalsIgnoreCase(" ")
				|| organizationDepartment.equals(null))
			throw new NullException("The organization department must be different than just empty or null strings");
		this.organizationDepartment = organizationDepartment;
	}

}
