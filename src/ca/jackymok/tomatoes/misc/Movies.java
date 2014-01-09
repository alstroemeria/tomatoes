
package ca.jackymok.tomatoes.misc;

import java.io.Serializable;
import java.util.List;


public class Movies implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String link_template;
   	private Links links;
   	private List<Movie> movies;

 	public String getLink_template(){
		return this.link_template;
	}
	public void setLink_template(String link_template){
		this.link_template = link_template;
	}
 	public Links getLinks(){
		return this.links;
	}
	public void setLinks(Links links){
		this.links = links;
	}
 	public List<Movie> getMovies(){
		return this.movies;
	}
	public void setMovies(List<Movie> movies){
		this.movies = movies;
	}
}
