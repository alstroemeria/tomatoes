
package ca.jackymok.tomatoes.misc;

import java.io.Serializable;
import java.util.List;

public class Abridged_cast implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List characters;
   	private String id;
   	private String name;

 	public List getCharacters(){
		return this.characters;
	}
	public void setCharacters(List characters){
		this.characters = characters;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
