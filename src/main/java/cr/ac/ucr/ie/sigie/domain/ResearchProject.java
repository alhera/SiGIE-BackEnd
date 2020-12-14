package cr.ac.ucr.ie.sigie.domain;

import java.util.Date;
import java.util.List;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class ResearchProject {
	private int researchProjectId;
	private String code;
	private String title;
	private String description;
	private boolean visible;
	private boolean active;
	private Date startDate;
	private Date endDate;
	
	private List<ExternalResearchCollaborator> externalCollaborators;
	private List<InternalResearchCollaborator> internalCollaborators;
	private UniversityBranch branch;
	
	
	
	public ResearchProject() {}
	public ResearchProject(int researchProjectId, String code, String title, String description, boolean visible,
			boolean active, Date startDate, Date endDate, List<ExternalResearchCollaborator> externalCollaborators,
			List<InternalResearchCollaborator> internalCollaborators, UniversityBranch branch) {
		setResearchProjectId(researchProjectId);
		setCode(code);
		setTitle(title);
		setDescription(description);
		setVisible(visible);
		setActive(active);
		setStartDate(startDate);
		setEndDate(endDate);
		setExternalCollaborators(externalCollaborators);
		setInternalCollaborators(internalCollaborators);
		setBranch(branch);
	}
	public int getResearchProjectId() {
		return researchProjectId;
	}
	public void setResearchProjectId(int researchProjectId) {
		if (researchProjectId <= 0)
			throw new NullException("The research project ID must be bigger than 0");
		this.researchProjectId = researchProjectId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if (code.equalsIgnoreCase("") || code.equalsIgnoreCase(" ")
				|| code.equals(null))
			throw new NullException("The code of the research project must be different than just empty or null strings");
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title.equalsIgnoreCase("") || title.equalsIgnoreCase(" ")
				|| title.equals(null))
			throw new NullException("The title of the research project must be different than just empty or null strings");
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if (description.equalsIgnoreCase("") || description.equalsIgnoreCase(" ")
				|| description.equals(null))
			throw new NullException("The description of the research project must be different than just empty or null strings");
		this.description = description;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		if (startDate.equals(null))
			throw new NullException("The start date of the research project must be different than just empty or null strings");
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		if (endDate.equals(null))
			throw new NullException("The end date of the research project must be different than just empty or null strings");
		this.endDate = endDate;
	}
	public List<ExternalResearchCollaborator> getExternalCollaborators() {
		return externalCollaborators;
	}
	public void setExternalCollaborators(List<ExternalResearchCollaborator> externalCollaborators) {
		if (externalCollaborators.equals(null))
			throw new NullException("The external collaborators of the research project must be different than just empty or null strings");
		this.externalCollaborators = externalCollaborators;
	}
	public List<InternalResearchCollaborator> getInternalCollaborators() {
		return internalCollaborators;
	}
	public void setInternalCollaborators(List<InternalResearchCollaborator> internalCollaborators) {
		if (internalCollaborators.equals(null))
			throw new NullException("The internal collaborators of the research project must be different than just empty or null strings");
		this.internalCollaborators = internalCollaborators;
	}
	public UniversityBranch getBranch() {
		return branch;
	}
	public void setBranch(UniversityBranch branch) {
		if (branch.equals(null))
			throw new NullException("The branch of the research project must be different than just empty or null strings");
		this.branch = branch;
	}
	
	
}
