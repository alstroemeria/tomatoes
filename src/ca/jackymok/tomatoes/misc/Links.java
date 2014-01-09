
package ca.jackymok.tomatoes.misc;

import java.io.Serializable;


public class Links implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alternate;
   	private String self;

 	public String getAlternate(){
		return this.alternate;
	}
	public void setAlternate(String alternate){
		this.alternate = alternate;
	}
 	public String getSelf(){
		return this.self;
	}
	public void setSelf(String self){
		this.self = self;
	}
}
