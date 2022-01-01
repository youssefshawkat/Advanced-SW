package sprint1;

import static java.lang.System.exit;
import static sprint1.Database.*;

import java.util.Scanner;

public class Sprint1 {

    static Database database = Database.getDatabase();

    public static void MainMenu()
    {
        while (true)
        {
            Scanner input = new Scanner(System.in);

            System.out.println("1-Register");
            System.out.println("2-Login");
            System.out.println("3-Exit");

            int n = input.nextInt();

            switch (n) {
                case 1 -> RegisterUser();
                case 2 -> Login();
                case 3 -> exit(0);
            }
        }
    }

    public static void RegisterUser()
    {
        int i;
        System.out.println("What would you like to Register as ?");
        System.out.println("(1) Driver");
        System.out.println("(2) Client");

        Scanner input = new Scanner(System.in);
        i = input.nextInt();
        input.nextLine();

        if (i == 1)
        {
            GetDriverData();
        }

        else
        {
            GetClientData();
        }
    }

    public static void Login()
    {
        int i;
        System.out.println("What would you like to Login as ?");
        System.out.println("(1) Driver");
        System.out.println("(2) Client");
        System.out.println("(3) Admin");

        Scanner input = new Scanner(System.in);
        i = input.nextInt();
        input.nextLine();

        switch (i) {
            case 1 -> DriverLogin();
            case 2 -> ClientLogin();
            case 3 -> AdminLogin();
        }
    }

    public static void GetClientData()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Name :");
        String n = input.nextLine();
        while(UsersClients(n) != null)
        {
            System.out.println("This UserName is Already Taken: ");
            n = input.nextLine();
        }
        System.out.println("Password :");
        String p = input.nextLine();
        System.out.println("Phone Number :");
        String pn = input.nextLine();
        System.out.println("Email (Optional) :");
        String em = input.nextLine();

        Client c = new Client();
        c.name = n;
        c.Password= p;
        c.Email = em;
        c.MobileNum = pn;

