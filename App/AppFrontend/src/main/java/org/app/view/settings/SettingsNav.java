package org.app.view.settings;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.app.mail.smtp.MailSetting;
import org.app.model.audit.LoggedInUser;
import org.app.service.DeskService;
import org.app.service.ElytronMailSettingService;
import org.app.service.RoomService;
import org.app.service.VisorService;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import org.app.view.masterdetail.desk.DeskView;
import org.app.view.masterdetail.room.RoomView;
import org.app.view.masterdetail.visor.VisorView;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;;

@HtmlImport("frontend://styles/shared-styles.html")
@Route(value = "SettingsNav", layout = MainLayout.class)
@ParentLayout(MainLayout.class)
public class SettingsNav extends VerticalLayout implements RouterLayout {

	@Inject
	V18Cdi v18;

	@Inject
	VisorService visorService;

	@Inject
	RoomService roomService;

	@Inject
	DeskService deskService;
	
	@Inject
	ElytronMailSettingService elytronMailSettingService;
	
	@Inject
	LoggedInUser loggedInUser;

	private static final long serialVersionUID = 1L;
	private VerticalLayout navigationHolder;
	private Div contentHolder;

	public SettingsNav() {
		setSizeFull();
		setClassName("main-layout-left");
	}

	@PostConstruct
	void init() {
		contentHolder = new Div();
		contentHolder.setClassName("content");
		navigationHolder = new VerticalLayout();

		Button mailSettingsButton = new Button(v18.getTranslation("basic.mail"));
		mailSettingsButton.setClassName("lnkButton");
		mailSettingsButton.addClickListener(e -> {
			contentHolder.removeAll();
			contentHolder.add(new MailSetting(elytronMailSettingService, loggedInUser));
		});

		Button visorButton = new Button(v18.getTranslation("md.visor"));
		visorButton.setClassName("lnkButton");
		visorButton.addClickListener(e -> {
			contentHolder.removeAll();
			contentHolder.add(new VisorView(visorService));
		});

		Button roomButton = new Button(v18.getTranslation("md.room"));
		roomButton.setClassName("lnkButton");
		roomButton.addClickListener(e -> {
			contentHolder.removeAll();
			contentHolder.add(new RoomView(roomService));
		});

		Button deskButton = new Button(v18.getTranslation("md.desk"));
		deskButton.setClassName("lnkButton");
		deskButton.addClickListener(e -> {
			contentHolder.removeAll();
			contentHolder.add(new DeskView(deskService, roomService));
		});


		navigationHolder.add(mailSettingsButton, visorButton, roomButton, deskButton);

		SplitLayout layout = new SplitLayout();
		layout.setOrientation(Orientation.HORIZONTAL);
		layout.addToPrimary(navigationHolder);
		layout.addToSecondary(contentHolder);
		layout.setSplitterPosition(25);
		layout.setSizeFull();

		add(layout);

	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		contentHolder.getElement().appendChild(content.getElement());
	}
}
