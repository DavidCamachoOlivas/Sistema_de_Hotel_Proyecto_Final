package controllers;

import views.AuthView;

public class AuthController {

	public AuthView view;
	
	public AuthController() {
		
		view = new AuthView();
	}
	
	
	
	public void login(){
		
		view.login();
		
	}
}
