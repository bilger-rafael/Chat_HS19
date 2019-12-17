package chat.NewUserClasses;

import java.net.Socket;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.ChatClasses.ChatModel;
import chat.ChatClasses.ChatView;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.LoginClasses.LoginController;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;
import chat.message.CreateLogin;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class NewUserController extends Controller {
	ServiceLocator serviceLocator;
	String name;
	String pw;
	Socket socket;

	
    public NewUserController(NewUserModel model, NewUserView view) {
        super(model, view);
        
        //Action für CreateUserButton
        view.getCancelButton().setOnAction( e -> getBackLoginView());
        
        //Action für CreateUserButton
        view.getOkButton().setOnAction( e -> createUserAndBackLoginView());
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
          
        });
        
      // Validierung der Passwort-Eingabe
		view.getPwField().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				view.getOkButton().setDisable(true);

				int i = view.getPwField().getText().length();

				if( i < 3 ) {
					view.getPwField().getStyleClass().remove("pwField-green");
					view.getPwField().getStyleClass().add("pwField-red");
				}else {
					view.getPwField().getStyleClass().remove("pwField-red");
					view.getPwField().getStyleClass().add("pwField-green");
					view.getOkButton().setDisable(false);
				}
			}
		});
		
		view.getNameField().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				view.getOkButton().setDisable(true);

				int i = view.getNameField().getText().length();

				if( i < 3 ) {
					view.getNameField().getStyleClass().remove("nameField-green");
					view.getNameField().getStyleClass().add("nameField-red");
				}else {
					view.getNameField().getStyleClass().remove("nameField-red");
					view.getNameField().getStyleClass().add("nameField-green");
					view.getOkButton().setDisable(false);
				}
			}
		});
        
        this.name = view.getNameField().getText();
        this.pw = view.getPwField().getText();
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }
    
    //Leitet zur LoginView
    private void getBackLoginView() {
    	//Logik für zurück auf LoginView
    	view.stop();
    	JavaFX_App_Template.getMainProgram().getLoginView().start();
		
    }
    //Erstellt User beim Server und leitet zur LoginView
    private void createUserAndBackLoginView() {
    	//TODO Anfrage an Server senden und User empfangen
    	String[] data = new String[2];
    	data[1]= this.name;
    	data[2]=this.pw;
    	CreateLogin createLogin = new CreateLogin(data);
    	
    	//TODO Socketverbidnungmachen
    	//createLogin.send(socket);
    	/*
    	if(createLogin.receive(socket)) {
        	//Logik für zurück auf LoginView
        	
        	*/
        	view.stop();
        	JavaFX_App_Template.getMainProgram().getLoginView().setConnectedLabel();
        	JavaFX_App_Template.getMainProgram().getLoginView().start();
    	/*}
    	else{
    		//TODO Try 
    	};
    	*/
    	

    }
}
