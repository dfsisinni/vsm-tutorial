package com.vsmtutorial.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ErrorView extends VerticalLayout implements View {

	private Label label = new Label("Error 404 PAGE NOT FOUND");
	
	public ErrorView () {
		this.addComponent(label);
		//Page.getCurrent().reload();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
