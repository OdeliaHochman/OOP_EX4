package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataBase {
	public static void main(String[] args)
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; 
		String jdbcUser="student";
		String jdbcPassword="student";
		double sum=0;
		double bestScore=0;
		int numOfPlayer=0;
		double average=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			
			Statement statement = connection.createStatement();
			
			//select data
			String allCustomersQuery = "SELECT * FROM logs WHERE FirstID =18 and SecondID = 73";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\t\tPoint\t\tSomeDouble");
			while(resultSet.next())
			{
				//System.out.println(resultSet.getInt("FirstID")+"\t\t" +
						//resultSet.getInt("SecondID")+"\t\t" +
						//resultSet.getInt("ThirdID")+"\t\t" +
						//resultSet.getTimestamp("LogTime") +"\t\t\t\t" +
						sum+=resultSet.getDouble("Point");
						numOfPlayer++;
						//resultSet.getDouble("SomeDouble"));
			}
			average=sum/numOfPlayer;
			resultSet.close();		
			statement.close();		
			connection.close();		
		}
		
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("The average score:"+average+" ,our best score:"+bestScore);
	}
	
}
