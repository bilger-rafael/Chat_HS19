package chat.LoginClasses;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private BorderPane bottonBox;
	private Label nameLabel, pwLabel, connectedLabel;
	private Label message;
	private FadeTransition transition;
	private HBox messageBox;

	public LoginView(Stage stage, LoginModel model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();

		this.root = new BorderPane();

		// Top Menuleiste
		headMenu = new MenuBar();

		menuFile = new Menu();
		closeMenuItem = new MenuItem();
		menuFile.getItems().add(closeMenuItem);
		menuEdit = new Menu();
		menuHelp = new Menu();
		menuLanguage = new Menu();
		menuLanguage.getItems().addAll();

		// Locale setzen
		for (Locale locale : sl.getLocales()) {
			MenuItem language = new MenuItem(locale.getLanguage());
			this.menuLanguage.getItems().add(language);
			language.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
				sl.setTranslator(new Translator(locale.getLanguage()));
				updateTexts();
			});
		}

		headMenu.getMenus().addAll(menuFile, menuEdit, menuLanguage, menuHelp);

		// Center
		centerBox = new VBox();
		nameLabel = new Label();
		nameField = new TextField();
		pwLabel = new Label();
		pwField = new PasswordField();

		// Botton HBox
		loginButton = new Button();
		createUserButton = new Button();

		bottonBox = new BorderPane();

		bottonBox.setLeft(createUserButton);
		bottonBox.setRight(loginButton);

		centerBox.setSpacing(10);

		loginButton.setAlignment(Pos.BASELINE_CENTER);
		createUserButton.setAlignment(Pos.BASELINE_CENTER);

		getNameField().setPrefWidth(250);
		getPwField().setPrefWidth(250);
		loginButton.setPrefWidth(100);
		createUserButton.setPrefWidth(100);

		centerBox.getChildren().addAll(nameLabel, getNameField(), pwLabel, getPwField(), bottonBox);

		messageBox = new HBox();
		connectedLabel = new Label();
		connectedLabel.setId("connectedLabel");
		connectedLabel.setOpacity(0);
		messageBox.getChildren().add(connectedLabel);

		
		//Nachricht, falls Login fehlgeschlagen
		message = new Label("");
		message.setId("message");
		message.setOpacity(0);
		messageBox.getChildren().add(message);
		
		
		// Borderpane anordnen
		root.setTop(headMenu);
		root.setCenter(centerBox);
		root.setBottom(messageBox);

		updateTexts();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
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
		message.setText(t.getString("Programm.login.message"));
		connectedLabel.setText(t.getString("Programm.login.connectedLabel"));
		stage.setTitle(t.getString("program.name"));
		
	}
	
	protected void showError() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		message.setText(t.getString("Programm.login.message"));
		
		if( transition == null ) {
			transition = new FadeTransition(Duration.millis(2000), message);
			transition.setFromValue(1.0);
			transition.setToValue(0);
			transition.setDelay(Duration.millis(2000));
		}
		
		transition.stop();
		message.setOpacity(1);
		transition.play();
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public Button getCreateUserButton() {
		return createUserButton;
	}

	public void setConnectedLabel() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		connectedLabel.setText(t.getString("Programm.login.connectedLabel"));
		
		if( transition == null ) {
			transition = new FadeTransition(Duration.millis(2000), connectedLabel);
			transition.setFromValue(1.0);
			transition.setToValue(0);
			transition.setDelay(Duration.millis(2000));
		}
		
		transition.stop();
		connectedLabel.setOpacity(1);
		transition.play();
	}

	public TextField getNameField() {
		return nameField;
	}

	public PasswordField getPwField() {
		return pwField;
	}
}
