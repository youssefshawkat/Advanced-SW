package sprint1;

import java.util.ArrayList;

public class Ride {
    float price;
    int Num_p;
    Client c = new Client();
    Driver d = new Driver();
    ArrayList<Event> Events = new ArrayList<Event>();

    public void setNum_p(int num_p) {
        Num_p = num_p;
    }

    public int getNum_p() {
        return Num_p;
    }
}
