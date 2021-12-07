package sprint1;


import java.util.Objects;
import java.util.Scanner;

public class Client extends User{
    
    public Database db;
    
    Client()
    {
        
    }
     
     public String toString() 
     {
        String s =  "Name: " + this.name + " Phone Number: " + MobileNum;
        if(!Objects.equals(Email, ""))
        {

            return  s + " Email: " + Email;
        }
        return s ;
    }
     
     public boolean Response (Driver d, float offer)
     {
         Scanner input = new Scanner(System.in);
         
         System.out.println("There's an offer from driver " + d.name + ", " + offer);
         System.out.println("Do you accept?");
         System.out.println("1- Yes");
         System.out.println("2- No");
         
         int i = input.nextInt();
         
         if (i == 1)
         {
             return true;
         }
         
         else 
         {
             return false;
         }
     }
     
     int RateDriver(Driver d)
     {
         Scanner input = new Scanner(System.in);
         
         System.out.println("Rate the driver (out of 5)");
         int i = input.nextInt();
         
          d.Ratings.add(i);
          
          return i;
     }
}
