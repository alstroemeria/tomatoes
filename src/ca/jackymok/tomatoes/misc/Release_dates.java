package ca.jackymok.tomatoes.misc;

import java.io.Serializable;
import java.util.Date;

public class Release_dates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date theater;
   	private Date dvd;

 	public Date getTheater(){
		return this.theater;
	}
	public void setTheater(Date theater){
		this.theater = theater;
	}
 	public Date getDvd(){
		return this.dvd;
	}
	public void setDvd(Date dvd){
		this.dvd = dvd;
	}
}
