package cr.ac.ucr.ie.sigie.domain;

import cr.ac.ucr.ie.sigie.exceptions.NullException;

public class NewsCategory {
	
	private int newsCategoryId;
	private String newsCategoryName;
	
	public NewsCategory(int newsCategoryId, String newsCategoryName) {
		setNewsCategoryId(newsCategoryId);
		setNewsCategoryName(newsCategoryName);
	}

	public NewsCategory() {
		
	}

	public int getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(int newsCategoryId) {
		if(newsCategoryId==0) {
			throw new NullException("Id incorrecto");
		}else
		this.newsCategoryId = newsCategoryId;
	}

	public String getName() {
		return newsCategoryName;
	}

	public void setNewsCategoryName(String newsCategoryName) {
		if(newsCategoryName==null) {
			throw new NullException("Nombre incorrecto");
		}else
		this.newsCategoryName = newsCategoryName;
	}

	@Override
	public String toString() {
		return "NewsCategory [newsCategoryId=" + newsCategoryId + ", newsCategoryName=" + newsCategoryName + "]";
	}
	
	

}