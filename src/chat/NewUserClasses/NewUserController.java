package chat.NewUserClasses;

import com.apple.laf.ClientPropertyApplicator.Property;
import com.sun.media.jfxmedia.logging.Logger;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.abstractClasses.Controller;
import chat.commonClasses.Client;
import chat.message.CreateLogin;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


public class NewUserController extends Controller<NewUserModel, NewUserView> {
	ServiceLocator serviceLocator;
	private int positionInArray = 0;


    public NewUserController(NewUserModel model, NewUserView view) {
        super(model, view);
        
        //Action für CreateUserButton
        view.getCancelButton().setOnAction( e -> getBackLoginView());
        
        //Action für CreateUserButton
        view.getOkButton().setOnAction( e -> createUser());
        
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
		
		//Validierung Namenseingabe
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
		
		//Schliesst Sitzung falls das Fenster geschlossen wird
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                view.stop();
                Platform.exit();
            }
        });
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }
    
    //Leitet zur LoginView nach Abbruch
    private void getBackLoginView() {
    	//Logik für zurück auf LoginView
    	this.view.stop();
    	JavaFX_App_Template.getMainProgram().getLoginView().start();
		
    }
    //Erstellt User beim Server und leitet zur LoginView
    private void createUser() {
    	String username = view.getNameField().getText();
    	String password = view.getPwField().getText();
    	
    	CreateLogin createLogin = new CreateLogin(username, password);
    	
    	
    	this.positionInArray = Client.getClient().addMsgListener((Message msg) -> {
    		if(msg instanceof Result) {
				Result r = (Result)msg;
				if( r.getType() == ResultType.Simple ) {
					if(r.getBoolean()) {
						serviceLocator.getLogger().info("erstellt");
						backLoginViewAfterLogin();
				
					}else {
						JavaFX_App_Template.getMainProgram().getNewUserView().setErrorLabel();
						serviceLocator.getLogger().info("User gibt es schon");
					}
					Client.getClient().removeMsgListener(Client.getClient().getMessageListener(positionInArray));
				}
			}
    	});
    	
    	Client.getClient().send(createLogin);
    	
    }
    
    //Leitet zur Loginview nach dem Login
    private void backLoginViewAfterLogin() {
    	
    	this.view.stop();
    	setConnectedInLoginView();
    	JavaFX_App_Template.getMainProgram().getLoginView().start();
    	
    	
    }
    
    //Setzt den Text Connected auf der Login View
    private void setConnectedInLoginView() {
    	JavaFX_App_Template.getMainProgram().getLoginView().setConnectedLabel();
    }
    

}
