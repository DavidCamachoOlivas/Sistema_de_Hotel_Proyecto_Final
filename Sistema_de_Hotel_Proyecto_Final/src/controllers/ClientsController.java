package controllers;

import views.ClientsView;

public class ClientsController {

	public ClientsView view;
	
	public ClientsController() {
		
		view = new ClientsView();
	}
	
	
	
	public void clients(){
		view.clients();
	}
	
	public void createClient() {
		view.createClient();
	}
	
	public void editClient() {
		view.editClient();
	}
	
	public void consultClient() {
		view.consultClient();
	}
	
	public void deleteClient() {
		view.deleteConfirm();
	}
	
	public void successDelete() {
		view.successDelete();
	}
}
