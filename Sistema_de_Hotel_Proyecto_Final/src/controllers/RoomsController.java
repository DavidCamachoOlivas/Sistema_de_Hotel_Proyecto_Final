package controllers;

import java.sql.SQLException;

import models.Room;
import models.RoomsModel;
import models.Tariff;
import models.TariffsModel;
import views.ClientsView;
import views.RoomsView;

public class RoomsController {

	public RoomsView view;
	
	public RoomsController() {
		
		view = new RoomsView();
	}
	public void rooms(){
		try {
			view.rooms();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createRoom() {
		view.createRoom();
	}
	
	public void editRoom(Room r) {
		view.editRoom(r);
	}
	
	public void consultRoom(Room r) throws SQLException {
		view.consultRoom(r);
	}
	
	public void deleteRoom(int r) {
		view.deleteConfirm(r);
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
    private RoomsModel   roomsModel   = new RoomsModel();
    private TariffsModel tariffsModel = new TariffsModel();

    public void createRoomWithTariff(Room room, Tariff tariff) throws SQLException {
        // 1) Inserta la habitación y recupera el nuevo id
        int newRoomId = roomsModel.createRoom(room);

        // 2) Asocia ese id_room al objeto Tariff
        tariff.setId_room(newRoomId);

        // 3) Inserta la tarifa ya con el id_room correcto
        int newTariffId = tariffsModel.createTariff(tariff);

        // Opcional: notificar al usuario
        System.out.println("Habitación ID=" + newRoomId + "  Tarifa ID=" + newTariffId);
    }


	
}
