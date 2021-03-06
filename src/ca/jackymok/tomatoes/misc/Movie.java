
package ca.jackymok.tomatoes.misc;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Abridged_cast> abridged_cast;
   	private Alternate_ids alternate_ids;
   	private String critics_consensus;
   	private String id;
   	private Links links;
   	private String mpaa_rating;
   	private Posters posters;
   	private Ratings ratings;
   	private Release_dates release_dates;
   	private String runtime;
   	private String synopsis;
   	private String title;
   	private String year;

 	public List<Abridged_cast> getAbridged_cast(){
		return this.abridged_cast;
	}
	public void setAbridged_cast(List<Abridged_cast> abridged_cast){
		this.abridged_cast = abridged_cast;
	}
 	public Alternate_ids getAlternate_ids(){
		return this.alternate_ids;
	}
	public void setAlternate_ids(Alternate_ids alternate_ids){
		this.alternate_ids = alternate_ids;
	}
 	public String getCritics_consensus(){
		return this.critics_consensus;
	}
	public void setCritics_consensus(String critics_consensus){
		this.critics_consensus = critics_consensus;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Links getLinks(){
		return this.links;
	}
	public void setLinks(Links links){
		this.links = links;
	}
 	public String getMpaa_rating(){
		return this.mpaa_rating;
	}
	public void setMpaa_rating(String mpaa_rating){
		this.mpaa_rating = mpaa_rating;
	}
 	public Posters getPosters(){
		return this.posters;
	}
	public void setPosters(Posters posters){
		this.posters = posters;
	}
 	public Ratings getRatings(){
		return this.ratings;
	}
	public void setRatings(Ratings ratings){
		this.ratings = ratings;
	}
 	public Release_dates getRelease_dates(){
		return this.release_dates;
	}
	public void setRelease_dates(Release_dates release_dates){
		this.release_dates = release_dates;
	}
 	public String getRuntime(){
		return this.runtime;
	}
	public void setRuntime(String runtime){
		this.runtime = runtime;
	}
 	public String getSynopsis(){
		return this.synopsis;
	}
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getYear(){
		return this.year;
	}
	public void setYear(String year){
		this.year = year;
	}
}
