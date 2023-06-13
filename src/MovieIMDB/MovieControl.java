package MovieIMDB;

import java.sql.*;

import java.util.*;

import SQL.Movie;

class ShorterMovieNameException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ShorterMovieNameException(String s)
    {
    	super(s);
    }
    public ShorterMovieNameException()
    {
    	super();
    }
}

@SuppressWarnings("serial")
//Unchecked User Exception


class LesserProductionCostException extends RuntimeException
{
	public LesserProductionCostException(String s)
	{
		super(s);
	}
	
	
}
public class MovieControl {
	private static final String URL="jdbc:mysql://localhost:3306/IMDB";
	private static final String USERNAME="root";
    private static final String PASSWORD="Ramya29@2000";
    
    public static void main(String[] args)
    {
        insertMovie();
       /* getAllMovies();
        getMovie("Interstellar");
        getMovie(200);
        getMovie("2022-01-01");*/
        deleteMovie("The Dark Knight");
    }
/*
    public static void getAllMovies() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Movies";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Movie> movies = new ArrayList<>();

            while (resultSet.next()) {
                String movieName = resultSet.getString("movieName");
                String releaseDate = resultSet.getString("releaseDate");
                double productionCost = resultSet.getDouble("productionCost");
                int noOfScreensReleased = resultSet.getInt("noOfScreensReleased");
                String directedBy = resultSet.getString("directedBy");
                String producedBy = resultSet.getString("producedBy");
                boolean status = resultSet.getBoolean("status");

                Movie movie = new Movie(movieName, releaseDate, status);
                movie.setProductionCost(productionCost);
                movie.setNoOfScreensReleased(noOfScreensReleased);
                movie.setDirectedBy(directedBy);
                movie.setProducedBy(producedBy);

                movies.add(movie);
            }

            for (Movie movie : movies) {
                System.out.println(movie.getMovieName() + " | " + movie.getReleaseDate() + " | " +
                        movie.getProductionCost() + " | " + movie.getNoOfScreensReleased() + " | " +
                        movie.getDirectedBy() + " | " + movie.getProducedBy() + " | " + movie.getStatus());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMovie(String movieName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Movies WHERE movieName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String releaseDate = resultSet.getString("releaseDate");
                double productionCost = resultSet.getDouble("productionCost");
                int noOfScreensReleased = resultSet.getInt("noOfScreensReleased");
                String directedBy = resultSet.getString("directedBy");
                String producedBy = resultSet.getString("producedBy");
                boolean status = resultSet.getBoolean("status");

                Movie movie = new Movie(movieName, releaseDate, status);
                movie.setProductionCost(productionCost);
                movie.setNoOfScreensReleased(noOfScreensReleased);
                movie.setDirectedBy(directedBy);
                movie.setProducedBy(producedBy);

                System.out.println(movie.getMovieName() + " | " + movie.getReleaseDate() + " | " +
                        movie.getProductionCost() + " | " + movie.getNoOfScreensReleased() + " | " +
                        movie.getDirectedBy() + " | " + movie.getProducedBy() + " | " + movie.getStatus());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMovie(double productionCost) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Movies WHERE productionCost > ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, productionCost);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String movieName = resultSet.getString("movieName");
                String releaseDate = resultSet.getString("releaseDate");
                int noOfScreensReleased = resultSet.getInt("noOfScreensReleased");
                String directedBy = resultSet.getString("directedBy");
                String producedBy = resultSet.getString("producedBy");
                boolean status = resultSet.getBoolean("status");

                Movie movie = new Movie(movieName, releaseDate, status);
                movie.setProductionCost(productionCost);
                movie.setNoOfScreensReleased(noOfScreensReleased);
                movie.setDirectedBy(directedBy);
                movie.setProducedBy(producedBy);

                System.out.println(movie.getMovieName() + " | " + movie.getReleaseDate() + " | " +
                        movie.getProductionCost() + " | " + movie.getNoOfScreensReleased() + " | " +
                        movie.getDirectedBy() + " | " + movie.getProducedBy() + " | " + movie.getStatus());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void getMovie(String releaseDate) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Movies WHERE releaseDate = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, releaseDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String movieName = resultSet.getString("movieName");
                double productionCost = resultSet.getDouble("productionCost");
                int noOfScreensReleased = resultSet.getInt("noOfScreensReleased");
                String directedBy = resultSet.getString("directedBy");
                String producedBy = resultSet.getString("producedBy");
                boolean status = resultSet.getBoolean("status");

                Movie movie = new Movie(movieName, releaseDate, status);
                movie.setProductionCost(productionCost);
                movie.setNoOfScreensReleased(noOfScreensReleased);
                movie.setDirectedBy(directedBy);
                movie.setProducedBy(producedBy);

                System.out.println(movie.getMovieName() + " | " + movie.getReleaseDate() + " | " +
                        movie.getProductionCost() + " | " + movie.getNoOfScreensReleased() + " | " +
                        movie.getDirectedBy() + " | " + movie.getProducedBy() + " | " + movie.getStatus());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    */

    @SuppressWarnings("resource")
	public static void insertMovie(){
        try  {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter movie name: ");
            String movieName = scanner.nextLine();

            if (movieName.length() < 3) {
                throw new ShorterMovieNameException("Movie name must be a minimum of 3 characters.");
            }

            System.out.print("Enter release date (YYYY-MM-DD): ");
            String releaseDate = scanner.nextLine();

            System.out.print("Enter production cost (in crores): ");
            double productionCost = Double.parseDouble(scanner.nextLine());

            if (productionCost <= 10) {
                throw new LesserProductionCostException("Production cost must be more than 10 crores.");
            }

            System.out.print("Enter number of screens released: ");
            int noOfScreensReleased = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter directed by: ");
            String directedBy = scanner.nextLine();

            System.out.print("Enter produced by: ");
            String producedBy = scanner.nextLine();

            System.out.print("Enter status (true/false): ");
            boolean status = Boolean.parseBoolean(scanner.nextLine());
            
	        Movie movie = new Movie(movieName, releaseDate, productionCost, noOfScreensReleased, directedBy, producedBy, status);

            
            try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))
            {
            String sql = "INSERT INTO Movies (movieName, releaseDate, productionCost, noOfScreensReleased, directedBy, producedBy, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieName);
            statement.setString(2, releaseDate);
            statement.setDouble(3, productionCost);
            statement.setInt(4, noOfScreensReleased);
            statement.setString(5, directedBy);
            statement.setString(6, producedBy);
            statement.setBoolean(7, status);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie inserted successfully.");
            }

            statement.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void deleteMovie(String movieName) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "DELETE FROM Movies WHERE movieName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieName);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Movie deleted successfully.");
            } else {
                System.out.println("Movie not found.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
