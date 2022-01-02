package sprint1;

import java.time.LocalDateTime;

public class DriverOffer implements Event{

    private String eventName;
    private Driver driver;
    private float price;
    private String offerTime;

    public DriverOffer(Driver driver, float price, String offerTime) {
        this.eventName = "Driver offer";
        this.driver = driver;
        this.price = price;
        this.offerTime = offerTime;
    }

    @Override
    public void GetEventDetails() {
        System.out.println("Event name: " + eventName);
        System.out.println("Driver: " + driver.name);
        System.out.println("Ride price: " + price);
        System.out.println("Event time: " + offerTime);
        System.out.println();
    }
}
