package sprint1;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Driver extends User{

    public String NationalId;
    public String Driver_license;
    public ArrayList<String> favAreas = new ArrayList<String>();
    public ArrayList<Integer> Ratings = new ArrayList<Integer> ();
    public int rideNum = 0;
    public float avgRating;
    public boolean Verified = false;

    Driver()
    {

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

    void SetAvgRating(int rate)
    {

        float i = avgRating * rideNum;
        rideNum++;
        avgRating = (i + rate) / (float) (rideNum);
    }
}
