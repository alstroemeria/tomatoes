package ca.jackymok.tomatoes.misc;

import java.io.Serializable;

public class Release_dates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theater;
   	private String dvd;

 	public String getTheater(){
		return this.theater;
	}
	public void setTheater(String theater){
		this.theater = theater;
	}
 	public String getDvd(){
		return this.dvd;
	}
	public void setDvd(String dvd){
		this.dvd = dvd;
	}
}
