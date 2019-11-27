package chat.ChatClasses;

import java.util.Locale;
import java.util.logging.Logger;

import chat.ServiceLocator;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.abstractClasses.View;
import chat.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatView extends View<ChatModel> {
	
	private ChatRoomModel model;
	private BorderPane root;
	private MenuBar headMenu;
	private Menu menuFile, menuEdit, menuLanguage, menuHelp;
	private MenuItem closeMenuItem;
	private TextField entryTextField;	
	private HBox botton;
	private Button sendButton, clearButton;

	//Liste muss dann ChatroomElemente sein
	protected ListView<String> chatCenter;

	public ChatView(Stage stage, ChatModel model) {
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
		chatCenter = new ListView<String>();
		
		
		//Botton 
		botton = new HBox();
		entryTextField = new TextField();
		sendButton = new Button("send");
		clearButton = new Button ("clear");
		
		
		entryTextField.setPrefWidth(500);
		sendButton.setPrefWidth(80);
		clearButton.setPrefWidth(80);
		
		botton.getChildren().addAll(entryTextField, sendButton, clearButton);
		
		
		
		//Borderpane anordnen
		root.setTop(headMenu);
		root.setBottom(botton);
		root.setCenter(chatCenter);
		


	    

	   
        
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
           entryTextField.setText(t.getString("program.chat.entry"));
           sendButton.setText(t.getString("program.chat.send"));
           clearButton.setText(t.getString("program.chat.clear"));
           
           
	                   
           stage.setTitle(t.getString("program.name"));
	    }
}
