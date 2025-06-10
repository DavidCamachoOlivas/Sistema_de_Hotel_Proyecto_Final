package controllers;

import java.sql.SQLException;

import models.Client;
import views.ClientsView;

public class ClientsController {

	public ClientsView view;
	
	public ClientsController() {
		
		view = new ClientsView();
	}
	
	public void clients(){
		try {
			view.clients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createClient() {
		view.createClient();
	}
	
	public void editClient(Client c) {
		view.editClient(c);
	}
	
	public void consultClient() {
		view.consultClient();
	}
	
	public void deleteClient(Client c) {
		view.deleteConfirm(c);
	}
	
	public void successDelete() {
		view.successDelete();
	}
	
	public void successDownload() {
		view.succesDownload();
	}
	
	public void errorDelete() {
		view.errorDelete();
	}
}
