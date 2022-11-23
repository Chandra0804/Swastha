package SWASTHA.My_Work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Doctor_Edit_To_CSV_Database {

	public static void main(String[] args) {
		Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        Scanner input = new Scanner(System.in);
        
        
        try
        {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root", "1234");
		
        ps = con.prepareStatement("select * from Doctor where email = ?");
		
        System.out.print("\nEnter the Email of the Doctor  :  ");
		String email = input.nextLine();
		
        ps.setString(1, email);
        
        rs1 = ps.executeQuery();
        
        if (rs1.isBeforeFirst()) {

            stmt = con.prepareStatement("select * from Doctor where email = ? and password = ?");
            stmt.setString(1, email);
            
            System.out.print("\nEnter the Password of the Doctor  :  ");
    		String password = input.nextLine();
    		
            stmt.setString(2, password);
            rs2 = stmt.executeQuery();
            
            if (rs2.isBeforeFirst()) {
            	
            	System.out.print("\nEnter the Attribute Name you want to Edit  :  ");
            	String Attribute = input.nextLine();
            	
            	System.out.print("\nEnter the Value of the New Attribute  :  ");
            	String change_to = input.nextLine();
            	
            	
            	
            	Statement stmmt = con.createStatement();
            	
                //psupdate = con.prepareStatement("update Doctoret ? = '?' where email = '?' and password = '?'");
            	
            	BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\IIIT SRI CITY\\SEMESTER - 3\\OOP\\src\\SWASTHA\\My_Work\\EXCEL_CSV_DATA\\CSV_Data\\Doctor_CSV.csv"));
            	
            	String data;
            	int count = 0;
            	
            	while((data = bufferedReader.readLine()) != null)
                { 
                    count++;
                }
            	
            	count--;
            	
            	
            	System.out.println("Count : "+count);
            	
                String sql = "update Doctor set " + Attribute + " =  '" + change_to + "' where email =  '" + email + "' and password = '" + password + "'";
                
                stmmt.execute(sql);
                
                int index = 0;
                
                if (Attribute.equals("Hospital_ID"))
                	index = 0;
                
                else if (Attribute.equals("Specialization"))
                	index = 4;
                
                else if (Attribute.equals("Address"))
                	index = 7;
                
                else if (Attribute.equals("Pincode"))
                	index = 8;
                
                               
                
                CSVReader csv_read = new CSVReader(new FileReader(new File("E:\\IIIT SRI CITY\\SEMESTER - 3\\OOP\\src\\SWASTHA\\My_Work\\EXCEL_CSV_DATA\\CSV_Data\\Doctor_CSV.csv")));
                
                
                
                List<String[]> All_Doctor_Data =  csv_read.readAll();
                
                for (int i = 1; i <= count; i++)
                {
                	if ( (All_Doctor_Data.get(i)[5].equals(email)) && ((All_Doctor_Data.get(i)[6]).equals(password)) )
                		{ //System.out.print(i);
                		  All_Doctor_Data.get(i)[index] = change_to;
                		  
                		}
                	
                }
                
                CSVWriter csv_write = new CSVWriter(new FileWriter(new File("E:\\IIIT SRI CITY\\SEMESTER - 3\\OOP\\src\\SWASTHA\\My_Work\\EXCEL_CSV_DATA\\CSV_Data\\Doctor_CSV.csv")));
                csv_write.writeAll(All_Doctor_Data);
                csv_write.flush();
                
                      
                
                
                
                //psupdate.setString(1, Attribute);
                //psupdate.setString(2, change_to);
                //psupdate.setString(3, email);
                //psupdate.setString(4, password);

                //psupdate.executeUpdate();
                
                System.out.print("Data Successfully Updated in database");
                
                            
            }
            
            
            else
            {
            	System.out.print("Wrong Password. Please Try Agin");
            }
            
            
		
        }
        
        
        
        
        else  // No Email
        {
        	System.out.print("No Email Matched. Please Try again");
        }
		
		
		
			
        }
        
        catch (Exception e) {
            System.err.println(e);
        }
        
        
        

	}   // End main

}  // End Class
