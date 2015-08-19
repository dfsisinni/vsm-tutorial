package com.vsmtutorial.views;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	private FormLayout form = new FormLayout();
	
	private TextField username = new TextField ("Username:");
	private PasswordField password = new PasswordField("Password:");
	private CheckBox checkbox = new CheckBox("Stay signed in");
	private Button button = new Button("Log in");
	private Label label = new Label("Log In:");
	
	private boolean goToAuthorizedManagerView = false;  //enables multi-purpose reuse of loginView
	
	public LoginView () {
		initialize();
	}
	
	public LoginView(boolean b) {
		initialize();
		goToAuthorizedManagerView = true;
		label.setValue("Please Provide Authentication:");
	}
	
	public void initialize () {
		this.addComponent(form);
		form.addComponent(label);
		label.addStyleName("login-title");
		
		form.addComponent(username);
		form.addComponent(password);
		form.addComponent(checkbox);
		form.addComponent(button);
		
		form.setWidthUndefined();
		this.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		this.setSizeFull();
		
		
		button.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				Subject currentUser = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(
						username.getValue(), password.getValue());
				
				token.setRememberMe(checkbox.getValue()); // if true user will not have to enter username/password in new browser session
				
				try {
					currentUser.login(token); //tries to authenticate user
					clear();
					
					if (goToAuthorizedManagerView) {
						if (currentUser.hasRole("manager")) {
							getUI().getNavigator().addView("dashboard/secure", new SecureView());
							getUI().getNavigator().navigateTo("dashboard/secure");
						} else {
							currentUser.logout();
							Page.getCurrent().reload();
						}
						
					} else {
					
						// if (currentUser.isAuthenticated())  - not necessary as try/catch statement will catch the unsuccessful login attempt

						if (currentUser.hasRole("manager")) { //authorization -- checking user permissions	
							getUI().getNavigator().addView("dashboard", new ManagerView());
						} else if (currentUser.hasRole("customer")) {
							getUI().getNavigator().addView("dashboard", new CustomerView());
						}
						getUI().getNavigator().navigateTo("dashboard");
					}

				
				} catch (Exception ex) { //if authentication is unsuccessful
					clear();
					Notification.show("Login Error:", "Invalid username/password combination.", Type.ERROR_MESSAGE);
				}
			}
			
		});
	}
	
	public void clear () {
		username.clear();
		password.clear();
		checkbox.clear();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
