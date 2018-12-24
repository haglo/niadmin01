package org.app.view.settings;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.app.mail.smtp.MailSetting;
import org.app.model.audit.LoggedInUser;
import org.app.service.ElytronMailSettingService;
import org.app.view.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "SettingsView", layout = MainLayout.class)
@PageTitle("SettingsView")
public class SettingsView extends VerticalLayout {
	
	@Inject
	ElytronMailSettingService elytronMailSettingService;
	
	@Inject
	LoggedInUser loggedInUser;


	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "SettingsView";

	public SettingsView() {
		Label label = new Label("Settings");
		add(label);
	}


	
	@PostConstruct
	void init() {
		MailSetting ms = new MailSetting(elytronMailSettingService, loggedInUser);
		add(ms);
		
	}
}