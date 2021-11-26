import java.sql.*;

public class Customer extends Person {
    public String acc_no;
    private String acc_open_dt;
    private String home_br;
    Connection conn;

    public Customer(ResultSet res) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/KOVPwh7gZl","KOVPwh7gZl","EpNstXzaUh");
        }
        catch (SQLException e) {
            System.out.println("SQL Exception error occurred.\n" + e);
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC MySQL bridge failed to load... Jar file maybe missing.\n" + e);
    }
        while(res.next()) {
            this.name = res.getString("name");
            this.phone_no = res.getString("phone_no");
            this.email = res.getString("email");
            this.address = res.getString("address");
            this.acc_no = res.getString("acc_no");
            this.acc_open_dt = res.getString("acc_open_dt");
            this.home_br = res.getString("home_br");
        }

        //print the result in ui
    }

    public String CheckBalance() throws SQLException {
        Statement st = conn.createStatement();
        String query = "SELECT acc_bal FROM customer_details WHERE acc_no=" + this.acc_no + ";";
        ResultSet tmp = st.executeQuery(query);

        tmp.next();
        return tmp.getString("acc_no");
    }

    // public String[] DisplayDetails() {
    //     // Code yet to be implemented
    // }

    public void UpdateDetails(String email, String phone_no, String address) throws SQLException {
        Statement st = conn.createStatement();
        if(email == "")
        {
            String query = "UPDATE customer_details SET email="+email+"WHERE acc_no="+this.acc_no+";";
            this.email = email;
        }
        if(phone_no == "")
        {
            String query = "UPDATE customer_details SET phone_no="+phone_no+"WHERE acc_no="+this.acc_no+";";
            this.email = email;
        }
        if(address == "")
        {
            String query = "UPDATE customer_details SET address="+address+"WHERE acc_no="+this.acc_no+";";
            this.address = address;
        }
    }
}