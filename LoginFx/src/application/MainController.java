package application;






import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Label lblSignup;
	
	@FXML
	private TextField txtFirstname;
	
	@FXML
	private TextField txtLastname ;
	
	@FXML
	private DatePicker txtDOB;
	
	@FXML
	private TextField txtPan;
	
	@FXML
	private TextField txtSSN;
	
	@FXML
	private TextField txtemail;
	
	@FXML
	private TextField txtPhn;
	
	@FXML
	private TextField txtAltPhn;
	
	@FXML
	private TextArea txtAddress;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtpassword;
	
	
	
	
	
	public void Signup(ActionEvent event)throws Exception {
		if(txtFirstname.getText().equals("First") && txtLastname.getText().equals("last")&& 
				 txtPan.getText().equals("Pan") &&txtSSN.getText().equals("12S12") && 
				txtemail.getText().equals("abc@gmail.com")&& 
				txtPhn.getText().equals("1234567891") && txtAltPhn.getText().equals("1234567689")&&
				txtAddress.getText().equals("abcdefg") && txtUsername.getText().equals("user")&&txtpassword.getText().equals("pass")) {
							
							
									lblSignup.setText("Signup Success");
									Stage primaryStage=new Stage();
			
									Parent root=FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
									Scene scene = new Scene(root,400,400);
									scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
									primaryStage.setScene(scene);
									primaryStage.show();
			
								}
		
	
		else {
			lblSignup.setText("Signup Failed");
		}
	
	
	}
}
