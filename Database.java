package sprint1;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.crypto.Data;

public class Database {
    
    public ArrayList<Driver> Drivers = new ArrayList<Driver>();
    public ArrayList<Client> Clients = new ArrayList<Client>();
    public ArrayList<Ride> Rides = new ArrayList<Ride>();
    public ArrayList<Driver> Pending = new ArrayList<Driver>();
    
    public Driver d;
    public Client c;
    public Ride r;
    public Admin Admin = new Admin("Mahmoud","mahmoud1804");
    
    
    public void setDrivers(Driver driver)
    {
        driver.db = this;
        Drivers.add(driver);
    }
    
    public void setClients(Client client)
    {
        client.db = this;
        Clients.add(client);
    }

    public void setRides(Ride ride)
    {
        ride.db = this;
        Rides.add(ride);
    }
    
    
    public void Request(Client c, String src, String dest)
    {
        Notify(c, src, dest);
    }
    
    public void Notify(Client c, String src, String des)
    {
        for (int i = 0; i < Drivers.size(); i++)
        {
            for (int j = 0; j < Drivers.get(i).favAreas.size(); j++)
            {
                if (src.equals(Drivers.get(i).favAreas.get(j)) && Drivers.get(i).Verified == true)
                {
                    System.out.println("There is a Client you can pick, " + Drivers.get(i).name + " from " + Drivers.get(i).favAreas.get(j));
                    System.out.println("The Client Destination is " + des);
                    float temp = Drivers.get(i).Offer(c, des);
                    if  (temp == 0)
                    {
                        continue;
                    }
                    
                    else
                    {
                        if ((c.Response(Drivers.get(i), temp)) == true)
                        {
                            r = new Ride();
                            
                            r.c = c;
                            r.d = d;
                            r.price = temp;
                            
                            setRides(r);
                            
                            Drivers.get(i).SetAvgRating(c.RateDriver(Drivers.get(i)));   
                            
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void UsersMenu(Client c)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome back, " + c.name);
        System.out.println("1- Request a ride");
        System.out.println("2- View average ratings");
        System.out.println("3- Exit");
        
        int i = input.nextInt();
        
        switch (i) {
            
            case 1:
            
                System.out.println("Enter your source area");
                String st = input.next();
                System.out.println("Enter your destination area");
                String des = input.next();
                Request(c, st, des);
                break;
           
            case 2:
                
                for (int j = 0; j < Drivers.size(); j++)
                {
                    System.out.println(Drivers.get(j).name + ":");
                    System.out.println(Drivers.get(j).avgRating);
                }   
                break;
            
            case 3:
                Sprint1.MainMenu();
                break;
        }
        
    }
    
    public void DriversMenu (Driver d)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome back, " + d.name);
        System.out.println("1- List ratings");
        System.out.println("2- Enter favourite area");
        System.out.println("3- Exit");
        
        int i = input.nextInt();
        
        switch (i) {
            
            case 1:
                
                for (int j = 0; j < d.Ratings.size(); j++)
                {
                    System.out.println(d.Ratings.get(j));
                }   break;
            
            case 2:
                
                Sprint1.AddFavArea(d);
                break;
            
            case 3:
                
                Sprint1.MainMenu();
                break;
        }
    }
    
    public void AdminsMenu(Admin a)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome back, " + a.Name);
        System.out.println("1- Verify Drivers");
        System.out.println("2- Suspend Users");
        System.out.println("3- Exit");
        
        int i = input.nextInt();
        
        switch(i)
        {
            case 1:
                int temp = Pending.size();
                for (int j = 0; j < temp; j++)
                {
                    System.out.println(Pending.get(j).toString());
                    boolean answer = a.Verify();
                    
                    for (int k = 0; k < Drivers.size(); k++)
                    {
                      if (Drivers.get(k).name.equals(Pending.get(j).name))
                      {
                          Drivers.get(k).Verified = answer;
                      }
                    }
                    
                    
                    if (answer == true)
                    {
                       Pending.remove(Pending.get(j));
                       j--;
                       temp--;
                    }                   
                }

                 break;
                 
            case 2:
                Scanner input2 = new Scanner(System.in);
                
                System.out.println("who do you want to suspend?");
                System.out.println("1- Driver");
                System.out.println("2- Client");
                
                i = input.nextInt();
                
                if(i == 1)
                {
                    System.out.println("Enter the name");
                    String s = input2.nextLine();
                    
                    for (int j = 0; j < Drivers.size(); j++)
                    {
                        if (Drivers.get(j).name.equals(s))
                        {
                            a.Suspend(Drivers.get(j));
                        }
                    }
                }
                
                else
                {
                    System.out.println("Enter the name");
                    String s = input2.nextLine();
                    
                    for (int j = 0; j < Clients.size(); j++)
                    {
                        if (Clients.get(j).name.equals(s))
                        {
                            a.Suspend(Clients.get(j));
                        }
                    }
                }
                
               break;
                
            case 3:
                Sprint1.MainMenu();
        }
    }
     
}
