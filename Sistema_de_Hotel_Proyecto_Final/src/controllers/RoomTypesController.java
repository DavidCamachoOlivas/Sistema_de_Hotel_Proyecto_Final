package controllers;

import java.sql.SQLException;

import models.RoomType;
import views.ClientsView;
import views.RoomTypesView;

public class RoomTypesController {

	public RoomTypesView view;
	
	public RoomTypesController() {
		
		view = new RoomTypesView();
	}
	
	public void roomTypes() throws SQLException{
		view.roomTypes();
	}
	
	public void createRoomType() throws SQLException {
		view.createRoomType();
	}
	
	public void editRoomType(RoomType rt) throws SQLException {
		view.editRoomType(rt);
	}
	
	public void consultRoomType(RoomType rt) {
		view.consultRoomType(rt);
	}
	
	public void deleteRoomType(RoomType rm) {
		view.deleteConfirm(rm);
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
