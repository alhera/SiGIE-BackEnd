package cr.ac.ucr.ie.sigie.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SocialActionProject {
	
	private int code;
	
	private String title;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private boolean active;
	
	private boolean visible;
	
	private String publicationStatus;
	
	private ArrayList<InternalSocialActionCollaborator> internalCollaborators;
	
	private ArrayList<ExternalSocialActionCollaborator> externalCollaborators;
	
	private ArrayList<SocialActionReviewComment> listReviewComments;
	
	private UniversityBranch branch;
	
	public static String APPROVED = "Approved";
	
	public static String NEED_TO_FIX = "NeedToFix";
	
	public static String ON_REVIEW = "OnReview";
	
	public SocialActionProject() {
		this.listReviewComments = new ArrayList<>();
		this.publicationStatus = ON_REVIEW;
	}

	public SocialActionProject(int code, String title, String description, Date startDate, Date endDate,
			boolean active, boolean visible, ArrayList<InternalSocialActionCollaborator> internalCollaborators,
			ArrayList<ExternalSocialActionCollaborator> externalCollaborators, UniversityBranch branch) {
		this.code = code;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
		this.visible = visible;
		this.internalCollaborators = internalCollaborators;
		this.externalCollaborators = externalCollaborators;
		this.branch = branch;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		try {
			this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		} catch (ParseException e) {
			this.startDate = new Date(0, 0, 0);
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		try {
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			this.endDate = new Date(0, 0, 0);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public ArrayList<InternalSocialActionCollaborator> getInternalCollaborators() {
		return internalCollaborators;
	}

	public void setInternalCollaborators(ArrayList<InternalSocialActionCollaborator> internalCollaborators) {
		this.internalCollaborators = internalCollaborators;
	}

	public ArrayList<ExternalSocialActionCollaborator> getExternalCollaborators() {
		return externalCollaborators;
	}

	public void setExternalCollaborators(ArrayList<ExternalSocialActionCollaborator> externalCollaborators) {
		this.externalCollaborators = externalCollaborators;
	}

	public UniversityBranch getBranch() {
		return branch;
	}

	public void setBranch(UniversityBranch branch) {
		this.branch = branch;
	}

	
	public ArrayList<SocialActionReviewComment> getListReviewComments() {
		return listReviewComments;
	}

	public void setListReviewComments(ArrayList<SocialActionReviewComment> listReviewComments) {
		this.listReviewComments = listReviewComments;
	}

	
	public String getPublicationStatus() {
		return publicationStatus;
	}

	public void setPublicationStatus(String publicationStatus) {
		this.publicationStatus = publicationStatus;
	}

	@Override
	public String toString() {
		return "SocialActionProject [" +"\n"
				+ "  -  code = " + code + "\n"
				+ "  -  title = " + title + "\n"
				+ "  -  description = " + description + "\n"
				+ "  -  startDate = " + startDate  + "\n"
				+ "  -  endDate = " + endDate + "\n"
				+ "  -  active = " + active + "\n"
				+ "  -  visible = " + visible + "\n"
				+ "  -  publication status = " + publicationStatus + "\n"
				+ "  -  internalCollaborators = " + internalCollaborators.toString() +"\n"
				+ "  -  externalCollaborator = " + externalCollaborators.toString() +"\n"
				+ "  -  university branch = "+ branch.toString() +"\n"
				+ "  -  review comments = "+ listReviewComments.toString() +"\n"
				+ "]";
	}
	
	
	
	

}
