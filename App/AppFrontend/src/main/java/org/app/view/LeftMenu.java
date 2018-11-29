package org.app.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class LeftMenu extends FlexLayout {

	private static final long serialVersionUID = 1L;
	private Tabs tabs;

	public LeftMenu() {
		setClassName("main-layout__left-nav");

		tabs = new Tabs();
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		setFlexGrow(1, tabs);
		add(tabs);
	}

	/**
	 * Add view to the navigation menu
	 *
	 */
	public void addView(Class<? extends Component> viewClass, String caption, Icon icon) {
		Tab tab = new Tab();
		RouterLink routerLink = new RouterLink(null, viewClass);
		routerLink.setClassName("main-layout__left-nav-item");
		routerLink.add(icon);
		routerLink.add(new Span(" "));
		routerLink.add(new Span(caption));
		tab.add(routerLink);
		tabs.add(tab);
	}
	
}
