package com.vsmtutorial.ui;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eclipse.jetty.util.log.Log;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vsmtutorial.views.CustomerView;
import com.vsmtutorial.views.ErrorView;
import com.vsmtutorial.views.LoginView;
import com.vsmtutorial.views.ManagerView;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Theme("vsm_tutorial")
public class VSMTutorialUI extends UI {

	private static final transient Logger log = LoggerFactory.getLogger(VSMTutorialUI.class);
	private Navigator navigator;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VSMTutorialUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		log.info("vsmtutorial");
		Subject currentUser = SecurityUtils.getSubject(); //get current apache shiro user
		

		navigator = new Navigator (this, this);
		
		navigator.setErrorView(new ErrorView());
		
		
		//user is authenticated if they entered their password ie. logged in during the current browser session
		//user is remembered if they check the 'stay signed in' checkbox during a previous session
		
		//case 1: user logs in (enters password) and checks the checkbox
		//isAuthenticated = true, isRemembered = true
		
		//case 2: user logs in (enters password) and DOES NOT check the checkbox
		//isAuthenticated = true, isRemembered = false
		
		//case 3: user creates a new browser session and returns to the site but does not have to enter password
		//as they checked the checkbox during a previous session
		//isAuthenticated = false, isRemembered = true
		
		//case 4: user returns to site (did not check checkbox in previous session) and has not entered login info yet
		//isAuthenticated = false, isRemembered = false
		
		if (currentUser.isAuthenticated() || currentUser.isRemembered()) { //hence a check on both variables is required
			if (currentUser.hasRole("manager")) {
				navigator.addView("dashboard", new ManagerView());
			} else if (currentUser.hasRole("customer")) {
				navigator.addView("dashboard", new CustomerView());
			}
			navigator.navigateTo("dashboard");
		} else {
			navigator.addView("", new LoginView());
			navigator.navigateTo("");
		}
		
	}

}