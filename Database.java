package sprint1;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.crypto.Data;

public class Database {

    public static ArrayList<Driver> Drivers = new ArrayList<Driver>();
    public static ArrayList<Client> Clients = new ArrayList<Client>();
    public static ArrayList<Ride> Rides = new ArrayList<Ride>();
    public static ArrayList<Driver> Pending = new ArrayList<Driver>();

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

}
