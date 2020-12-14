package cr.ac.ucr.ie.sigie.domain;

import java.util.Date;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class News {

	private int newsId;
	private String title;
	private String content;
	private Date publicationDate;
	private Date expirationDate;
	private String image;
	private boolean visible;
	private NewsCategory newsCategory;
	private UniversityBranch universityBranch;
	private String authorName;
	private String authorLastName;
	private String authorMail;
	private String authorInstitutionRole;

	public News(int newsId, String title, String content, Date publicationDate, Date expirationDate, String image,
			boolean visible, NewsCategory newsCategory, UniversityBranch universityBranch, String authorName,
			String authorLastName, String authorMail, String authorInstitutionRole) {
		setNewsId(newsId);
		setTitle(title);
		setContent(content);
		setPublicationDate(publicationDate);
		setExpirationDate(expirationDate);
		setImage(image);
		setVisible(visible);
		setNewsCategory(newsCategory);
		setUniversityBranch(universityBranch);
		setAuthorName(authorName);
		setAuthorLastName(authorLastName);
		setAuthorMail(authorMail);
		setAuthorInstitutionRole(authorInstitutionRole);
	}

	public News() {
		super();
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		if(newsId==0) {
			throw new NullException("Id incorrecto");
		}else
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title==null) {
			throw new NullException("Titulo incorrecto");
		}else
		this.title = title;
	}

	public String getContent() {
		
		return content;
	}

	public void setContent(String content) {
		if(content==null) {
			throw new NullException("Contenido incorrecto");
		}else
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		if(publicationDate==null) {
			throw new NullException("Fecha de publicación incorrecta");
		}else	
		this.publicationDate = publicationDate;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		if(expirationDate==null) {
			throw new NullException("Fecha de expiración incorrecta");
		}else	
		this.expirationDate = expirationDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		if(image==null) {
			throw new NullException("imagen incorrecta");
		}else		
		this.image = image;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public NewsCategory getNewsCategory() {

		return newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory) {
		if(newsCategory==null) {
			throw new NullException("Cada noticia necesita una categoria");
		}else	
		this.newsCategory = newsCategory;
	}

	public UniversityBranch getUniversityBranch() {
		return universityBranch;
	}

	public void setUniversityBranch(UniversityBranch universityBranch) {
		if(universityBranch==null) {
			throw new NullException("Cada noticia necesita un recinto");
		}else	
		this.universityBranch = universityBranch;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		if(authorName==null) {
			throw new NullException("Nombre del autor incorrecto");
		}else		
		this.authorName = authorName;
	}
	
	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		if(authorLastName==null) {
			throw new NullException("Apellido del autor incorrecto");
		}else		
		this.authorLastName = authorLastName;
	}
	
	public String getAuthorMail() {
		return authorMail;
	}

	public void setAuthorMail(String authorMail) {
		if(authorMail==null) {
			throw new NullException("Email incorrecto");
		}else	
		this.authorMail = authorMail;
	}

	public String getAuthorInstitutionRole() {
		return authorInstitutionRole;
	}

	public void setAuthorInstitutionRole(String authorInstitutionRole) {
		if(authorInstitutionRole==null) {
			throw new NullException("Rol incorrecto");
		}else	
		this.authorInstitutionRole = authorInstitutionRole;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + ", content=" + content + ", publicationDate="
				+ publicationDate + ", expirationDate=" + expirationDate + ", image=" + image + ", visible=" + visible
				+ ", newsCategory=" + newsCategory + ", universityBranch=" + universityBranch + ", authorName="
				+ authorName + ", authorLastName=" + authorLastName + ", authorMail=" + authorMail
				+ ", authorInstitutionRole=" + authorInstitutionRole + "]";
	}

}
