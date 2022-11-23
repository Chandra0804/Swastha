package SWASTHA.My_Work;


import java.io.*;
import java.sql.*;


public class Hospital_CSV_To_Database {

	public static void main(String[] args) {
		try
		{

		String csvFilePath = "E:\\IIIT SRI CITY\\SEMESTER - 3\\OOP\\src\\SWASTHA\\My_Work\\EXCEL_CSV_DATA\\CSV_Data\\Hospital_CSV.csv";


		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root", "1234");
		connection.setAutoCommit(false);


		Statement stmt = connection.createStatement();
		stmt.execute("drop table if exists Hospital");
		stmt.execute("create table if not exists Hospital(hospital_name varchar(50), hospital_ID varchar(50), ph_no varchar(15))");
		stmt.execute("truncate table Hospital");


		PreparedStatement statement = connection.prepareStatement("insert into Hospital values (?, ?, ?)");
		BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));


		String lineText = null;
		int count = 0, batchSize = 100;
		lineReader.readLine(); 



		while ((lineText = lineReader.readLine()) != null) 
		{

		String[] data = lineText.split(",");

		String name   =  data[0];
		String ID     =  data[1];
		String ph_no  =  data[2];

		statement.setString(1, name);
		statement.setString(2, ID);
		statement.setString(3, ph_no);


		statement.addBatch();
		 
		if (count % batchSize == 0)
			statement.executeBatch();


		}

		 
		lineReader.close();

		statement.executeBatch();
		connection.commit();
		connection.close();

		System.out.print("\nInsertion of Hospital Data into Database Server is Successfully Implemented\n");

		}



		catch(Exception e)
		{
		System.out.println("\nAn Exception has Occurred\n");
		System.err.println(e);
		}


	}

}
