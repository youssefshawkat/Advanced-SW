package sprint1;


import static java.lang.System.exit;
import java.util.Scanner;

public class Sprint1 {
    
    static Database database = new Database();

     public static void MainMenu()
    {        
        while (true)
        {
            Scanner input = new Scanner(System.in);

            System.out.println("1-Register");
            System.out.println("2-Login");
            System.out.println("3-Exit");

            int n = input.nextInt();

            switch(n)
            {
                case 1:
                   RegisterUser();  
                   break;
                case 2:
                   Login();
                   break;
                case 3:
                   exit(0);
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
        
        switch(i)
        {

            case 1:
            {             
                DriverLogin();
                break;
            }
            
            case 2: 
            {
                ClientLogin();
                break;
            }
            
            case 3:
            {
                AdminLogin();
            }
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
                
                database.c = new Client();
                database.c.name = n;
                database.c.Password= p;
                database.c.Email = em;
                database.c.MobileNum = pn;
                
                database.Clients.add(database.c);
                MainMenu();
                
      }
    
    public static void GetDriverData()
      {
                Scanner input = new Scanner(System.in);
                int i;
                
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
                
                database.d = new Driver();
                database.d.name = n;
                database.d.Password= p;
                database.d.Email = em;
                database.d.NationalId = ni;
                database.d.MobileNum = pn;
                database.d.Driver_license = dl;
                
                database.setDrivers(database.d);
                System.out.println("Add favourite Areas:");
                AddFavArea(database.d);
                
                
                database.Pending.add(database.Drivers.get(database.Drivers.size()-1));
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
        
        for (i = 0; i < database.Drivers.size(); i++)
        {
            if (database.Drivers.get(i).name.equals(s))
            {
                if (database.Drivers.get(i).Password.equals(s1))
                {
                    if (database.Drivers.get(i).Verified == false)
                    {
                        System.out.println("your account isn't verified yet.");
                        MainMenu();
                    }
                    
                    else if (database.Drivers.get(i).Suspended == true)
                    {
                        System.out.println("your account is suspended.");
                        MainMenu();
                    }
                    
                    else
                    {
                        database.DriversMenu(database.Drivers.get(i));
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
        if ( i == database.Drivers.size() && flag == 0)
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
        
        for (i = 0; i < database.Clients.size(); i++)
        {
            if (database.Clients.get(i).name.equals(s))
            {
                if (database.Clients.get(i).Password.equals(s1))
                {
                    if (database.Clients.get(i).Suspended == true)
                    {
                       System.out.println("your account is suspended.");
                      MainMenu();
                    }
                    
                    else
                    {
                        database.UsersMenu(database.Clients.get(i));
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
        if ( i == database.Clients.size() && flag == 0)
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
                    database.AdminsMenu(database.Admin); 
                }
                
                else
                {
                   System.out.println("Wrong username or Password");
                   AdminLogin();
                }
            }
    }
    
    public static String UsersClients(String name)
     {
        for (Client i: database.Clients) 
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
        for (Driver i: database.Drivers) 
        {
            if(i.name.equals(name)) 
            {
                return name;
            }
        }
        return null;
    }

    
    public static void main(String[] args) {
    //public void main(String[] args) {
        MainMenu();

    }
    
}
