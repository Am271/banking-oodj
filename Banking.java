import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Banking {
  private String IFCS;
  private String branch_name;
  private Customer c;// Customer instance used by Bank
  private Transaction t;
  private Connection conn;

  public Banking() {
    try {
      Class.forName("com.mysql.cj.jconnc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql:///databasename","username","password");
    }
    catch (SQLException e) {
      System.out.println("SQL Exception error occurred.\n" + e);
    }
    catch (ClassNotFoundException e) {
      System.out.println("JDBC MySQL bridge failed to load... Jar file maybe missing.\n" + e);
    }
  }

  private void createCustInstance(long acc_no) {
      Statement customer = conn.createStatement(); // Creation of a new statement to retrieve user information
      String query = "SELECT * FROM customer_details WHERE acc_no=" + acc_no + ";"; // Statement to retrieve customer details from database
      ResultSet details;

      details = customer.executeQuery(query);

      // Code to retrieve data from Resultset and to create a new instance of customer class
  }

  private void fill(Customer obj) {
    Statement s = conn.createStatement();
    String query ="INSERT INTO customer_details VALUES("+obj.getName()+","+obj.getPhone_no()+","+obj.getEmail()+","+obj.getAddr()+","+obj.getSSn()
    +","+obj.getDob()+","+obj.getPan()+","+obj.getAcc_no()+","+obj.getAc_open_date()+","+obj.getHome_br()+");";
  }

  public void login() {
      // Check the database for user credentials
      String username = ; // Obtained from UI
      String password = ; // Obtained from UI
      String query; ResultSet login_cred; long acc_no;

      query = "SELECT * FROM credentials WHERE username=" + username + " AND password=" + password + ";";
      // The above string is the query to check if the database contains credentials entered by the user
      Statement st = conn.createStatement(); // Creation of the statement to execute the query
      login_cred = st.executeQuery(query); // Query is executed and result set is obtained
      acc_no = ; // Get account number from the result set

      // Code to check if the login credentials are correct

      // if login credentials are correct, call the following function
      fillDetails(acc_no); // This function will create an instance of the Customer object with the necessary details from the database
      // This is necessary because the user's balance and account details will have to be displayed to the user when necessary
  }

  public void signup() {
    String tname,tphno,temail,taddr,tssn,tdob,tpan,tacno,tacopdt,thbr;

    Customer tc;

    //code to obtain details from UI

    long tacno = (long) Math.floor(Math.random() * 900_000_000L) + 1_000_000_000L;

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    tacopdt = simpleDateFormat.format(new Date());

    thbr = "Kudlu Gate";

    tc = new Customer(tname,tphno,temail,taddr,tssn,tdob,tpan,tacno,tacopdt,thbr);
    fill(tc);
  }

  public static void main(String[] args) {
    Banking obj = new Banking();
  }
}
