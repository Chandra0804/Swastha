package SWASTHA.My_Work;

import java.io.*;
import java.sql.*;


public class Appointment_CSV_To_Database {

	public static void main(String[] args) {
		try
		{

		String csvFilePath = "E:\\IIIT SRI CITY\\SEMESTER - 3\\OOP\\src\\SWASTHA\\My_Work\\EXCEL_CSV_DATA\\CSV_Data\\Appointment_CSV.csv";


		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root", "1234");
		connection.setAutoCommit(false);



		Statement stmt = connection.createStatement();
		stmt.execute("drop table if exists Appointment");
		stmt.execute("create table if not exists appointment(appointmentID varchar(50),time varchar(50),date varchar(50),doctorEmail varchar(50) , patientEmail varchar(50))");
		stmt.execute("truncate table Appointment");   



		PreparedStatement statement = connection.prepareStatement("insert into Appointment values (?, ?, ?, ?, ?)");
		BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));


		String lineText = null;
		int count = 0, batchSize = 100;
		lineReader.readLine(); 


		while ((lineText = lineReader.readLine()) != null) 
		{

		String[] data = lineText.split(",");

		String ID        =  data[0];
		String time      =  data[1];
		String date    =  data[2];
		String doctor_email      =  data[3];
		String patient_email  =  data[4];


		statement.setString(1, ID);
		statement.setString(2, time);
		statement.setString(3, date);
		statement.setString(4, doctor_email);
		statement.setString(5, patient_email);

		statement.addBatch();
		 
		if (count % batchSize == 0)
			statement.executeBatch();


		}


		 
		lineReader.close();

		statement.executeBatch();
		connection.commit();
		connection.close();

		System.out.print("\nInsertion of Appointment Data into Database Server is Successfully Implemented\n");



		}



		catch(Exception e)
		{
		System.out.println("\nAn Exception has Occurred\n");
		System.err.println(e);
		}

	}

}
