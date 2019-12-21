package chat.PWChangeClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.ChangePassword;
import chat.message.CreateLogin;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PWChangeController extends Controller<PWChangeModel, PWChangeView> {
	ServiceLocator serviceLocator;

	private PWChangeModel pWChangeModel;

	public PWChangeController(PWChangeModel model, PWChangeView view) {
		super(model, view);
		
		// Validierung der Passwort-Eingabe
		view.getPwField().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				view.getOkButton().setDisable(true);

				if (isPwValid()) {
					view.getPwField().getStyleClass().remove("pwField-red");
					view.getPwField().getStyleClass().add("pwField-green");
					view.getOkButton().setDisable(false);
				} else {
					view.getPwField().getStyleClass().remove("pwField-green");
					view.getPwField().getStyleClass().add("pwField-red");
				}

			}
		});
		
		// Action für okButton
		view.getOkButton().setOnAction(e -> setNewPassword());
		
		// Action für backButton
		view.getBackButton().setOnAction(e -> closePWChangView());
		
	}
	
	// Erstellt User beim Server und leitet zur LoginView
	private void setNewPassword() {
		String password = view.getPwField().getText();

		ChangePassword changePw = new ChangePassword(password);

		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.Simple) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("geändert");
							Platform.runLater(() -> {
								closePWChangView();
							});
						} else {
							JavaFX_App_Template.getMainProgram().getNewUserView().setErrorLabel();
							serviceLocator.getLogger().info("User gibt es schon");
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}

		});

		Client.getClient().send(changePw);
	}
	
	// Schliesst Fenster
	private void closePWChangView() {
		this.view.resetPWField();
		this.view.stop();


	}
	private boolean isPwValid() {
		int i = view.getPwField().getText().length();
		if (i < 3) {
			return false;
		} else {
			return true;
		}
	}
}

