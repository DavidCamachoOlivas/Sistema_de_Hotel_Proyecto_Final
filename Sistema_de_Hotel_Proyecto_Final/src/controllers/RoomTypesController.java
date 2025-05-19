package controllers;

import views.ClientsView;
import views.RoomTypesView;

public class RoomTypesController {

	public RoomTypesView view;
	
	public RoomTypesController() {
		
		view = new RoomTypesView();
	}
	
	
	
	public void roomTypes(){
		view.roomTypes();
	}
	
	public void createRoomType() {
		view.createRoomType();
	}
	
	public void editRoomType() {
		view.editRoomType();
	}
	
	public void consultRoomType() {
		view.consultRoomType();
	}
	
	public void deleteRoomType() {
		view.deleteConfirm();
	}
	
	public void successDelete() {
		view.successDelete();
	}
}
