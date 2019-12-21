package chat.PWChangeClasses;

import chat.ServiceLocator;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;

public class PWChangeController extends Controller<PWChangeModel, PWChangeView> {
	ServiceLocator serviceLocator;

	private PWChangeModel pWChangeModel;

	public PWChangeController(PWChangeModel model, PWChangeView view) {
		super(model, view);
		
	}
}

