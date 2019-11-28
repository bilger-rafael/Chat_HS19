package chat.LoginClasses;

import java.util.Locale;
import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends View<LoginModel> {
	
	private LoginModel model;
	private BorderPane root;
	private MenuBar headMenu;
	private Menu menuFile, menuEdit, menuLanguage, menuHelp;
	private MenuItem closeMenuItem;
	private PasswordField pwField;
	private TextField nameField;
	private Button loginButton, createUserButton;
	private VBox centerBox;
	private HBox BottonBox;
	private Label nameLabel, pwLabel;

	public LoginView(Stage stage, LoginModel model) {
        super(stage, model);
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
        
        
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
		
		this.root = new BorderPane();
		
		//Top Menuleiste
		headMenu = new MenuBar();
		
		menuFile = new Menu();
		closeMenuItem = new MenuItem();
		
		
		menuFile.getItems().add(closeMenuItem);
		
		menuEdit = new Menu();
		
		menuHelp = new Menu();
		
		menuLanguage = new Menu();

		menuLanguage.getItems().addAll();
		
		
		
		
		//Locale setzen
	       for (Locale locale : sl.getLocales()) {
	           MenuItem language = new MenuItem(locale.getLanguage());
	           this.menuLanguage.getItems().add(language);
	           language.setOnAction( event -> {
					sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
	                sl.setTranslator(new Translator(locale.getLanguage()));
	                updateTexts();
	            });
	        }
		
	    headMenu.getMenus().addAll(menuFile, menuEdit, menuLanguage, menuHelp);
		
		
		
		
		//Center 
		centerBox = new VBox();
		nameLabel = new Label ();
		nameField = new TextField();
		pwLabel = new Label ();
		pwField = new PasswordField();
		
		//Botton HBox
		loginButton = new Button ();
		createUserButton = new Button();
		BottonBox = new HBox();
		BottonBox.getChildren().addAll(createUserButton, loginButton);
		
		BottonBox.setSpacing(120);
		centerBox.setSpacing(10);
		
		loginButton.setAlignment(Pos.BASELINE_CENTER);
		createUserButton.setAlignment(Pos.BASELINE_CENTER);
		
		nameField.setPrefWidth(250);
		pwField.setPrefWidth(250);
		loginButton.setPrefWidth(100);
		createUserButton.setPrefWidth(100);
		
		centerBox.getChildren().addAll(nameLabel, nameField, pwLabel, pwField, BottonBox);
		
		
		
		
		
		//Borderpane anordnen
		root.setTop(headMenu);
		root.setCenter(centerBox);
		
		

	    

	   
        
        updateTexts();
		
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("app.css").toExternalForm());
        return scene;
	}
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	        
	        // The menu entries
	       menuFile.setText(t.getString("program.menu.file"));
	       menuLanguage.setText(t.getString("program.menu.file.language"));
           menuHelp.setText(t.getString("program.menu.help"));
           closeMenuItem.setText(t.getString("program.menu.file.close"));
           menuEdit.setText(t.getString("program.menu.file.edit"));
           nameLabel.setText(t.getString("programm.login.nameLabel"));
           pwLabel.setText(t.getString("Programm.login.pwLabel"));
           loginButton.setText(t.getString("Programm.login.loginButton"));
           createUserButton.setText(t.getString("Programm.login.createUserButton"));
	                   
           stage.setTitle(t.getString("program.name"));
	    }
	   
		public Button getLoginButton() {
			return loginButton;
		}
	   
}
