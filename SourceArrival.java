package sprint1;

public class SourceArrival implements Event{

    private String eventName;
    private Driver driver;
    private Client client;
    private String arrivalTime;

    public SourceArrival(Driver driver,Client client, String arrivalTime) {
        this.driver = driver;
        this.eventName = "Driver arrived to source area";
        this.client = client;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public void GetEventDetails() {
        System.out.println("Event name: " + eventName);
        System.out.println("Driver: " + driver.name);
        System.out.println("Client: " + client.name);
        System.out.println("Event time: " + arrivalTime);
        System.out.println();
    }
}
