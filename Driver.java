package sprint1;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Driver extends User{
    
    public String NationalId;
    public String Driver_license;
    public ArrayList<String> favAreas = new ArrayList<String>();
    public User u;
    public ArrayList<Integer> Ratings = new ArrayList<Integer> ();
    public int rideNum = 0;
    public float avgRating;
    public boolean Verified = false;
    
    public Database db;
    
    
    Scanner input = new Scanner(System.in);
    
    
    Driver()
    {
        
    }
    
    Driver( String Name ,String Password,String Mobile,String N_ID,String DL,String EM)
    {
        NationalId = N_ID;
        Driver_license = DL;
        name = Name;
        MobileNum = Mobile;
        this.Password = Password;
        Email = EM;
    }
    
    
    Driver( String Name ,String Password,String Mobile,String N_ID,String DL)
    {
        NationalId = N_ID;
        Driver_license = DL;
        name = Name;
        MobileNum = Mobile;
        this.Password = Password;
    }
    
    @Override
    public String toString() 
    {
       String s =  "Name: " + this.name + " Phone Number: " + MobileNum + "  National Id: " + NationalId + " Driver License : " + Driver_license;
       if(!Objects.equals(Email, "")){

           return  s + " Email: " + Email;
       }
        return s ;
    }
    
    
    public float Offer(Client c, String dest)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Do you accept this ride?");
        System.out.println("1- Yes");
        System.out.println("2- No");
        
        int i = input.nextInt();
        
        if (i ==1)
        {
            System.out.println("Enter offer");
            float f = input.nextFloat();
            return f;
        }
        
        else
        {
            return 0;
        }
    }
    
    void SetAvgRating(int rate)
    {
        
        float i = avgRating * rideNum;
        rideNum++;
        avgRating = (i + rate) / (float) (rideNum);
    }
}
