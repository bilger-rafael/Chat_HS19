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
	private Button loginButton;
	private VBox centerBox;
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
		loginButton = new Button ();
		centerBox.setSpacing(10);
		
		
		nameField.setPrefWidth(200);
		pwField.setPrefWidth(200);
		loginButton.setPrefWidth(80);
		
		centerBox.getChildren().addAll(nameLabel, nameField, pwLabel, pwField, loginButton);
		
		
		
		
		
		//Borderpane anordnen
		root.setTop(headMenu);
		root.setCenter(centerBox);
		
		loginButton.setAlignment(Pos.BASELINE_CENTER);

	    

	   
        
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
	                   
           stage.setTitle(t.getString("program.name"));
	    }
}
