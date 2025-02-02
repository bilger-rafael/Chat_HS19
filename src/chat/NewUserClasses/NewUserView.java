package chat.NewUserClasses;

import java.util.Locale;
import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewUserView extends View<NewUserModel> {
	
	private NewUserModel model;
	private BorderPane root;
	private MenuBar headMenu;
	private Menu menuFile, menuEdit, menuLanguage, menuHelp;
	private MenuItem closeMenuItem;
	private PasswordField pwField;
	private TextField nameField;
	private Button okButton, cancelButton;
	private VBox centerBox;
	private BorderPane bottonBox;
	private Label nameLabel, pwLabel, errorLabel;
	private FadeTransition transition;

	public NewUserView(Stage stage, NewUserModel model) {
        super(stage, model);
        stage.setTitle("Chat Bilger-Etter");
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
		okButton = new Button ();
		cancelButton = new Button();
		bottonBox = new BorderPane();

		
		bottonBox.setLeft(cancelButton);
		bottonBox.setRight(okButton);
		
		centerBox.setSpacing(10);
		
		cancelButton.setAlignment(Pos.BASELINE_CENTER);
		okButton.setAlignment(Pos.BASELINE_CENTER);
		
		nameField.setPrefWidth(250);
		pwField.setPrefWidth(250);
		cancelButton.setPrefWidth(100);
		okButton.setPrefWidth(100);
		
		centerBox.getChildren().addAll(nameLabel, nameField, pwLabel, pwField, bottonBox);
		
		//ErrorLabel falls User erstellen fehlgeschlagen
		errorLabel = new Label();
		errorLabel.setId("errorLabel");
		errorLabel.setOpacity(0);

		
		
		//Borderpane anordnen
		root.setTop(headMenu);
		root.setCenter(centerBox);
		root.setBottom(errorLabel);
		

        
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
           nameLabel.setText(t.getString("programm.newUser.nameLabel"));
           pwLabel.setText(t.getString("Programm.newUser.pwLabel"));
           cancelButton.setText(t.getString("Programm.newUser.cancelButton"));
           okButton.setText(t.getString("Programm.newUser.okButton"));
           errorLabel.setText(t.getString("Programm.newUser.errorLabel"));
	                   
           stage.setTitle(t.getString("program.name"));
	    }
	   
		public Button getCancelButton() {
			return cancelButton;
		}
	   
		public Button getOkButton() {
			return okButton;
		}
		public PasswordField getPwField() {
			return pwField;
		}
		
		public void setPwField(String s) {
			pwField.setText(s);;
		}
		
		public TextField getNameField() {
			return nameField;
		}
		
		public void showError() {
			Translator t = ServiceLocator.getServiceLocator().getTranslator();
			errorLabel.setText(t.getString("Programm.newUser.errorLabel"));
			
			if( transition == null ) {
				transition = new FadeTransition(Duration.millis(2000), errorLabel);
				transition.setFromValue(1.0);
				transition.setToValue(0);
				transition.setDelay(Duration.millis(2000));
			}
			
			transition.stop();
			errorLabel.setOpacity(1);
			transition.play();
		}
		public void resetPwField() {
			pwField.setText("");
		}
		
		public void resetNameField() {
			nameField.setText("");
		}

}
