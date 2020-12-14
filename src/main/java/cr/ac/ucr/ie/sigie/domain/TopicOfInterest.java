package cr.ac.ucr.ie.sigie.domain;

public class TopicOfInterest {
	private int topicId;
	private String topicDescription;

	
	public TopicOfInterest() {
		super();
	}

	public TopicOfInterest(int topicId, String topicDescription) {
		super();
		this.topicId = topicId;
		this.topicDescription = topicDescription;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	@Override
	public String toString() {
		return "TopicOfInterest [topicId=" + topicId + ", topicDescription=" + topicDescription + "]";
	}

}
