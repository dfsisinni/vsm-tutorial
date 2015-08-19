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

public class ManagerView extends VerticalLayout implements View {

	private Label label = new Label("Hello Manager: " + SecurityUtils.getSubject().getPrincipal()); //getPrincipal returns the username
	private Button button = new Button ("Go To Settings");
	private Button logout = new Button("Log Out");
	
	public ManagerView () {
		this.addComponent(label);
		this.addComponent(new Label(""));
		this.addComponent(button);
		this.addComponent(new Label(""));
		this.addComponent(logout);
		
		logout.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				SecurityUtils.getSubject().logout(); //log out
				Page.getCurrent().reload(); //reload page otherwise default vaadin server communication error will appear
				
			}
			
		});
		
		button.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				
				//akin to amazon
				//you can access shopping cart (or manager view) as a remembered user
				//but you must be authenticated to access the secure view (or secure credit card area on amazon)
				
				if (SecurityUtils.getSubject().isAuthenticated()) { //if authenticated -- go to secure area
					getUI().getNavigator().addView("dashboard/secure", new SecureView());
					getUI().getNavigator().navigateTo("dashboard/secure");
				} else { //otherwise authenticate yourself
					getUI().getNavigator().addView("authenticate", new LoginView(true));
					getUI().getNavigator().navigateTo("authenticate");
				}
				
				//NOTE: isRemembered() is not 'secure enough' in this scenario
				
			}
			
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
