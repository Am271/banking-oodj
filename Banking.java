import java.sql.*;
import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.io.*;

public class Banking {

    private Customer c;// Customer instance used by Bank
    private Connection conn;

    public Banking() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/KOVPwh7gZl", "KOVPwh7gZl", "EpNstXzaUh");
        } catch (SQLException e) {
            System.out.println("SQL Exception error occurred.\n" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC MySQL bridge failed to load... Jar file maybe missing.\n" + e);
        }
    }

    public boolean login(String username, String password) throws SQLException {
        String query;
        ResultSet login_cred;
        String acc_no;

        query = "SELECT * FROM credentials WHERE username=" + username + " AND password=" + password + ";";
        // The above string is the query to check if the database contains credentials entered by the user
        Statement st = conn.createStatement(); // Creation of the statement to execute the query
        login_cred = st.executeQuery(query); // Query is executed and result set is obtained

        if (!login_cred.isBeforeFirst()) {
            return false;
        } else {
            login_cred.next();
            acc_no = login_cred.getString("acc_no"); // Get account number from the result set
            query = "SELECT * FROM customer_details WHERE acc_no=" + acc_no + ";";
            ResultSet tmp = st.executeQuery(query);
            c = new Customer(tmp);
            return true;
        }
    }

    public void signup(String tname, String tphno, String temail, String taddr, String tssn, String tdob, String tpan, String tusername, String tpass) throws SQLException {
        String tacno, thbr, query;

        long tacno_ = (long) Math.floor(Math.random() * 900_000_000L) + 1_000_000_000L;

        tacno = String.valueOf(tacno_);

//    String pattern = "yyyy-MM-dd";
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//    tacopdt = simpleDateFormat.format(new Date());
        java.sql.Date tacopdt = new java.sql.Date(new java.util.Date().getTime());
        thbr = "Kudlu Gate";

        Statement st = conn.createStatement();

        query = "INSERT INTO banking (`acc_no`, `name`, `phone_no`, `acc_balance`, `acc_open_date`, `home_branch`) VALUES(`" + tacno + "`,`" + tname + "`,`" + tphno + "`," + "10000.00" + ", ? ,`" + thbr + "`);";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setDate(1, tacopdt);
        pstmt.execute();

        query = "INSERT INTO customer_details (`name`, `phone_no`, `email`, `address`, `social_security_no`, `date_of_birth`, `pan`, `acc_no`) VALUES(`" + tname + "`,`" + tphno + "`,`" + temail + "`,`" + taddr + "`,`" + tssn + "`, ? ,`" + tpan + "`,`" + tacno + "`);";
        pstmt = conn.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(tdob));
        pstmt.execute();

        query = "INSERT INTO credentials (`username`, `password`, `acc_no`) VALUES(`" + tusername + "`,`" + tpass + "`,`" + tacno + "`);";
        st.executeUpdate(query);
    }

    public boolean Transact(String tr_amt, String benf_name, String benf_acc_no) throws SQLException {
        Statement st = conn.createStatement();
        String query, tr_date, pattern;

        ResultSet r = st.executeQuery("SELECT * FROM banking WHERE acc_no=" + benf_acc_no + ";");
        if (!r.isBeforeFirst()) {
            return false;
        }

        long tr_id = (long) Math.floor(Math.random() * 900_000_000_000L) + 100_000_000_000L;

        pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//    tr_date = simpleDateFormat.format(new Date());

//    query="INSERT INTO transactions VALUES(" + c.acc_no + "," + benf_acc_no + "," + benf_name + "," + tr_id + "," + tr_amt + "," + tr_date + ");";
//    st.executeUpdate(query);
        return true;
    }

    public void DispTransactions(int no_tr) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet r;
        String query;

        if (no_tr == -1) {
            r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM transactions WHERE from_acc_no=" + c.acc_no + " OR to_acc_no=" + c.acc_no + ";");
            r.next();
            no_tr = r.getInt("rowcount");
        }

        query = "SELECT * FROM transactions WHERE from_acc_no=" + c.acc_no + " OR to_acc_no=" + c.acc_no + ";";
        r = st.executeQuery(query);

        for (int i = 0; i < no_tr; i++) {
            r.next();
        }
    }

    public boolean ChangePassword(String old_pass, String new_pass) throws SQLException {
        boolean flag;
        Statement s = conn.createStatement();
        String query;

        query = "SELECT * FROM credentials WHERE password=" + old_pass + ";";
        ResultSet r = s.executeQuery(query);
        if (!r.isBeforeFirst()) {
            return false;
        }

        query = "UPDATE TABLE credentials SET password =" + new_pass + "WHERE acc_no=" + c.acc_no + " AND password=" + old_pass + ";";
        s.executeUpdate(query);
        return true;
    }

//  public static void console(Banking obj) throws IOException{
//    System.out.println("---------------------------------");
//    System.out.println("              LOGIN/SIGNUP");
//    System.out.println("---------------------------------");
//    System.out.println("Enter 1 for login");
//    System.out.println("Enter 2 for Signup");
//    BufferedReader b = new BufferedReader(new InputStreamReader(system.in));
//    int choice = b.read();
//    if(choice == 1) {
//      System.out.println("Enter Username");
//      String name = b.readLine();
//      System.out.println("Enter Password");
//      String pass = b.readLine();
//      boolean flag = obj.login(name,pass);
//    }
//    if(choice == 2) {
//      System.out.println("Enter the following");
//      System.out.println("Name :");
//      String name = b.readLine();
//
//      System.out.println("Phone number:");
//      String phone_no = b.readLine();
//
//      System.out.println("Email:");
//      String email = b.readLine();
//
//      System.out.println("Address:");
//      String addr = b.readLine();
//
//      System.out.println("Social Security Number:");
//      String ssn = b.readLine();
//
//      System.out.println("Date of Birth:");
//      String name = b.readLine();
//
//      System.out.println("PAN:");
//      String name = b.readLine();
//
//      System.out.println("Enter Username");
//      String name = b.readLine();
//      
//      System.out.println("Enter Password");
//      String pass = b.readLine();
//      
//      obj.signup(name, phone_no, email, add)
//    }
//  }
}
