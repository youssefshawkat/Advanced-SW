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

    boolean Verify()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Do you want to verify this driver?");
        System.out.println("1- yes");
        System.out.println("2- no");

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

    void Suspend (User u)
    {
        u.Suspended = true;
    }
}
