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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class LoginController extends Controller {
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
		// Validierung fehlt noch und Loginmit DB

		JavaFX_App_Template.getMainProgram().startChatRoom();
	}

	// Leitet zur CreatUserView
	private void createUserView() {
		JavaFX_App_Template.getMainProgram().startNewUser();
	}

}
