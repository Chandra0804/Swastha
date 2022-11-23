

import java.util.*;
import java.io.*;
import java.lang.*;






public class Prescriptions
{
Scanner input = new Scanner(System.in);

ArrayList<String> Medicines = new ArrayList<String>();

Doctor Doctor_Object;
Person Person_Object;


public void Create_Medication()
{

String Medicine,Dosage;   // Dosage input must be given as 1-0-1 [ 1 in the Morning, 0 in the Afternoon, 1 in the Evening]

System.out.print("\n\nEnter The Medicine Name  :  ");
Medicine = input.nextLine();
System.out.print("\nEnter the Dosage for the Medicine  :  ");
Dosage = input.nextLine();

// The Medicine Names and Dosage will be adjacent in ArrayList

Medicines.add(Medicine);
Medicines.add(Dosage);


}






public void Display_Medication()
{

System.out.print(\nThe Name of the Patient  :  ");
Patient_Object.getName();
System.out.print("\n

}







public static void main(String[] args)
{

while (true)
{
System.out.print("\n\n1.  Enter A Medication\n");
System.out.print("2.  Display The Medication\n");
System.out.print("3.  EXIT\n\n");

System.out.print("Please Enter Your Choice  :  ");
int C = Integer.parseInt(input.nextLine());

if (C == 1)
	Create_Medication();

else if (C == 2)
	Display_Medication();

else
	break;


}  // End While 


}  // End Main 

}  // End Prescriptions