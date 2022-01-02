package sprint1;

public class ClientAcceptance implements Event{

    private String eventName;
    private Client client;
    private String acceptanceTime;

    public ClientAcceptance(Client client, String acceptanceTime) {
        this.eventName = "Client accepts offer";
        this.client = client;
        this.acceptanceTime = acceptanceTime;
    }

    @Override
    public void GetEventDetails() {
        System.out.println("Event name: " + eventName);
        System.out.println("Client: " + client.name);
        System.out.println("Event time: " + acceptanceTime);
        System.out.println();
    }
}
