import java.sql.*;
import java.io.*;

public class Customer extends Person {
    public String acc_no;
    private String acc_open_dt;
    private String home_br;
    Connection conn;

    public Customer(ResultSet res) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/KOVPwh7gZl", "KOVPwh7gZl", "EpNstXzaUh");
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
        }
        
        // while(res.next()) {
        //     this.acc_open_dt = res.getString("acc_open_date");
        //     this.home_br = res.getString("home_branch");
        // }

    }

    public void DispDetails() throws IOException, SQLException {
      System.out.println("-------Customer Details-------");
      System.out.println("Name               : "+ this.name);
      System.out.println("Phone Number       : "+ this.phone_no);
      System.out.println("Email ID           : "+ this.email);
      System.out.println("Address            : "+ this.address);
      System.out.println("Account number     : "+ this.acc_no);
      System.out.println("Account open date  : "+ this.acc_open_dt);
      System.out.println("Home Branch        : "+ this.home_br);
      System.out.println("------------------------------");
      System.out.println("Enter 1 to update details");
      System.out.println("Enter anything else to go back");
      int ch;
      BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
      ch = Integer.parseInt(b.readLine());
      switch(ch) {
         case 1:
            System.out.println("Enter new Email ID");
            String em = b.readLine();
            System.out.println("Enter new Phone number");
            String pn = b.readLine();
            System.out.println("Enter new Address");
            String ad = b.readLine();
            this.UpdateDetails(em,pn,ad);
         break;
      }

    }

    public void CheckBalance() throws SQLException {
        Statement st = conn.createStatement();
        String query = "SELECT `acc_balance` FROM `banking` WHERE `acc_no`='" + this.acc_no + "';";
        ResultSet tmp = st.executeQuery(query);

        tmp.next();
        System.out.println(tmp.getString("acc_balance"));
    }


    private void UpdateDetails(String email, String phone_no, String address) throws SQLException {
        Statement st = conn.createStatement();
        if(!(email == ""))
        {
            String query = "UPDATE `customer_details` SET `email`='"+email+"' WHERE `acc_no`='"+this.acc_no+"';";
            this.email = email;
        }
        if(!(phone_no == ""))
        {
            String query = "UPDATE `customer_details` SET `phone_no`='"+phone_no+"' WHERE `acc_no`='"+this.acc_no+"';";
            this.email = email;
        }
        if(!(address == ""))
        {
            String query = "UPDATE `customer_details` SET `address`='"+address+"' WHERE `acc_no`='"+this.acc_no+"';";
            this.address = address;
        }
    }
}