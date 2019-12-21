package chat.ChatRoomCreateClasses;

import java.util.Locale;
import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatRoomCreatView extends View<ChatRoomCreatModel> {

	private BorderPane root;
	private TextField nameField;
	private Button createButton, backButton;
	private VBox centerBox;
	private HBox checkBoxArea;
	private BorderPane bottonBox;
	private Label nameLabel, isPublic, errorLabel;
	private CheckBox isPublicCheckBox;

	public ChatRoomCreatView(Stage stage, ChatRoomCreatModel model) {
		super(stage, model);

		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();

		this.root = new BorderPane();



		//akutelle Sprace laden
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
		getCreateButton().setPrefWidth(100);
		getBackButton().setPrefWidth(100);

		centerBox.getChildren().addAll(nameLabel, getNameField(), checkBoxArea, bottonBox);

		errorLabel = new Label();
		// Borderpane anordnen
		root.setCenter(centerBox);
		root.setBottom(errorLabel);

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

		stage.setTitle(t.getString("program.name"));
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
