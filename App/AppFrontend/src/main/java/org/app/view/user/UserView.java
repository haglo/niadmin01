package org.app.view.user;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;;

@Route(value = "UserView", layout = MainLayout.class)
@PageTitle("UserView")
public class UserView extends VerticalLayout {

	@Inject 
	V18Cdi v18;
	
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "UserView";
	

	public UserView() {
		setAlignItems(Alignment.END);
	}

	@PostConstruct
	void init() {
		Button profileButton = new Button(v18.getTranslation("person.profile"));
		profileButton.addClickListener(e -> {
			profileButton.getUI().ifPresent(ui -> ui.navigate("ProfileView"));
		});
		add(profileButton);

		Button settingsButton = new Button(v18.getTranslation("settings.settings"));
		settingsButton.addClickListener(e -> {
			settingsButton.getUI().ifPresent(ui -> ui.navigate("SettingsNav"));
		});
		add(settingsButton);
		
		Button logoutButton = new Button(v18.getTranslation("auth.logout"), VaadinIcon.SIGN_OUT.create());
		logoutButton.addClickListener(event -> {
			VaadinSession.getCurrent().getSession().invalidate();
			UI.getCurrent().getPage().reload();
		});
		add(logoutButton);

	}

}
