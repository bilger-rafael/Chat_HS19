package chat.ChatRoomCreateClasses;

import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChatRoomCreatView extends View<ChatRoomCreatModel> {

	private BorderPane root;
	private TextField nameField;
	private Button createButton, backButton;
	private VBox centerBox;
	private HBox checkBoxArea;
	private BorderPane bottonBox;
	private Label nameLabel, isPublic;
	private CheckBox isPublicCheckBox;
	private Label message;
	private FadeTransition transition;

	public ChatRoomCreatView(Stage stage, ChatRoomCreatModel model) {
		super(stage, model);

		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();

		this.root = new BorderPane();

		//aktuelle Sprace laden
		sl.getTranslator().getCurrentLocale();

		// Center
		centerBox = new VBox();
		nameLabel = new Label();
		nameField = new TextField();

		// CheckboxArea
		isPublicCheckBox = new CheckBox();
		isPublicCheckBox.setSelected(true);
		isPublic = new Label();
		checkBoxArea = new HBox();
		checkBoxArea.getChildren().addAll(getCheckBox(), isPublic);

		// Botton BorderPane
		createButton = new Button();
		backButton = new Button();

		bottonBox = new BorderPane();

		bottonBox.setLeft(getBackButton());
		bottonBox.setRight(getCreateButton());

		centerBox.setSpacing(10);

		getCreateButton().setAlignment(Pos.BASELINE_CENTER);
		getBackButton().setAlignment(Pos.BASELINE_CENTER);

		getNameField().setPrefWidth(250);
		getCreateButton().setPrefWidth(150);
		getBackButton().setPrefWidth(150);

		centerBox.getChildren().addAll(nameLabel, getNameField(), checkBoxArea, bottonBox);

		//Nachricht, falls Login fehlgeschlagen
		message = new Label("");
		message.setId("message");
		message.setOpacity(0);
		
		// Borderpane anordnen
		root.setCenter(centerBox);
		root.setBottom(message);

		updateTexts();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		// The menu entries

		nameLabel.setText(t.getString("programm.ChatRoomCreat.nameLabel"));
		getCreateButton().setText(t.getString("Programm.ChatRoomCreat.createButton"));
		getBackButton().setText(t.getString("Programm.ChatRoomCreat.backButton"));
		isPublic.setText(t.getString("Programm.ChatRoomCreat.isPublic"));
		message.setText(t.getString("Programm.ChatRoomCreate.message"));

		stage.setTitle(t.getString("program.name"));
	}
	
	protected void showError() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		message.setText(t.getString("Programm.ChatRoomCreate.message"));
		
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


	public TextField getNameField() {
		return nameField;
	}

	public CheckBox getCheckBox() {
		return isPublicCheckBox;
	}

	public Button getCreateButton() {
		return createButton;
	}

	public Button getBackButton() {
		return backButton;
	}

}
