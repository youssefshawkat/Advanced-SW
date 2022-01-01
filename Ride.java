package sprint1;

public class Ride {
    float price;
    int Num_p;
    Client c = new Client();
    Driver d = new Driver();

    public void setNum_p(int num_p) {
        Num_p = num_p;
    }

    public int getNum_p() {
        return Num_p;
    }
}
