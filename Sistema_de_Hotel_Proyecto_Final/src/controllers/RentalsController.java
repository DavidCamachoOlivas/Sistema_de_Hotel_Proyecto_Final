package controllers;

import java.sql.SQLException;

import models.Rental;
import views.RentalsView;

public class RentalsController {

	public RentalsView view;
	
	public RentalsController() {
		view =  new RentalsView();
	}
	
	public void rentals() {
		try {
			view.rentals();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createRental() {
		try {
			view.createRental();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editRental(Rental r) {
		try {
			view.editRental(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteConfirm(Rental r) {
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
	
}
