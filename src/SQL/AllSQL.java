package SQL;
import java.sql.*;
import static java.sql.DriverManager.getConnection;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class AllSQL {
	
	Connection ConnTwo;
	Statement stat;
	ResultSet rs;
	Properties PropOne;

	public AllSQL()
	{
		try
		{
			// STEP 1 LOCATE AND LOAD THE JDBC DRIVER
			
			new com.mysql.cj.jdbc.Driver();
			
			System.out.println("Driver Loaded.....");
			
			
			
			// STEP 2 CONNECT TO THE DATABASE 
			// getConnection("URL of the database(BackendDB software://Name or IP address of the machine where MYSQL db is installed: PortNo/name of the database)","Username","password");
			
			ConnTwo = getConnection("jdbc:mysql://localhost:3306/JDBCDB","root","Ramya29@2000");
			
			System.out.println("Connected to the DataBase for Mysql");
			    
			
			// No auto commit only manual commits
			
			ConnTwo.setAutoCommit(false); 
			
			
			// Creates a statement object
			stat=ConnTwo.createStatement();
			
			
			// Execution of Queries 

			rs=stat.executeQuery("SELECT teamid,teamname,baselocation,turnover from Teams");
			
			//results of queries stored in result set rs (2D array)
			
			// It is a linked list of records. Iterates from 1st to last
			
			while (rs.next ())
			{
			System.out.print (rs.getString ("teamid") + " ");
			System.out.print (rs.getString ("teamname") + " ");
			System.out.println (rs.getString ("baselocation")+" ");
			System.out.println (rs.getDouble ("turnover")+" ");

			}/* Loop terminates when there are no more records in the resultset
			In every cycle of the loop. we are retrieving one record.*/
			/*
			int status=stat.executeUpdate("update Teams set teamname= upper(teamname)"+"where teamid='csk'");
		
			stat.executeUpdate();
			ConnTwo.commit();
			System.out.println(((status>0)? status+"records updated" : "No records updated"));*/
			
			PreparedStatement prepared = ConnTwo.prepareStatement("update Teams set teamname=? where teamid=?");
			
			//int status = prepared.executeUpdate("update Teams set teamname=? where teamid=?");
			
			
			
			Scanner sc=new Scanner(System.in);
			System.out.print("ENter team id : ");
			String team_id=sc.nextLine();
			
			System.out.print("Enter team name");
			String team_name=sc.nextLine();
			
			prepared.setString(1,team_name);
			prepared.setString(2,team_id);
			
			boolean b=prepared.execute();
			
			prepared.executeUpdate("commit");
			
			System.out.println(((b==true)? "records updated" : "No records updated"));
			
			ConnTwo.close ();
			
			
		} 
		catch (SQLException sqle)
		{
			System.out.println (sqle);
			
		} 
		
	}
	public static void main (String args[])
	{
		new AllSQL ();
	}


}
