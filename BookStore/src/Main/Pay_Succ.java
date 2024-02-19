package Main;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import JavaClass.Book;
import JavaClass.Cart;
import JavaClass.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Pay_Succ implements Initializable {

    @FXML
    private Label txt_am;

    @FXML
    private Label txt_fn;

    @FXML
    private Label txt_ln;

    private int totalPrice = 0;
    
    @FXML
    void Close(ActionEvent event) throws IOException {
		FXMLLoader shoppingCartPage = new FXMLLoader(getClass().getResource("Success.fxml"));
		Parent shoppingCartParent = (Parent) shoppingCartPage.load();
		Scene shoppingCartScene = new Scene(shoppingCartParent);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(shoppingCartScene);
		window.show();
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.createSQLQuery("truncate table cart_list").executeUpdate();
		session.getTransaction().commit();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		List<User> uList = session.createQuery("from User").list();
		for(User i:uList) {
			txt_fn.setText(i.getFirstName());
			txt_ln.setText(i.getLastName());
		}
		
		SessionFactory fact = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session s = fact.getCurrentSession();
		s.beginTransaction();
		
		List<Cart> cList = s.createQuery("from Cart").list();
		for(Cart i:cList) {
			totalPrice += i.getTotal();
			txt_am.setText(String.valueOf(totalPrice));
		}
	}
}
