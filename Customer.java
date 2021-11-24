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

   public String getSSn() {
      return this.ssn;
   }

   public String getDob() {
      return this.dob;
   }

   public String getPan() {
      return this.pan;
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

   public void setName(String name) {
      this.name = name;
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

   public void setSsn(String ssn) {
      this.ssn = ssn;
   }

   public void setDob(String dob) {
      this.dob = dob;
   }

   public void setPan(String pan) {
      this.pan = pan;
   }

   public void setAcc_no(String acc_no) {
      this.acc_no = acc_no;
   }

   public void setAc_open_date(String ac_open_date) {
      this.ac_open_date = ac_open_date;
   }

   public void setHome_br(String home_br) {
      this.home_br = home_br;
   }
}
