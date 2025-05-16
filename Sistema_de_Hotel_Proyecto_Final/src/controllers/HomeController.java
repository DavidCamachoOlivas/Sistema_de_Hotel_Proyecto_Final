package controllers;

import views.AuthView;
import views.HomeView;

public class HomeController {

	public HomeView view;
	
	public HomeController() {
		
		view = new HomeView();
	}
	
	
	
	public void home(){
		
		view.home();
		
	}
}
