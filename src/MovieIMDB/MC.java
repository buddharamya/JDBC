package MovieIMDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import SQL.*;

@SuppressWarnings("serial")
class ShorterMovieNameException extends Exception {
    
	public ShorterMovieNameException(String s)
    {
    	super(s);
    }
    public ShorterMovieNameException()
    {
    	super();
    }
}


//Unchecked User Exception


@SuppressWarnings("serial")
class LesserProductionCostException extends RuntimeException
{
	public LesserProductionCostException(String s)
	{
		super(s);
	}
	public LesserProductionCostException()
	{
		super();
	}
	
}

public class MC 
{
	private static final String URL="jdbc:mysql://localhost:3306/IMDB";
	private static final String Username="root";
    private static final String Password="Ramya29@2000";
	
	 public static void main(String[] args) {
	        MC movieControl = new MC();
	        movieControl.insertMovie();
	        
	    }


	    public void insertMovie() {
	    	try
			{
				 Scanner sc=new Scanner(System.in);
				// Accept input from the user
		         System.out.print("Enter movie name: ");
		         String movieName = sc.nextLine();
		         
		         //check length of movie name
		         
		         if(movieName.length()<3)
		         {
		        	 throw new ShorterMovieNameException();
		         }
		         
		         System.out.print("Enter release date: ");
		         String releaseDate = sc.nextLine();
		         
		         System.out.print("Enter production cost (in Cr): ");
		         double productionCost = sc.nextDouble();

		         // Check productionCost
		         if (productionCost < 10) 
		         {
		           throw new LesserProductionCostException();
		         }
		         
		         System.out.print("Enter number of screens released: ");
		         int noOfScreensReleased = sc.nextInt();
		         sc.nextLine(); // Consume the newline character

		         System.out.print("Enter director's name: ");
		         String directedBy = sc.nextLine();

		         System.out.print("Enter producer's name: ");
		         String producedBy = sc.nextLine();

		         System.out.print("Enter status: ");
		         Boolean status1 = sc.nextBoolean();	
		         
		         // Create a Movie object
		         Movie movie = new Movie(movieName, releaseDate, productionCost, noOfScreensReleased, directedBy, producedBy, status1);

		         // Insert the movie data into the Movies table
		         
		         
		 	     
		 	    try (Connection connection = DriverManager.getConnection(URL, Username, Password)) 
		 	    {
	                String sql = "INSERT INTO Movies (movieName, releaseDate, productionCost, noOfScreensReleased, directedBy, producedBy, status) " +
	                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement statement = connection.prepareStatement(sql);
	                statement.setString(1, movie.getMovieName());
	                statement.setString(2, movie.getReleaseDate());
	                statement.setDouble(3, movie.getProductionCost());
	                statement.setInt(4, movie.getNoOfScreensReleased());
	                statement.setString(5, movie.getDirectedBy());
	                statement.setString(6, movie.getProducedBy());
	                statement.setBoolean(7, movie.getStatus());
	                
	                int rowsAffected = statement.executeUpdate();
	                if (rowsAffected > 0) {
	                    System.out.println("Movie data inserted successfully.");
	                } else {
	                    System.out.println("Failed to insert movie data.");
	                }

	                statement.close();
			     } 
		 	    
		 	   catch (SQLException e) {
	               e.printStackTrace();
	           }

	           sc.close();
	       } 
			
			
			catch (ShorterMovieNameException e) 
			{
	            System.out.println("Error: Movie name should be a minimum of 3 characters.");
	            insertMovie(); // Ask the user to reenter the movie name
	        } catch (LesserProductionCostException e) 
			{
	            System.out.println("Error: Production cost should be more than 10 Cr.");
	            insertMovie(); // Ask the user to reenter the production cost
	        }
			
		}
	    
}
