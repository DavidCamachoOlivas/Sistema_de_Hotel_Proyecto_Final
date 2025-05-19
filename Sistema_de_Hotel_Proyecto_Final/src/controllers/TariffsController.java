package controllers;

import views.ClientsView;
import views.TariffsView;

public class TariffsController {

	public TariffsView view;
	
	public TariffsController() {
		
		view = new TariffsView();
	}
	
	
	
	public void tariffs(){
		view.tariff();
	}
	
	public void createClient() {
		view.createTariff();
	}
	
	public void editClient() {
		view.editTariff();
	}
	
	public void consultClient() {
		view.consultTariff();
	}
	
	public void deleteClient() {
		view.deleteConfirm();
	}
	
	public void successDelete() {
		view.successDelete();
	}
}
