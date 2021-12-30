package sprint1;
import java.util.Objects;
import java.util.Scanner;

public class Client extends User{

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

    public boolean Response (int i) // here
    {
        /*System.out.println("There's an offer from driver " + d.name + ", " + offer);
        System.out.println("Do you accept?");
        System.out.println("1- Yes");
        System.out.println("2- No")*/
        if (i == 1)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    int RateDriver(Driver d, int i) // here
    {
        d.Ratings.add(i);

        return i;
    }
}
