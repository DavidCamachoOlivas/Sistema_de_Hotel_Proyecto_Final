package controllers;

import java.sql.SQLException;

import javax.swing.JFrame;

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
	public void createClient2() {
		view.createClient2();
	}
	
	public void editClient(Client c) {
		view.editClient(c);
	}
	
	public void consultClient(Client c) {
		try {
			view.consultClient(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteClient(Client c,JFrame frame) {
		view.deleteConfirm(c,frame);
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
