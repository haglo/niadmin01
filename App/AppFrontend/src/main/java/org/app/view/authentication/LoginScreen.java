package org.app.view.authentication;

import javax.inject.Inject;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * UI content when the user is not logged in yet.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Route("Login")
@PageTitle("Login")
public class LoginScreen extends FlexLayout {
	
	@Inject 
	ValidateElytronUser validateElytronUser;

	private static final long serialVersionUID = 1L;
	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private Button forgotPassword;
	private AccessControl accessControlDatabase;
	private AccessControl accessControlLdap;

	public LoginScreen() {
		accessControlDatabase = AccessControlFactory.getInstance().createAccessControlDatabase();
		accessControlLdap = AccessControlFactory.getInstance().createAccessControlLdap();
		
		buildUI();
		username.focus();
	}

	private void buildUI() {
		setSizeFull();
		setClassName("login-screen");

		// login form, centered in the available part of the screen
		Component loginForm = buildLoginForm();

		// layout to center login form when there is sufficient screen space
		FlexLayout centeringLayout = new FlexLayout();
		centeringLayout.setSizeFull();
		centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
		centeringLayout.setAlignItems(Alignment.CENTER);
		centeringLayout.add(loginForm);

		// information text about logging in
		Component loginInformation = buildLoginInformation();

		add(loginInformation);
		add(centeringLayout);
	}

	private Component buildLoginForm() {
		FormLayout loginForm = new FormLayout();

		loginForm.setWidth("310px");

		loginForm.addFormItem(username = new TextField(), "Username");
		username.setWidth("15em");
		username.setValue("haglo");
		loginForm.add(new Html("<br/>"));
		loginForm.addFormItem(password = new PasswordField(), "Password");
		password.setWidth("15em");
		password.setValue("noway4u2");

		HorizontalLayout buttons = new HorizontalLayout();
		loginForm.add(new Html("<br/>"));
		loginForm.add(buttons);

		buttons.add(loginButton = new Button("Login"));
		loginButton.addClickListener(event -> login());
		loginForm.getElement().addEventListener("keypress", event -> login()).setFilter("event.key == 'Enter'");
		loginButton.getElement().getThemeList().add("success primary");

		buttons.add(forgotPassword = new Button("Forgot password?"));
		forgotPassword.addClickListener(event -> showNotification(new Notification("Hint: try anything")));
		forgotPassword.getElement().getThemeList().add("tertiary");

		return loginForm;
	}

	private Component buildLoginInformation() {
		VerticalLayout loginInformation = new VerticalLayout();
		loginInformation.setClassName("login-information");

		H1 loginInfoHeader = new H1("Login Information");
		Span loginInfoText = new Span(
				"Log in as \"admin\" to have full access. Log in with any other username to have read-only access. For all users, any password is fine.");
		loginInformation.add(loginInfoHeader);
		loginInformation.add(loginInfoText);

		return loginInformation;
	}

	private void login() {
		loginButton.setEnabled(false);
		try {
			if (accessControlDatabase.signIn(username.getValue(), password.getValue())
					&& validateElytronUser.validate(username.getValue())) {
				getUI().get().navigate("");
			} else if (accessControlLdap.signIn(username.getValue(), password.getValue())
					&& validateElytronUser.validate(username.getValue())) {
				getUI().get().navigate("");
			} else {
				showNotification(new Notification(
						"Login failed. <br>" + "Please check your username and password and try again."));
				username.focus();
			}
		} finally {
			loginButton.setEnabled(true);
		}
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDuration(2000);
		notification.open();
	}
}
