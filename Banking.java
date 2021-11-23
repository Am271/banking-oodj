import java.sql.*;

public class Banking {
  private String IFCS;
  private String branch_name;
  private Customer c;// Customer instance used by Bank
  private Transaction t;
  private Connection db;

  public Banking() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      db = DriverManager.getConnection("jdbc:mysql:///databasename","username","password");
    }
    catch (SQLException e) {
      System.out.println("SQL Exception error occurred.\n" + e);
    }
    catch (ClassNotFoundException e) {
      System.out.println("JDBC MySQL bridge failed to load... Jar file maybe missing.\n" + e);
    }
  }

  public void login() {

  }
  public static void main(String[] args) {
    Banking obj = new Banking();
  }
}
