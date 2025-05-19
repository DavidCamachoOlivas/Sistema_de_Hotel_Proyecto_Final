package controllers;

import views.RentalsView;

public class RentalsController {

	public RentalsView view;
	
	public RentalsController() {
		view =  new RentalsView();
	}
	
	public void rentals() {
		view.rentals();
	}
	
	public void createRental() {
		view.createRental();
	}
	
	public void editRental() {
		view.editRental();
	}
	
	public void consultRental() {
		view.consultRental();
	}
	
	public void deleteConfirm() {
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
	
}
