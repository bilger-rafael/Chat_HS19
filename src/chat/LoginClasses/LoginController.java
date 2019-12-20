package chat.LoginClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.ChatRoomClasses.ChatRoomController;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.ChatRoomClasses.ChatRoomView;
import chat.NewUserClasses.NewUserController;
import chat.NewUserClasses.NewUserModel;
import chat.NewUserClasses.NewUserView;
import chat.abstractClasses.Controller;
import chat.appClasses.App_Model;
import chat.appClasses.App_View;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.CreateLogin;
import chat.message.Login;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class LoginController extends Controller<LoginModel, LoginView> {
	ServiceLocator serviceLocator;

	private LoginModel loginModel;

	public LoginController(LoginModel model, LoginView view) {
		super(model, view);

		// Action für LoginButton
		view.getLoginButton().setOnAction(e -> login());

		// Action für CreateUserButton
		view.getCreateUserButton().setOnAction(e -> createUserView());

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
	}

	private void login() {
		String username = view.getNameField().getText();
		String password = view.getPwField().getText();

		Login login = new Login(username, password);
		
		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.Token) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("eingelogt");
							Client.getClient().setToken(r.getToken());
							Platform.runLater(() -> {
								goToChatRoom();
							});
						} else {
							//TODO Fehlermeldung anzeigen
							//JavaFX_App_Template.getMainProgram().getNewUserView().setErrorLabel();
							serviceLocator.getLogger().info("Logindaten falsch");
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}
			
		});

		Client.getClient().send(login);
	}

	private void goToChatRoom() {
		this.view.stop();
		JavaFX_App_Template.getMainProgram().getChatRoom().start();
	}
	
	// Leitet zur CreatUserView
	private void createUserView() {
		JavaFX_App_Template.getMainProgram().startNewUser();
	}

}
