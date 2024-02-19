package Main;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import JavaClass.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Login_Cont implements Initializable {

	@FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_user;

    @FXML
    void CreateAcc(ActionEvent event) throws IOException {
    	Parent Registration = FXMLLoader.load(getClass().getResource("Regist.fxml"));
		Scene RegistrationScene = new Scene(Registration);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(RegistrationScene);
		window.show();
    }

    @FXML
    void Login(ActionEvent event) throws IOException {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction(); 
		
		List<User> Us = Collections.emptyList();
		List<User> pList = session.createQuery("from User").list();
		pList = session.createQuery("from User s where s.UserName='" + txt_user.getText() + "'AND s.Pass='"+txt_pass.getText()+ "'").list();
		
		if(pList.equals(Us)) {
			Alert alert = new Alert(AlertType.NONE, "Login Failed!", ButtonType.YES);
    		alert.show();
		}
		else {
			Alert alert = new Alert(AlertType.NONE, "Login Suscess!", ButtonType.YES);
    		alert.show();
    		Parent Registration = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    		Scene RegistrationScene = new Scene(Registration);
    		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    		window.setScene(RegistrationScene);
    		window.show();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
