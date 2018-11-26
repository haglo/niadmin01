/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.app.view;

import java.util.Locale;
import javax.inject.Inject;
import org.app.view.masterdetail.MasterDetail;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(value = Lumo.class)
@Route("")
public class MainLayout extends Div implements RouterLayout {


	private static final long serialVersionUID = 1L;
	private RouterLink studentLink;
	private RouterLink visorLink;
	private RouterLink masterDetailLink;


	public MainLayout() {
		H2 title = new H2("Institute of Neural Information Processing");
		title.addClassName("main-layout__title");

		// header of the menu
		final HorizontalLayout top = new HorizontalLayout();
		top.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		top.setClassName("menu-header");

		studentLink = new RouterLink(null, About.class);
		studentLink.add(new Icon(VaadinIcon.USERS), new Text("About"));
		studentLink.addClassName("main-layout__top-nav-item");
		studentLink.setHighlightCondition(HighlightConditions.sameLocation());

		visorLink = new RouterLink(null, About.class);
		visorLink.add(new Icon(VaadinIcon.ACADEMY_CAP), new Text("Betreuer 11"));
		visorLink.addClassName("main-layout__top-nav-item");
		visorLink.setHighlightCondition(HighlightConditions.sameLocation());

		masterDetailLink = new RouterLink(null, MasterDetail.class);
		masterDetailLink.add(new Icon(VaadinIcon.CLUSTER), new Text("Master Detail 11"));
		masterDetailLink.addClassName("main-layout__top-nav-item");

//		Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
//		logoutButton.addClickListener(event -> {
//			VaadinSession.getCurrent().getSession().invalidate();
//			UI.getCurrent().getPage().reload();
//		});
//
//		logoutButton.getElement().getThemeList().add("tertiary-inline");

		Div divText = new Div();
		divText.setText("Logout");
		Button logoutButton = new Button();
		Icon icon = new Icon(VaadinIcon.SIGN_OUT);
		logoutButton.getElement().appendChild(icon.getElement());
		logoutButton.getElement().appendChild(divText.getElement());
		logoutButton.addClickListener(event -> {
			VaadinSession.getCurrent().getSession().invalidate();
			UI.getCurrent().getPage().reload();
		});
//		logoutButton.addClassName("logout-button");

//		Div divText2 = new Div();
//		divText2.setText("Logout");
//		IconButton logoutButton = new IconButton();
//		logoutButton.setText(divText2);
//		logoutButton.setIcon(VaadinIcon.SIGN_OUT);

		Anchor a = new Anchor();
		a.setText("My link text");
		Icon ic = new Icon(VaadinIcon.SIGN_OUT);
		a.add(ic);
		a.getElement().addEventListener("click", e -> {
			VaadinSession.getCurrent().getSession().invalidate();
			UI.getCurrent().getPage().reload();
		});

		Div navigation = new Div(studentLink, visorLink, masterDetailLink, logoutButton, a);
		navigation.addClassName("main-layout__top-nav");

		Div header = new Div(title, navigation);
		header.addClassName("main-layout__header");

		add(header);
		addClassName("main-layout");

	}

}
