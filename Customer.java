public class Customer extends Person {
    private String pan_no;
    private String acc_no;
    private String acc_bal;
    private String acc_open_dt;
    private String home_br;

    public Customer(String name, String phone_no, String email, String address, String ssn, String dob, String pan_no, String acc_no, String acc_bal, String acc_open_dt, String home_br) {
        super(name, phone_no, email, address, ssn, dob);
        this.pan_no = pan_no;
        this.acc_no = acc_no;
        this.acc_bal = acc_bal;
        this.acc_open_dt = acc_open_dt;
        this.home_br = home_br;
    }

    public double CheckBalance() {
        return this.acc_bal;
    }

    public String DisplayDetails() {
        // Code yet to be implemented
    }

    public void UpdateDetails() {

    }
}
