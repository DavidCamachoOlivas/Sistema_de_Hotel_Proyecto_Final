package controllers;

import java.sql.SQLException;

import javax.swing.JFrame;

import models.RoomTypesModel;
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
	
	public void editRoomType(RoomTypesModel rt) throws SQLException {
		view.editRoomType(rt);
	}
	
	public void consultRoomType(RoomTypesModel rt) {
		view.consultRoomType(rt);
	}
	
	public void deleteRoomType(RoomTypesModel rm, JFrame frame) {
		view.deleteConfirm(rm, frame);
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
