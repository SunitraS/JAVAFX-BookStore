package Main;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import JavaClass.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Regis_Cont {
	@FXML
    private TextField txt_cpw;

    @FXML
    private TextField txt_em;

    @FXML
    private TextField txt_fn;

    @FXML
    private TextField txt_ln;

    @FXML
    private TextField txt_pw;

    @FXML
    private TextField txt_us;

    private void showAlert(String string) {
    	Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    	errorAlert.setTitle("Form Error!");
    	errorAlert.show();
	}
    
    @FXML
    void Create(ActionEvent event) throws IOException {
    	if(txt_fn.getText().isEmpty() || txt_ln.getText().isEmpty() || txt_em.getText().isEmpty() || txt_us.getText().isEmpty() || txt_pw.getText().isEmpty() || txt_cpw.getText().isEmpty()) { 
            showAlert("Please enter your data");
            return;
        }
    	if (!txt_pw.getText().equals(txt_cpw.getText())) {
			showAlert("Your password and confirm password don't match");
	        return;
    	}
    	
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction(); 
		
		User p = new User(txt_fn.getText(),txt_ln.getText(), txt_em.getText(), txt_us.getText(), txt_pw.getText()); // จาก class Pers line 36 เหมือนเรียกใช้คลาสจาวาปกติ
		session.save(p);
		
		List<User> Us = Collections.emptyList();
		List<User> pList = session.createQuery("from User").list();
		ObservableList<User> PersList = FXCollections.observableArrayList(pList);
		session.getTransaction().commit();
		
		Alert alert = new Alert(AlertType.NONE, "Registration Succesful! Welcome to BookStore", ButtonType.OK);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			Parent Registration = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    		Scene RegistrationScene = new Scene(Registration);
    		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    		window.setScene(RegistrationScene);
    		window.show();
		}
    }
    
	@FXML
    void cancle(ActionEvent event) throws IOException {
    	Parent Registration = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene RegistrationScene = new Scene(Registration);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(RegistrationScene);
		window.show();
    }
}
