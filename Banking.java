import java.sql.*;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.io.*;

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

        query = "SELECT * FROM credentials WHERE username='" + username + "' AND password='" + password + "';";
        // The above string is the query to check if the database contains credentials entered by the user
        Statement st = conn.createStatement(); // Creation of the statement to execute the query
        login_cred = st.executeQuery(query); // Query is executed and result set is obtained

        if (!login_cred.isBeforeFirst()) {
            return false;
        } else {
            login_cred.next();
            acc_no = login_cred.getString("acc_no"); // Get account number from the result set
            query = "SELECT * FROM customer_details WHERE acc_no='" + acc_no + "';";
            ResultSet tmp = st.executeQuery(query);
            c = new Customer(tmp);
            return true;
        }
    }

    public void signup(String tname, String tphno, String temail, String taddr, String tssn, String tdob, String tpan, String tusername, String tpass) throws SQLException {
        String tacno, thbr, query;
        PreparedStatement pstmt;

        long tacno_ = (long) Math.floor(Math.random() * 900_000_000L) + 1_000_000_000L;

        tacno = String.valueOf(tacno_);

        java.sql.Date tacopdt = new java.sql.Date(new java.util.Date().getTime());
        thbr = "Kudlu Gate";

        Statement st = conn.createStatement();

        query = "INSERT INTO `banking`(`acc_no`, `name`, `phone_no`, `acc_balance`, `acc_open_date`, `home_branch`) VALUES (?,?,?," + 10000.00 + ",?,?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, tacno);
        pstmt.setString(2, tname);
        pstmt.setString(3, tphno);
        pstmt.setDate(4, tacopdt);
        pstmt.setString(5, thbr);
        pstmt.execute();

        query = "INSERT INTO `customer_details`(`name`, `phone_no`, `email`, `address`, `social_security_no`, `date_of_birth`, `pan`, `acc_no`) VALUES (?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, tname);
        pstmt.setString(2, tphno);
        pstmt.setString(3, temail);
        pstmt.setString(4, taddr);
        pstmt.setString(5, tssn);
        pstmt.setDate(6, Date.valueOf(tdob));
        pstmt.setString(7, tpan);
        pstmt.setString(8, tacno);
        pstmt.execute();

        query = "INSERT INTO `credentials`(`username`, `password`, `acc_no`) VALUES (?,?,?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, tpass);
        pstmt.setString(2, tusername);
        pstmt.setString(3, tacno);
        pstmt.execute();
    }

    public boolean Transact(String tr_amt, String benf_name, String benf_acc_no) throws SQLException {
        Statement st = conn.createStatement();
        String query, tr_date, pattern;

        ResultSet r = st.executeQuery("SELECT * FROM banking WHERE acc_no=`" + benf_acc_no + "`;");
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

    public void DispTransactions() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet r;
        String query;
        //counting no of records in transactions table
        r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM transactions WHERE from_acc_no=`" + c.acc_no + "` OR to_acc_no=`" + c.acc_no + "`;");
        r.next();
        int no_tr = r.getInt("rowcount");

        query = "SELECT * FROM transactions WHERE from_acc_no=`" + c.acc_no + "` OR to_acc_no=`" + c.acc_no + "`;";
        r = st.executeQuery(query);

        System.out.println("|   From Account    |    To Account    |   Beneficiary name   |  Transaction ID  |  Transaction Amount | Date |");
        for (int i = 0; i < no_tr; i++) {
            r.next();
            System.out.println("|"+r.getString("from_acc_no")+"|"+r.getString("to_acc_no")+"|"+r.getString("benf_name")+"|"+r.getString("tr_id")+"|"+r.getString("tr_amt")+"|"+r.getString("tr_date")+"|");
        }
    }

    public boolean ChangePassword(String old_pass, String new_pass) throws SQLException {
        boolean flag;
        Statement s = conn.createStatement();
        String query;

        query = "SELECT * FROM credentials WHERE password=`" + old_pass + "`;";
        ResultSet r = s.executeQuery(query);
        if (!r.isBeforeFirst()) {
            return false;
        }

        query = "UPDATE TABLE credentials SET password =`" + new_pass + "` WHERE acc_no=`" + c.acc_no + "` AND password=`" + old_pass + "`;";
        s.executeUpdate(query);
        return true;
    }

    public static void console(Banking obj) throws IOException, SQLException {
     System.out.println("---------------------------------");
     System.out.println("              LOGIN/SIGNUP");
     System.out.println("---------------------------------\n");
     System.out.println("Enter 1 for login");
     System.out.println("Enter 2 for Signup");
     BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
     int choice = Integer.parseInt(b.readLine());
     if(choice == 1) {
        boolean flag; 
        do {
           System.out.println("Enter Username");
           String name = b.readLine();
           System.out.println("Enter Password");
           String pass = b.readLine();
           flag = obj.login(name,pass);
           if(!flag){
            System.out.println("Incorrect username/password!\n");
        }
    }while(!flag);

    boolean temp = true;
    while(temp == true) {

        System.out.println("-----------------------------");
        System.out.println("           Main Menu");
        System.out.println("-----------------------------\n");
        System.out.println("1. Check Balance");
        System.out.println("2. Transfer fund");
        System.out.println("3. Display Customer details");
        System.out.println("4. Display transactions");
        System.out.println("5. Change password");
        System.out.println("6. Logout");
        System.out.println("Enter choice : ");
        choice = Integer.parseInt(b.readLine());
        switch(choice) {
          case 1:
          obj.c.CheckBalance();
          break;
          case 2:
          System.out.println("-----------------------------");
          System.out.println("           Transfer Fund");
          System.out.println("-----------------------------\n");
          System.out.print("Enter Beneficiary name : ");
          String s = b.readLine();
          System.out.print("Enter Transfer Amount : ");
          String amt = b.readLine();
          System.out.print("Enter Beneficiary Account number : ");
          String ac = b.readLine();
          boolean result = obj.Transact(amt,s,ac);
          if(!result) {
              System.out.println("Beneficiary Account number does not exist");
          }
          break;
          case 3:
          obj.c.DispDetails();
          break;
          case 4:
          obj.DispTransactions();
          break;
          case 5:
          boolean f; 
          do {
            System.out.println("Enter old password");
            String old = b.readLine();
            System.out.println("Enter new password");
            String nu = b.readLine();
            f = obj.ChangePassword(old,nu);
            if(!f) {
                System.out.println("Incorrect old password!\n");
            }
        }while(!f);
        break;
        case 6:
        temp = false;
        break;
        default:
        System.out.println("Invalid choice");
        break;
    }
}
}
if(choice == 2) {
   System.out.println("Enter the following");
   System.out.println("Name :");
   String name = b.readLine();

   System.out.println("Phone number:");
   String phone_no = b.readLine();

   System.out.println("Email:");
   String email = b.readLine();

   System.out.println("Address:");
   String addr = b.readLine();

   System.out.println("Social Security Number:");
   String ssn = b.readLine();

   System.out.println("Date of Birth:");
   String dob = b.readLine();

   System.out.println("PAN:");
   String pan = b.readLine();

   System.out.println("Enter Username");
   String username = b.readLine();

   System.out.println("Enter Password");
   String pass = b.readLine();

   obj.signup(name, phone_no, email, addr, ssn, dob, pan, username, pass);
}
}
    public static void main(String[] args) throws IOException, SQLException {
        Banking obj = new Banking();
        // console(obj);
        obj.login("kp", "kp");
        obj.ChangePassword("kp","secret");
    }
}
