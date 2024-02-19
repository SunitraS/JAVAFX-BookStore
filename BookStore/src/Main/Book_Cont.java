package Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Book_Cont implements Initializable {
	@FXML
    private TableColumn<Book, String> col_bn;

    @FXML
    private TableColumn<Book, String> col_no;

    @FXML
    private Label lb_au;

    @FXML
    private Label lb_bn;

    @FXML
    private Label lb_no;

    @FXML
    private Label lb_pr;
    
    @FXML
    private TextField lb_qu;

    @FXML
    private TableView<Book> table_book;

    @FXML
    private TextField txt_src;
    
    @FXML
    private TableView<Cart> table_cart;
    
    private String username;

    @FXML
    void AddtoCart(ActionEvent event) {
    	if (table_book.getSelectionModel().getSelectedItem() != null) {
			Book selectedBook = table_book.getSelectionModel().getSelectedItem();
			if (saveToCart(selectedBook)) {
				Alert alert = new Alert(AlertType.NONE, selectedBook.getBookName() +" Added Successfully!", ButtonType.OK);
				alert.showAndWait();
				return;
			} else {
				Alert alert = new Alert(AlertType.NONE, "Book Adding Failed!", ButtonType.OK);
				alert.showAndWait();
				return;
			}
		}
		else {
			Alert alert = new Alert(AlertType.NONE, "Please Select Book To Add!", ButtonType.OK);
			alert.showAndWait();
			return;
		}
    }
    
    private boolean saveToCart(Book cart) {

		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction(); 
			
			Cart c = new Cart(lb_no.getText(),lb_bn.getText(),(lb_qu.getText()),(lb_pr.getText())); 
			session.save(c);

			List<Cart> cList = session.createQuery("from Cart").list();
    		ObservableList<Cart> CartList = FXCollections.observableArrayList(cList);
    		session.getTransaction().commit(); 
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		return true;
	}
    
    @FXML
    void Search(ActionEvent event) {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		List<Book> List = session.createQuery("from Book").list();
		ObservableList<Book> bList = FXCollections.observableArrayList(List);
		table_book.setItems(bList);
		
		FilteredList<Book> filData = new FilteredList<>(bList,b->true);
		txt_src.textProperty().addListener((observable,oldValue,newValue)->{
			filData.setPredicate(Book->{
				if(newValue.isEmpty()|| newValue.isBlank()||newValue==null) {
					return true;
				}
				
				String bk = newValue.toLowerCase();
				
				if(Book.getNo().toLowerCase().indexOf(bk)!= -1) {
					return true;
				}
				else if(Book.getBookName().toLowerCase().indexOf(bk)!= -1) {
					return true;
				}
				else if(Book.getAuthor().toLowerCase().indexOf(bk)!= -1) {
					return true;
				}
				else
					return false;
			});
		});
		
		SortedList<Book> sortedData = new SortedList<>(filData);
		sortedData.comparatorProperty().bind(table_book.comparatorProperty());
		table_book.setItems(sortedData);
    }

    @FXML
    void viewCart(MouseEvent event) throws IOException {
    	FXMLLoader shoppingCartPage = new FXMLLoader(getClass().getResource("Cart_list.fxml"));
		Parent shoppingCartParent = (Parent) shoppingCartPage.load();
		Scene shoppingCartScene = new Scene(shoppingCartParent);
		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		window.setScene(shoppingCartScene);
		window.show();
    }
    
    public void showBookDetails(Book book) {
		lb_no.setText(book.getNo());
		lb_bn.setText(book.getBookName());
		lb_au.setText(book.getAuthor());
		lb_pr.setText("" + book.getPrice());
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction(); 
		
		col_no.setCellValueFactory(new PropertyValueFactory<>("No"));
		col_bn.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		
		List<Book> BList = session.createQuery("from Book").list();
		ObservableList<Book> BookList = FXCollections.observableArrayList(BList);
		session.getTransaction().commit();
		table_book.setItems(BookList);
		
		table_book.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Book selectedBook = table_book.getSelectionModel().getSelectedItem();
				showBookDetails(selectedBook);
			}
		});
	}

}
