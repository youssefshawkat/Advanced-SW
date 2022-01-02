package sprint1;
import java.util.Scanner;


public class Admin {
    public String Name;
    public String Password;

    public Admin(String n, String pw)
    {
        Name = n;
        Password = pw;
    }

    boolean Verify(int i)
    {
        if (i == 1)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    void Suspend (User u)
    {
        u.Suspended = true;
    }

    public void ShowRideEvents()
    {
        System.out.println();
        for (int i = 0; i < Database.Rides.size(); i++)
        {
            System.out.println("Ride #" + (i+1));
            for (int j = 0; j < Database.Rides.get(i).Events.size(); j++)
            Database.Rides.get(i).Events.get(j).GetEventDetails();
            System.out.println();
        }
    }
}
