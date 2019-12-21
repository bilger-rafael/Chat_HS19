package chat.ChatRoomClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.NewUserClasses.NewUserModel;
import chat.NewUserClasses.NewUserView;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatRoomController extends Controller<ChatRoomModel, ChatRoomView> {
	ServiceLocator serviceLocator;

	public ChatRoomController(ChatRoomModel model, ChatRoomView view) {
		super(model, view);

		// Action für CreateUserButton
		view.getLogoutMenuItem().setOnAction(e -> getBackLoginView());

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});

		// Action für CreateChatButton
		view.getNewChat().setOnAction(e -> createChatRoomCreatView());

		// Action für ChangePassword
		view.getChangePWMenuItem().setOnAction(e -> creatPWChangeView());

		// Action für Refresh
		view.getRefreshButton().setOnAction(e -> model.refreshChatrooms());

		view.chatRoomCenter.setItems(model.chatrooms);

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");

	}

	private void creatPWChangeView() {
		JavaFX_App_Template.getMainProgram().getPWChange().start();
	}

	private void getBackLoginView() {
		// TODO Logout mit dem Netzwerk und alle Fenster schliessen
		// Logik für zurück auf LoginView
		view.stop();
		JavaFX_App_Template.getMainProgram().getLoginView().start();

	}

	// Leitet zur CreatUserView
	private void createChatRoomCreatView() {
		JavaFX_App_Template.getMainProgram().getChatRoomCreate().start();
	}

}