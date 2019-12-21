package chat.ChatRoomClasses;

import java.util.Locale;
import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChatRoomView extends View<ChatRoomModel> {

	private ChatRoomModel model;
	private BorderPane root, botton;
	private MenuBar headMenu;
	private Menu menuFile, menuEdit, menuLanguage, menuHelp;
	private MenuItem closeMenuItem, logoutMenuItem, changePWMenuItem;
	private Label myAccountLabel;
	private Button joinButton, newChatButton, refreshButton;

	// Liste muss dann ChatroomElemente sein
	protected ListView<String> chatRoomCenter;

	public ChatRoomView(Stage stage, ChatRoomModel model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();

		this.root = new BorderPane();

		// Top mein Name
		myAccountLabel = new Label();

		// Top Menuleiste
		headMenu = new MenuBar();
		menuFile = new Menu();
		closeMenuItem = new MenuItem();
		logoutMenuItem = new MenuItem();
		menuFile.getItems().addAll(closeMenuItem, logoutMenuItem);
		menuEdit = new Menu();
		changePWMenuItem = new MenuItem();
		menuEdit.getItems().addAll(changePWMenuItem);
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
		chatRoomCenter = new ListView<String>();
		chatRoomCenter.setPrefWidth(500);

		// Botton
		joinButton = new Button();
		refreshButton = new Button();
		newChatButton = new Button();
		botton = new BorderPane();

		joinButton.setPrefWidth(150);
		getRefreshButton().setPrefWidth(150);
		newChatButton.setPrefWidth(150);

		botton.setLeft(newChatButton);
		botton.setCenter(getRefreshButton());
		botton.setRight(joinButton);

		// Borderpane anordnen
		root.setTop(headMenu);
		root.setBottom(botton);
		root.setCenter(chatRoomCenter);

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
		logoutMenuItem.setText(t.getString("program.menu.file.logout"));
		menuEdit.setText(t.getString("program.menu.file.edit"));
		changePWMenuItem.setText(t.getString("program.menu.file.edit.ChatRoom.PWChange"));
		myAccountLabel.setText(t.getString("program.chatRoom.myAccount"));
		joinButton.setText(t.getString("program.chatRoom.join"));
		newChatButton.setText(t.getString("program.chatRoom.newChat"));
		getRefreshButton().setText(t.getString("program.chatRoom.refresh"));

		stage.setTitle(t.getString("program.name"));
	}

	public MenuItem getLogoutMenuItem() {
		return logoutMenuItem;
	}

	public Button getNewChat() {
		return newChatButton;
	}

	public MenuItem getChangePWMenuItem() {
		return changePWMenuItem;
	}

	public Button getRefreshButton() {
		return refreshButton;
	}
}
