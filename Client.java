package sprint1;
import java.util.Objects;
import java.util.Scanner;

public class Client extends User{

    private int Num_r = 0;
    public String Birthday;
    public boolean inRide = false;

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

    public boolean Response (int i)
    {
        if (i == 1)
        {
            Num_r++;
            inRide = true;
            return true;
        }

        else
        {
            return false;
        }
    }

    int RateDriver(Driver d, int i)
    {
        d.Ratings.add(i);

        return i;
    }
    int getNum_r(){

        return Num_r;
    }
}
