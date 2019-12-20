package chat;

import chat.ChatClasses.ChatController;
import chat.ChatClasses.ChatModel;
import chat.ChatClasses.ChatView;
import chat.ChatRoomClasses.ChatRoomController;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.ChatRoomClasses.ChatRoomView;
import chat.ChatRoomCreateClasses.ChatRoomCreatController;
import chat.ChatRoomCreateClasses.ChatRoomCreatModel;
import chat.ChatRoomCreateClasses.ChatRoomCreatView;
import chat.LoginClasses.LoginController;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.NewUserClasses.NewUserController;
import chat.NewUserClasses.NewUserModel;
import chat.NewUserClasses.NewUserView;
import chat.abstractClasses.View;
import chat.appClasses.App_Controller;
import chat.appClasses.App_Model;
import chat.appClasses.App_View;
import chat.splashScreen.Splash_Controller;
import chat.splashScreen.Splash_Model;
import chat.splashScreen.Splash_View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class JavaFX_App_Template extends Application {
	private static JavaFX_App_Template mainProgram; // singleton
	private Splash_View splashView;
	private LoginView loginView;
	private ChatView chatView;
	private ChatRoomView chatRoomView;
	private NewUserView newUserView;
	private ChatRoomCreatView chatRoomCreatView;

	private ServiceLocator serviceLocator; // resources, after initialization

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Note: This method is called on the main thread, not the JavaFX Application
	 * Thread. This means that we cannot display anything to the user at this point.
	 * Since we want to show a splash screen, this means that we cannot do any real
	 * initialization here.
	 * 
	 * This implementation ensures that the application is a singleton; only one per
	 * JVM-instance. On client installations this is not necessary (each application
	 * runs in its own JVM). However, it can be important on server installations.
	 * 
	 * Why is it important that only one instance run in the JVM? Because our
	 * initialized resources are a singleton - if two programs instances were
	 * running, they would use (and overwrite) each other's resources!
	 */
	@Override
	public void init() {
		if (mainProgram == null) {
			mainProgram = this;
		} else {
			Platform.exit();
		}
	}

	/**
	 * This method is called after init(), and is called on the JavaFX Application
	 * Thread, so we can display a GUI. We have two GUIs: a splash screen and the
	 * application. Both of these follow the MVC model.
	 * 
	 * We first display the splash screen. The model is where all initialization for
	 * the application takes place. The controller updates a progress-bar in the
	 * view, and (after initialization is finished) calls the startApp() method in
	 * this class.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Create and display the splash screen and model
		Splash_Model splashModel = new Splash_Model();
		splashView = new Splash_View(primaryStage, splashModel);
		new Splash_Controller(splashModel, splashView);
		splashView.start();

		// Display the splash screen and begin the initialization
		splashModel.initialize();
	}

	/**
	 * This method is called when the splash screen has finished initializing the
	 * application. The initialized resources are in a ServiceLocator singleton. Our
	 * task is to now create the application MVC components, to hide the splash
	 * screen, and to display the application GUI.
	 * 
	 * Multitasking note: This method is called from an event-handler in the
	 * Splash_Controller, which means that it is on the JavaFX Application Thread,
	 * which means that it is allowed to work with GUI components.
	 * http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
	 */
	public void startLogin() {

		// Initialize the application MVC components. Note that these components
		// can only be initialized now, because they may depend on the
		// resources initialized by the splash screen

		// LoginView
		View view = getLoginView();

		// Resources are now initialized
		serviceLocator = ServiceLocator.getServiceLocator();

		// Close the splash screen, and set the reference to null, so that all
		// Splash_XXX objects can be garbage collected
		splashView.stop();
		splashView = null;

		view.start();
	}

	public void startChat() {

	}

	public void startNewUser() {

		Stage stage = new Stage();
		NewUserModel model = new NewUserModel();

		if (newUserView == null) {
			newUserView = new NewUserView(stage, model);
		}

		new NewUserController(model, newUserView);

		loginView.stop();

		newUserView.start();

	}

	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the program, including GUI components. If the start
	 * method has never been called, the stop method may or may not be called.
	 * 
	 * Make the GUI invisible first. This prevents the user from taking any actions
	 * while the program is ending.
	 */
	@Override
	public void stop() {
		serviceLocator.getConfiguration().save();

		if (loginView != null) {
			// Make the view invisible
			loginView.stop();
		}

		// TODO durch Views loopen und wenn View nicht NULL ist, dann xxxView.stop();

		// More cleanup code as needed

		serviceLocator.getLogger().info("Application terminated");
	}

	// Static getter for a reference to the main program object
	public static JavaFX_App_Template getMainProgram() {
		return mainProgram;
	}

	public LoginView getLoginView() {
		if (loginView == null) {
			Stage appStage = new Stage();
			LoginModel loginModel = new LoginModel();
			loginView = new LoginView(appStage, loginModel);

			new LoginController(loginModel, loginView);
		}

		return loginView;
	}

	public NewUserView getNewUserView() {
		if (newUserView == null) {
			Stage appStage = new Stage();
			NewUserModel newUserModel = new NewUserModel();
			newUserView = new NewUserView(appStage, newUserModel);

			new NewUserController(newUserModel, newUserView);
		}

		return newUserView;
	}

	public ChatRoomView getChatRoom() {
		if (chatRoomView == null) {
			Stage stage = new Stage();
			ChatRoomModel model = new ChatRoomModel();
			chatRoomView = new ChatRoomView(stage, model);
			new ChatRoomController(model, chatRoomView);
		}

		return chatRoomView;
	}
	public ChatRoomCreatView getChatRoomCreate() {
		if (chatRoomCreatView == null) {
			Stage stage = new Stage();
			ChatRoomCreatModel model = new ChatRoomCreatModel();
			chatRoomCreatView = new ChatRoomCreatView(stage, model);
			new ChatRoomCreatController(model, chatRoomCreatView);
		}

		return chatRoomCreatView;
	}

}
