package cr.ac.ucr.ie.sigie.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.SocialActionReviewComment;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@Repository
public class SocialActionReviewCommentData {
	
	private static DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public boolean addReviewComment(SocialActionReviewComment socialActionReviewComment, int projecCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		String reviewComment = socialActionReviewComment.getReviewComment();
		
		String date = dateToString(socialActionReviewComment.getReviewCommentDate());
		
		String query = "INSERT INTO social_action_review_comment (social_action_code, review_comment, review_comment_date)\r\n" + 
				"VALUES ("+projecCode+", '"+reviewComment+"', '"+date+"');";
		
		
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				wasSuccessfulProcess = true;
			}
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}

	public ArrayList<SocialActionReviewComment> getAllReviewCommentByCodeProject(int code) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from social_action_review_comment where social_action_code = "+code+";";
		
		ArrayList<SocialActionReviewComment> listReviewComments = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			SocialActionReviewComment socialActionReviewComment;
			
			while(rs.next()) {
				
				int reviewCommentID = rs.getInt("review_comment_id");
				String reviewComment = rs.getString("review_comment");
				String reviewCommentDate = rs.getString("review_comment_date");
				
				socialActionReviewComment = new SocialActionReviewComment();
				
				socialActionReviewComment.setReviewComment(reviewComment);
				socialActionReviewComment.setReviewCommentDate(reviewCommentDate);
				socialActionReviewComment.setReviewCommentID(reviewCommentID);
				
				listReviewComments.add(socialActionReviewComment);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listReviewComments;
		
	}

	//----------------------------------------------UTIL METHODS-----------------------------------------------------------//
	
	private String dateToString(Date date) {
		
		String pattern = "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(pattern);

		String dateAsString = df.format(date);

		return dateAsString;
		
	}

	public static boolean deleteByCodeProject(int code) throws SQLException {
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulConnection = false;

		String query = "delete from social_action_review_comment where social_action_code ="+code+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				
				wasSuccessfulConnection = true;
				
			}
			
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulConnection;
		
	}
	
}
