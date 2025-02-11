package assignment_question;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

class Donor implements Serializable
{
    String name;
    int age;
    String address;
    long contact_no;
    String blood_group;
    Calendar last_donated_date=Calendar.getInstance();
    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
    Donor(String name,int age,String address,long contact_no,String blood_group,String date1) 
    {
        this.name=name;
        this.age=age;
        this.address=address;
        this.contact_no=contact_no;
        this.blood_group=blood_group;
        try{
            Date date=formatter.parse(date1);// parsing(separeting) the date string
            last_donated_date.setTime(date);// date to calender object
        }
        catch(ParseException e)
        {
            System.out.println("Not a valid date ");
        }
    }
    @Override
    public String toString()
    {
        return  "Name: "+name+
                "\nAge: "+age+
                "\nAddress: "+address+
                "\nContact_no: "+contact_no+
                "\nBlood Group: "+blood_group+
                "\nDate: "+formatter.format(last_donated_date.getTime())+//Calendar to String
                "\n---------------------------------------------------------";
    }
    
}
public class assignment_4 {
    public static void main(String args[]){
       Scanner in =new Scanner(System.in);
       String name;
       int age;
       String address;
       long number;
       String blood_group;
       String date;
        System.out.println("Constrints : blood group should be 'A+ve' & shouldn't be donated for last six months");
       System.out.print("Enter the number of Donors: ");
       int Donors_no=in.nextInt();
       Donor[] d; // array of donor reference
       d = new Donor[Donors_no];
       try
       {
       FileOutputStream fos=new FileOutputStream("Donor_Details_01");
       ObjectOutputStream oos = new ObjectOutputStream(fos);   
       for (int i=0;i<Donors_no;i++)
       {
           System.out.println("Please enter the details for Donor-"+(i+1));
           System.out.print("Enter name : ");
           name=in.next();
           System.out.print("Enter age : ");
           age=in.nextInt();
           in.nextLine();
           System.out.print("Enter address : ");
           address=in.nextLine();
           System.out.print("Enter mobile number : ");
           number=in.nextLong();
           System.out.print("Enter blood group : ");
           blood_group=in.next();
           System.out.print("Enter date of last donation : ");
           date=in.next();
           d[i]=new Donor(name,age,address,number,blood_group,date);
           oos.writeObject(d[i]);
           oos.flush();
       }
        oos.close();
       }
       catch(FileNotFoundException fnfe)
       {
           System.out.println("During Serialization, error occured due to "+fnfe);
       }  
       catch(IOException ioe)
       {
           System.out.println("During Serialization, error occured due to "+ioe);
       }
        System.out.println("\n========================================================="
                + "\nDeserializing......\n");
       try{
        FileInputStream fis=new FileInputStream("Donor_Details_01");
        ObjectInputStream ois = new ObjectInputStream(fis);    
        Calendar six_month = Calendar.getInstance();  
        six_month.add(Calendar.MONTH,-6);//getting the instance of six month ago calendar 
        Donor []d2;
        d2 = new Donor[Donors_no];
        int count=0;//flag to check presence of data
           System.out.println("Details of Donors with 'A+ve' & didn't donate on past six months .......\n");
        for (int i=0;i<Donors_no;i++)
        {
            d2[i] = (Donor) ois.readObject();
            //constraint
            if((d2[i].blood_group).equals("A+ve") && (d2[i].last_donated_date).compareTo(six_month)<=0)
            {
                System.out.println(d2[i]);
                count++;
            }
        }
        ois.close();
        if(count==0)// executed when no data is found for the constraint 
        {
            System.out.println("No Data Found");
        }
       }
        catch(FileNotFoundException fnfe)
       {
           System.out.println("During Deserialization, error occured due to "+fnfe);
       }  
       catch(IOException | ClassNotFoundException e)
       {
           System.out.println("During Deserialization, error occured due to "+e);
       }
        System.out.println("\n=========================================================");
    }
}