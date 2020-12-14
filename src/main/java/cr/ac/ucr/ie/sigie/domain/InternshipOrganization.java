package cr.ac.ucr.ie.sigie.domain;

public class InternshipOrganization {
	private int organizationId;
	private String organizationName;
	private String address;
	private String phone;
	private String contactEmail;
	private String contactName;
	private String contactLastName;
	private String sector;
	private EconomySectorType economySectorType;
	private CapitalOwnershipType capitalOwnershipType;
	private District district;
	private Province province;

	public InternshipOrganization() {
		this.district = new District();
		this.province = new Province();
		this.economySectorType = new EconomySectorType();
		this.capitalOwnershipType = new CapitalOwnershipType();
	}

	public InternshipOrganization(int organizationId, String organizationName, String address, String phone, String contactEmail,
			String contactName, String contactLastName, String sector, EconomySectorType economySectorType,
			CapitalOwnershipType capitalOwnershipType, District district, Province province) {
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.address = address;
		this.phone = phone;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.contactLastName = contactLastName;
		this.sector = sector;
		this.economySectorType = economySectorType;
		this.capitalOwnershipType = capitalOwnershipType;
		this.district = district;
		this.province = province;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public EconomySectorType getEconomySectorType() {
		return economySectorType;
	}

	public void setEconomySectorType(EconomySectorType economySectorType) {
		this.economySectorType = economySectorType;
	}

	public CapitalOwnershipType getCapitalOwnershipType() {
		return capitalOwnershipType;
	}

	public void setCapitalOwnershipType(CapitalOwnershipType capitalOwnershipType) {
		this.capitalOwnershipType = capitalOwnershipType;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
