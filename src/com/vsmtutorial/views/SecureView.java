package com.vsmtutorial.views;

import org.apache.shiro.SecurityUtils;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SecureView extends VerticalLayout implements View {

	private Label label = new Label("SUPER SECURE INFO FOR MANAGER'S EYES ONLY");
	private Button button = new Button("Log Out");
	
	public SecureView () {
		this.addComponent(label);
		this.addComponent(button);
		
		button.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				SecurityUtils.getSubject().logout(); //log out
				Page.getCurrent().reload(); //reload page otherwise default vaadin server communication error will appear
				
			}
			
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
