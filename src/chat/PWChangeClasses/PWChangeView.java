package chat.PWChangeClasses;

import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PWChangeView  extends View<PWChangeModel> {



	private PasswordField pwField;
	private Button backButton, okButton;
	private Label pwLabel;
	private VBox root;
	private HBox bottonArea;
	private Region spacer;

	public PWChangeView(Stage stage, PWChangeModel model) {
		super(stage, model);
		stage.setTitle("Chat Bilger-Etter");
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();
		
		
		//akutelle Sprace laden
		sl.getTranslator().getCurrentLocale();
		
		this.root = new VBox();



		//Botton Bereich
		backButton = new Button();
		okButton = new Button();
		spacer= new Region();
		spacer.setMinWidth(60);
		bottonArea = new HBox();
		bottonArea.getChildren().addAll(backButton, spacer, okButton);
		
		//Zusammenführen
		pwLabel = new Label();
		pwField = new PasswordField();
		root.getChildren().addAll(pwLabel, pwField, bottonArea);
		

		updateTexts();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		// The menu entries

		pwLabel.setText(t.getString("Programm.PWChange.pwLabel"));
		okButton.setText(t.getString("Programm.PWChange.okButton"));
		backButton.setText(t.getString("Programm.PWChange.backButton"));

		stage.setTitle(t.getString("program.name"));
	}

	public Button getOkButton() {
		return okButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public PasswordField getPwField() {
		return pwField;
	}
	
	public void resetPWField() {
		pwField.setText("");
	}

}