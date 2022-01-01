package sprint1;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.xml.crypto.Data;

public class Database {

    private Database(){};
    public static ArrayList<Driver> Drivers = new ArrayList<Driver>();
    public static ArrayList<Client> Clients = new ArrayList<Client>();
    public static ArrayList<Ride> Rides = new ArrayList<Ride>();
    public static ArrayList<Driver> Pending = new ArrayList<Driver>();
    public static ArrayList<String> AreasWithDiscount = new ArrayList<String>();
    public static ArrayList<String> PublicHolidays = new ArrayList<String>(Arrays.asList(
            "01-01-2022","25-01-2022","25-04-2022","01-05-2022","02-05-2022","03-05-2022","04-05-2022","05-05-2022","30-06-2022","08-07-2022","09-07-2022","10-07-2022","11-07-2022","12-07-2022","23-07-2022","30-07-2022","06-10-2022","08-10-2022"));
    public static Database database;

    public Admin Admin = new Admin("Mahmoud","mahmoud1804");


    public void setDrivers(Driver driver)
    {
        Drivers.add(driver);
    }

    public void setClients(Client client)
    {
        Clients.add(client);
    }

    public void setRides(Ride ride)
    {
        Rides.add(ride);
    }

    public static Database getDatabase()
    {
        if (database == null)
        {
            database = new Database();
        }
        return database;
    }

}
