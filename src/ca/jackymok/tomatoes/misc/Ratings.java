package ca.jackymok.tomatoes.misc;

import java.io.Serializable;

public class Ratings implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String critics_rating;
   	private String critics_score;
   	private String audience_rating;
   	private String audience_score;
   	
 	public String getCritics_rating(){
		return this.critics_rating;
	}
	public void setCritics_rating(String critics_rating){
		this.critics_rating = critics_rating;
	}
 	public String getCritics_score(){
		return this.critics_score;
	}
	public void setCritics_score(String critics_score){
		this.critics_score = critics_score;
	}
 	public String getAudience_rating(){
		return this.audience_rating;
	}
	public void setAudience_rating(String audience_rating){
		this.audience_rating = audience_rating;
	}
 	public String getAudience_score(){
		return this.audience_score;
	}
	public void setAudience_score (String audience_score){
		this.audience_score = audience_score;
	}
}