        Database.Clients.add(c);
        MainMenu();

    }

    public static void GetDriverData()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Name :");
        String n = input.nextLine();
        while(UsersDrivers(n) != null)
        {
            System.out.println("This UserName is Already Taken: ");
            n = input.nextLine();
        }
        System.out.println("Password :");
        String p = input.nextLine();
        System.out.println("Phone Number :");
        String pn = input.nextLine();
        System.out.println("Email (Optional) :");
        String em = input.nextLine();
        System.out.println("National Id : ");
        String ni = input.nextLine();
        System.out.println("Driver License : ");
        String dl = input.nextLine();

        Driver d = new Driver();
        d.name = n;
        d.Password= p;
        d.Email = em;
        d.NationalId = ni;
        d.MobileNum = pn;
        d.Driver_license = dl;

        database.setDrivers(d);
        System.out.println("Add favourite Areas:");
        AddFavArea(d);


        Database.Pending.add(Database.Drivers.get(Database.Drivers.size()-1));
        System.out.println("An admin will verify your account soon");
        MainMenu();


    }


    public static void AddFavArea(Driver driver)
    {
        while (true)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("What is the name of the area you want to enter?");
            String s = input.nextLine();
            driver.favAreas.add(s);

            System.out.println("1-continue");
            System.out.println("2-exit");
            int i = input.nextInt();

            if (i == 2)
            {
                break;
            }

        }
    }

    public static void DriverLogin()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your user name");
        String s = input.nextLine();
        System.out.println("Enter your password");
        String s1 = input.nextLine();
        int i;
        int flag = 0;

        for (i = 0; i < Database.Drivers.size(); i++)
        {
            if (Database.Drivers.get(i).name.equals(s))
            {
                if (Database.Drivers.get(i).Password.equals(s1))
                {
                    if (!Database.Drivers.get(i).Verified)
                    {
                        System.out.println("your account isn't verified yet.");
                        MainMenu();
                    }

                    else if (Database.Drivers.get(i).Suspended)
                    {
                        System.out.println("your account is suspended.");
                        MainMenu();
                    }

                    else
                    {
                        DriversMenu(Database.Drivers.get(i));
                        flag = 1;
                    }
                }

                else
                {
                    System.out.println("Wrong username or Password");
                    DriverLogin();
                }
                break;
            }
        }
        if ( i == Database.Drivers.size() && flag == 0)
        {
            System.out.println("Wrong username or Password");
            DriverLogin();
        }

    }

    public static void ClientLogin()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your user name");
        String s = input.nextLine();
        System.out.println("Enter your password");
        String s1 = input.nextLine();
        int i;
        int flag = 0;

        for (i = 0; i < Database.Clients.size(); i++)
        {
            if (Database.Clients.get(i).name.equals(s))
            {
                if (Database.Clients.get(i).Password.equals(s1))
                {
                    if (Database.Clients.get(i).Suspended)
                    {
                        System.out.println("your account is suspended.");
                        MainMenu();
                    }

                    else
                    {
                        UsersMenu(Database.Clients.get(i));
                        flag = 1;
                    }
                }

                else
                {
                    System.out.println("Wrong username or Password");
                    ClientLogin();
                }
                break;
            }
        }
        if ( i == Database.Clients.size() && flag == 0)
        {
            System.out.println("Wrong username or Password");
            ClientLogin();
        }
    }

    public static void AdminLogin()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your user name");
        String s = input.nextLine();
        System.out.println("Enter your password");
        String s1 = input.nextLine();


        if (database.Admin.Name.equals(s))
        {
            if (database.Admin.Password.equals(s1))
            {
                AdminsMenu(database.Admin);
            }

            else
            {
                System.out.println("Wrong username or Password");
                AdminLogin();
            }
        }
    }

    public static void UsersMenu(Client c)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome back, " + c.name);
        System.out.println("1- Request a ride");
        System.out.println("2- View average ratings");
        System.out.println("3- Exit");

        int i = input.nextInt();

        switch (i) {
            case 1 -> {
                System.out.println("Enter your source area");
                String st = input.next();
                System.out.println("Enter your destination area");
                String des = input.next();
                Request(c, st, des);
                UsersMenu(c);
            }
            case 2 -> {
                for (Driver driver : Drivers) {
                    System.out.println(driver.name + ":");
                    System.out.println(driver.avgRating);
                }
                UsersMenu(c);
            }
            case 3 -> Sprint1.MainMenu();
        }

    }

    public static void DriversMenu (Driver d)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome back, " + d.name);
        System.out.println("1- List ratings");
        System.out.println("2- Enter favourite area");
        System.out.println("3- Show Balance");
        System.out.println("4- Exit");

        int i = input.nextInt();

        switch (i) {
            case 1 -> {
                for (int j = 0; j < d.Ratings.size(); j++) {
                    System.out.println(d.Ratings.get(j));
                }
                DriversMenu(d);
            }
            case 2 -> {
                Sprint1.AddFavArea(d);
                DriversMenu(d);
            }
            case 3 -> {
                System.out.println("Your balance is: " + d.getBalance());
                DriversMenu(d);
            }
            case 4 -> Sprint1.MainMenu();
        }
    }

    public static void AdminsMenu(Admin a)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome back, " + a.Name);
        System.out.println("1- Verify Drivers");
        System.out.println("2- Suspend Users");
        System.out.println("3- Add an area with discount");
        System.out.println("4- Exit");

        int i = input.nextInt();

        switch (i) {
            case 1 -> {
                int temp = Pending.size();
                for (int j = 0; j < temp; j++) {
                    System.out.println(Pending.get(j).toString());

                    System.out.println("Do you want to verify this driver?");
                    System.out.println("1- yes");
                    System.out.println("2- no");

                    int c = input.nextInt();
                    boolean answer = a.Verify(c);

                    for (Driver driver : Drivers) {
                        if (driver.name.equals(Pending.get(j).name)) {
                            driver.Verified = answer;
                        }
                    }


                    if (answer) {
                        Pending.remove(Pending.get(j));
                        j--;
                        temp--;
                    }
                }
                AdminsMenu(a);
            }
            case 2 -> {
                Scanner input2 = new Scanner(System.in);
                System.out.println("who do you want to suspend?");
                System.out.println("1- Driver");
                System.out.println("2- Client");
                i = input.nextInt();
                if (i == 1) {
                    System.out.println("Enter the name");
                    String s = input2.nextLine();

                    for (Driver driver : Drivers) {
                        if (driver.name.equals(s)) {
                            a.Suspend(driver);
                        }
                    }
                } else {
                    System.out.println("Enter the name");
                    String s = input2.nextLine();

                    for (Client client : Clients) {
                        if (client.name.equals(s)) {
                            a.Suspend(client);
                        }
                    }
                }
                AdminsMenu(a);
            }
            case 3 -> {
                System.out.println("Enter the name of the area");
                String area = input.next();
                AreasWithDiscount.add(area);
                AdminsMenu(a);
            }
            case 4 -> Sprint1.MainMenu();
        }
    }

    public static String UsersClients(String name)
    {
        for (Client i: Database.Clients)
        {
            if(i.name.equals(name))
            {
                return name;
            }
        }
        return null;
    }

    public static String UsersDrivers(String name)
    {
        for (Driver i: Database.Drivers)
        {
            if(i.name.equals(name))
            {
                return name;
            }
        }
        return null;
    }


    public static float Offer(Client c, String dest) // here
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

    public static void Request(Client c, String src, String dest)
    {
        Notify(c, src, dest);
    }

    public static void Notify(Client c, String src, String des)
    {
        Scanner input = new Scanner(System.in);
        for (Driver driver : Drivers) {
            for (int j = 0; j < driver.favAreas.size(); j++) {
                if (src.equals(driver.favAreas.get(j)) && driver.Verified) {
                    System.out.println("There is a Client you can pick, " + driver.name + " from " + driver.favAreas.get(j));
                    System.out.println("The Client Destination is " + des);

                    float driverPrice = Offer(c, des);
                    if (driverPrice == 0) {
                        continue;
                    } else {
                        float clientPrice = driverPrice;
                        for (String s : AreasWithDiscount) {
                            if (s.equals(des)) {
                                clientPrice = Discount(driverPrice, 10);
                            }
                        }
                        int resp;
                        System.out.println("There's an offer from driver " + driver.name + ", " + clientPrice);
                        System.out.println("Do you accept?");
                        System.out.println("1- Yes");
                        System.out.println("2- No");

                        resp = input.nextInt();
                        if ((c.Response(resp))) {
                            driver.setBalance(driverPrice);
                            System.out.println("Rate the driver (out of 5)");
                            int rate = input.nextInt();

                            driver.SetAvgRating(c.RateDriver(driver, rate));

                            break;
                        }
                    }
                }
            }
        }
    }

    public static float Discount(float price, int discount)
    {
        float dis = (price / 100) * discount ;
        float newPrice = price - dis;
        return newPrice;
    }


    public static void main(String[] args) {

        MainMenu();

    }
}
