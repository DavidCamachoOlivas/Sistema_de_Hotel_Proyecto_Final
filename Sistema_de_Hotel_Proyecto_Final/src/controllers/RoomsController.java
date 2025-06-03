package controllers;

import views.ClientsView;
import views.RoomsView;

public class RoomsController {

	public RoomsView view;
	
	public RoomsController() {
		
		view = new RoomsView();
	}
	
	
	
	public void rooms(){
		view.rooms();
	}
	
	public void createRoom() {
		view.createRoom();
	}
	
	public void editRoom() {
		view.editRoom();
	}
	
	public void consultRoom() {
		view.consultRoom();
	}
	
	public void deleteRoom() {
		view.deleteConfirm();
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
	
	public void roomDetails() {
		view.roomDetails();
	}
}
