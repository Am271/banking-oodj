public class Customer extends Person {
   private String name;
   private String phone_no;
   private String email;
   private String address;
   private String ssn;
   private String dob;
   private String pan;
   private String acc_no;
   private String ac_open_date;
   private String home_br;

   public Customer(String name,
   String phone_no,
   String email,
   String address,
   String ssn,
   String dob,
   String pan,
   String acc_no,
   String ac_open_date,
   String home_br) {
      this.name= name;
      this.phone_no = phone_no;
      this.email = email;
      this.address = address;
      this.ssn = ssn;
      this.dob = dob;
      this.pan = pan;
      this.acc_no = acc_no;
      this.ac_open_date = ac_open_date;
      this.home_br = home_br;
   }

   public Customer(String name,
   String phone_no,
   String email,
   String address,
   String acc_no,
   String ac_open_date,
   String home_br) {
      this.name= name;
      this.phone_no = phone_no;
      this.email = email;
      this.address = address;
      this.acc_no = acc_no;
      this.ac_open_date = ac_open_date;
      this.home_br = home_br;
   }

   public String getName() {
      return this.name;
   }

   public String getPhone_no() {
      return this.phone_no;
   }
   
   public String getEmail() {
      return this.email;
   }

   public String getAddr() {
      return this.address;
   }

   public String getAcc_no() {
      return this.acc_no;
   }

   public String getAcc_open_date() {
      return this.acc_open_date;
   }

   public String getHome_br() {
      return this.home_br;
   }

   public void setPhone_no(String phone_no) {
      this.phone_no = phone_no;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setAddr(String address) {
      this.address = address;
   }
}
