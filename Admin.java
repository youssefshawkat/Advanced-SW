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
}
