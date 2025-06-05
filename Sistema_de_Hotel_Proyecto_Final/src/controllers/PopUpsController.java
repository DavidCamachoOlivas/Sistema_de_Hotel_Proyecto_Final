package controllers;

import views.PopUpsView;

public class PopUpsController {

	public PopUpsView pop = new PopUpsView();
	
	public void successDownload() {
		pop.successDownload();
	}
	
	public void successDelete() {
		pop.successDelete();
	}
	
	public void incorrectSignIn() {
		pop.incorrectSignIn();
	}
	
	public void loading() {
		pop.loading();
	}
	
	public void closeLoading() {
		pop.closeLoading();
	}
}
