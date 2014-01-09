
package ca.jackymok.tomatoes.misc;

import java.io.Serializable;


public class Alternate_ids implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imdb;

 	public String getImdb(){
		return this.imdb;
	}
	public void setImdb(String imdb){
		this.imdb = imdb;
	}
}
