package MovieIMDB;

import java.util.ArrayList;


class Movie {

	private String movieName;
    private String releaseDate;
    private double productionCost;
    private int noOfScreensReleased;
    private String directedBy;
    private String producedBy;
    private boolean status1;
    
    
    
	public Movie(String movieName, String releaseDate, double productionCost, int noOfScreensReleased,
			String directedBy, String producedBy, boolean status1) {
		super();
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.productionCost = productionCost;
		this.noOfScreensReleased = noOfScreensReleased;
		this.directedBy = directedBy;
		this.producedBy = producedBy;
		this.status1 = status1;
	}

	public Movie(String movieName, String releaseDate, boolean status) {
		
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.status1 = status;
	}
	
	public Movie(String movieName,String directedBy, double productionCost) {
		
		this.movieName = movieName;
		this.productionCost = productionCost;
		this.directedBy = directedBy;
	}

	public Movie() {
		this.movieName = "Default Movie";
        this.releaseDate = "01-01-2023";
        this.productionCost = 50.0;
        this.noOfScreensReleased = 1000;
        this.directedBy = "Default Director";
        this.producedBy = "Default Producer";
        this.status1 = true;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getProductionCost() {
		return productionCost;
	}

	public void setProductionCost(double productionCost) {
		this.productionCost = productionCost;
	}

	public int getNoOfScreensReleased() {
		return noOfScreensReleased;
	}

	public void setNoOfScreensReleased(int noOfScreensReleased) {
		this.noOfScreensReleased = noOfScreensReleased;
	}

	public String getDirectedBy() {
		return directedBy;
	}

	public void setDirectedBy(String directedBy) {
		this.directedBy = directedBy;
	}

	public String getProducedBy() {
		return producedBy;
	}

	public void setProducedBy(String producedBy) {
		this.producedBy = producedBy;
	}

	public boolean getStatus() {
		return status1;
	}

	public void setStatus(boolean status) {
		this.status1 = status;
	}
    
    
	public static Movie getMovie(ArrayList<Movie> m,String releasedate)
	{
		for(Movie i : m)
		{
			if(i.getReleaseDate().equals(releasedate))
			{
				return i;
				
			}
		}
		return null;
	}
	
	public static ArrayList<Movie> getMovie(ArrayList<Movie> m,boolean status)
	{
		ArrayList<Movie> res = new ArrayList<>();
		for(Movie i : m)
		{
			if(i.getStatus()==status)
			{
				res.add(i);
			}
		}
		return res;
	}
	
	public static ArrayList<Movie> getMovie(ArrayList<Movie> m,double productioncost)
	{
		ArrayList<Movie> res = new ArrayList<>();
		for(Movie i : m)
		{
			if(i.getProductionCost()>productioncost)
			{
				res.add(i);
			}
			
		}
		return res;
	}
}
