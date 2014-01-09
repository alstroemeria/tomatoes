package ca.jackymok.tomatoes.misc;

import java.io.Serializable;

public class Posters implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String thumbnail;
   	private String profile;
   	private String detailed;
   	private String original;
   	
   	public String getThumbnail(){
		return this.thumbnail;
	}
   	public void setThumbnail(String thumbnail){
   		this.thumbnail = thumbnail;
	}
	public String getProfile(){
		return this.profile;
	}
   	public void setProfile(String profile){
   		this.profile = profile;
	}
	public String getDetailed(){
		return this.detailed;
	}
   	public void setDetailed(String detailed){
   		this.detailed = detailed;
	}
	public String getOriginal(){
		return this.original;
	}
   	public void setOriginal(String original){
   		this.original = original;
	}
}
