package sprint1;

import static java.lang.System.exit;
import static sprint1.Database.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("Birthday :  ");
        System.out.println("Please Enter in This Format : (dd-MM-yyyy)");
        String bd = input.nextLine();


        Client c = new Client();
        c.name = n;
        c.Password= p;
        c.Email = em;
        c.MobileNum = pn;
        c.Birthday = bd;

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
                System.out.println("Enter your source area: ");
                String st = input.next();
                System.out.println("Enter your destination area: ");
                String des = input.next();
                System.out.println("Enter Number of Passengers: ");
                int p = input.nextInt();
                Request(c, st, des,p);
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
        System.out.println("1- Finish Ride");
        System.out.println("2- List ratings");
        System.out.println("3- Enter favourite area");
        System.out.println("4- Show Balance");
        System.out.println("5- Exit");

        int i = input.nextInt();

        switch (i) {
            case 1 -> {
                if (d.available == false)
                {
                    d.available = true;
                    Ride r = d.getCurrentRide();
                    System.out.println("Rate the driver (out of 5)");
                    int rate = input.nextInt();
                    d.SetAvgRating(r.c.RateDriver(d, rate));
                    LocalDateTime destArrivalTime = LocalDateTime.now();
                    DateTimeFormatter formatDestArrivalTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDestArrivalTime = destArrivalTime.format(formatDestArrivalTime);
                    DestinationArrival DA = new DestinationArrival(d,r.c,formattedDestArrivalTime);
                    r.Events.add(DA);
                }

                else
                {
                    System.out.println("You don't have any unfinished rides at the moment.");
                }
                DriversMenu(d);
            }
            case 2 -> {
                for (int j = 0; j < d.Ratings.size(); j++) {
                    System.out.println(d.Ratings.get(j));
                }
                DriversMenu(d);
            }
            case 3 -> {
                Sprint1.AddFavArea(d);
                DriversMenu(d);
            }
            case 4 -> {
                System.out.println("Your balance is: " + d.getBalance());
                DriversMenu(d);
            }
            case 5 -> Sprint1.MainMenu();
        }
    }

    public static void AdminsMenu(Admin a)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome back, " + a.Name);
        System.out.println("1- Verify Drivers");
        System.out.println("2- Suspend Users");
        System.out.println("3- Add an area with discount");
        System.out.println("4- Show ride events");
        System.out.println("5- Exit");

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

            case 4 -> {
                a.ShowRideEvents();
                AdminsMenu(a);
            }

            case 5 -> Sprint1.MainMenu();
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

    public static void Request(Client c, String src, String dest, int p)
    {
        Notify(c, src, dest,p);
    }

    public static void Notify(Client c, String src, String des,int Num_p)
    {
        int foundDriver = 0;
        Scanner input = new Scanner(System.in);
        for (Driver driver : Drivers) {
            for (int j = 0; j < driver.favAreas.size(); j++) {
                if (src.equals(driver.favAreas.get(j)) && driver.Verified && driver.available) {
                    System.out.println("There is a Client you can pick, " + driver.name + " from " + driver.favAreas.get(j));
                    System.out.println("The Client Destination is " + des);

                    float driverPrice = Offer(c, des);
                    LocalDateTime offerTime = LocalDateTime.now();
                    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedTime = offerTime.format(formatTime);

                    if (driverPrice == 0) {
                        continue;
                    } else {
                        float clientPrice = discounts(driverPrice,c,Num_p,des);

                        DriverOffer DO = new DriverOffer(driver, driverPrice, formattedTime);
                        Ride r = new Ride();
                        r.Events.add(DO);

                        int resp;
                        System.out.println("There's an offer from driver " + driver.name + ", " + clientPrice);
                        System.out.println("Do you accept?");
                        System.out.println("1- Yes");
                        System.out.println("2- No");

                        LocalDateTime acceptanceTime = LocalDateTime.now();
                        DateTimeFormatter formatAcceptanceTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedAcceptanceTime = offerTime.format(formatAcceptanceTime);

                        ClientAcceptance CA = new ClientAcceptance(c,formattedAcceptanceTime);
                        r.Events.add(CA);

                        resp = input.nextInt();
                        if ((c.Response(resp))) {
                            foundDriver = 1;
                            r.c = c;
                            r.d = driver;
                            r.price = clientPrice;

                            LocalDateTime sourceArrivalTime = LocalDateTime.now();
                            DateTimeFormatter formatSourceArrivalTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            String formattedSourceArrivalTime = offerTime.format(formatSourceArrivalTime);

                            SourceArrival SA = new SourceArrival(driver,c,formattedSourceArrivalTime);
                            r.Events.add(SA);

                            driver.setBalance(driverPrice);
                            driver.available = false;
                            driver.setRide(r);
                            /*
                            System.out.println("Rate the driver (out of 5)");
                            int rate = input.nextInt();
                            driver.SetAvgRating(c.RateDriver(driver, rate));
                            LocalDateTime destArrivalTime = LocalDateTime.now();
                            DateTimeFormatter formatDestArrivalTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            String formattedDestArrivalTime = offerTime.format(formatDestArrivalTime);
                            DestinationArrival DA = new DestinationArrival(driver,c,formattedSourceArrivalTime);
                            r.Events.add(DA);

                             */
                            Rides.add(r);


                            break;
                        }
                    }
                }
            }
            if (foundDriver == 1)
            {
                foundDriver = 0;
                break;
            }
        }
    }

    public static float Discount(float price, int discount)
    {
        float dis = (price / 100) * discount ;
        float newPrice = price - dis;
        return newPrice;
    }

    public static float discounts(float price,Client c,int Num_p,String des){
        int discount = 0;

        //first ride discount
        if(c.getNum_r() == 0){
           discount = discount + 10;
        }
        //Areas with discounts added by admin
        for (String s : AreasWithDiscount) {
            if (s.equals(des)) {
                discount = discount+10;
            }

        }
        //if the ride contains two passengers
        if(Num_p == 2){

            discount = discount+5;

        }

        LocalDate date = LocalDate.now();
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(myFormatObj);
        for(String d : PublicHolidays){
            if(d.equals(formattedDate)){
                discount = discount+5;
            }

        }
        myFormatObj = DateTimeFormatter.ofPattern("dd-MM");
        formattedDate = date.format(myFormatObj);
        //if the ride matches client's birthday
        if(c.Birthday.length() >= 5) {
            if (c.Birthday.substring(0, 5).equals(formattedDate)) {
                discount = discount + 10;
            }
        }

        price = Discount(price,discount);

        return price;



    }





    public static void main(String[] args) {

        MainMenu();

    }
}
