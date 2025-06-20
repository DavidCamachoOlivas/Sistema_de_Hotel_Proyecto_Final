package controllers;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import models.Tariff;
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
	
	public void createTariff() {
		view.createTariff();
	}
	
	public void editTariff() {
		view.editTariff();
	}
	
	public void consultTariff() {
		view.consultTariff();
	}
	
	public void deleteConfirm(int row, Tariff t, DefaultTableModel model,JFrame frame) {
		view.deleteConfirm(row, t, model,frame);
	}
	
	public void successDelete() {
		view.successDelete();
	}
	
	public void successDownload() {
		view.succesDownload();
	}
	
}
