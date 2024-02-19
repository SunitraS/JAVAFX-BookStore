package Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import JavaClass.Book;
import JavaClass.Cart;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewCart_Cntr implements Initializable{

    @FXML
    private TableColumn<?, ?> col_bn;

    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_pr;

    @FXML
    private TableColumn<?, ?> col_qu;

    @FXML
    private TableColumn<?, ?> cul_bnum;
    
    @FXML
    private TableView<Cart> table_cart;
    
    @FXML
    void DelData(ActionEvent event) throws IOException {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
    	
    	Cart c = table_cart.getSelectionModel().getSelectedItem();
    	if ( table_cart.getSelectionModel().getSelectedItem() != null) {
    		session.delete(c);
    		
			Alert alert = new Alert(AlertType.NONE, "Removed Successfully!", ButtonType.OK);
			alert.showAndWait();
			
			List<Cart> pList = session.createQuery("from Cart").list();
			ObservableList<Cart> PersList = FXCollections.observableArrayList(pList);
			table_cart.setItems(FXCollections.observableArrayList(PersList));
			session.getTransaction().commit();
			ref();
    	}
		else if (event == null) {
			Alert alert = new Alert(AlertType.NONE, "Removal Failed!", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		else {
			Alert alert = new Alert(AlertType.NONE, "Please select a book for removal!", ButtonType.OK);
			alert.showAndWait();
			return;
		}
    }

    @FXML
    void Submit(ActionEvent event) throws IOException {
    	FXMLLoader shoppingCartPage = new FXMLLoader(getClass().getResource("Payment.fxml"));
		Parent shoppingCartParent = (Parent) shoppingCartPage.load();
		Scene shoppingCartScene = new Scene(shoppingCartParent);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(shoppingCartScene);
		window.show();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
    	Parent Registration = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene RegistrationScene = new Scene(Registration);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(RegistrationScene);
		window.show();
    }
   
   	public void ref() {
   		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
   		col_no.setCellValueFactory(new PropertyValueFactory<>("No"));
		cul_bnum.setCellValueFactory(new PropertyValueFactory<>("bookNo"));
		col_bn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		col_qu.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		col_pr.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		List<Cart> cList = session.createQuery("from Cart").list();
		ObservableList<Cart> CartList = FXCollections.observableArrayList(cList);
		session.getTransaction().commit(); 
		table_cart.setItems(CartList);
   }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ref();
		//Cal();
	}

}
