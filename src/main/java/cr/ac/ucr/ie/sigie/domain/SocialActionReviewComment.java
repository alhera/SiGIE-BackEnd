package cr.ac.ucr.ie.sigie.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocialActionReviewComment {
	
	private int reviewCommentID;
	
	private String reviewComment;
	
	private Date reviewCommentDate;
	
	public SocialActionReviewComment() {
		super();
	}
	
	public SocialActionReviewComment(int reviewCommentID, String reviewComment, Date reviewCommentDate) {
		super();
		this.reviewCommentID = reviewCommentID;
		this.reviewComment = reviewComment;
		this.reviewCommentDate = reviewCommentDate;
	}

	public int getReviewCommentID() {
		return reviewCommentID;
	}

	public void setReviewCommentID(int reviewCommentID) {
		this.reviewCommentID = reviewCommentID;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Date getReviewCommentDate() {
		return reviewCommentDate;
	}

	public void setReviewCommentDate(String reviewCommentDate) {
		try {
			this.reviewCommentDate = new SimpleDateFormat("yyyy-MM-dd").parse(reviewCommentDate);
		} catch (ParseException e) {
			this.reviewCommentDate = new Date(0,0,0);
		}
	}

	@Override
	public String toString() {
		return "\nSocialActionReviewComment [ \n"
				+ "  -  id = " + reviewCommentID + "\n"
				+ "  -  review comment = " + reviewComment + "\n"
				+ "  -  date = "+reviewCommentDate.toString()
				+ "]\n";
	}
	
	
	
	

}
