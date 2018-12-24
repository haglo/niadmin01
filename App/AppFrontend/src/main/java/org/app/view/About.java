package org.app.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.app.model.audit.LoggedInUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "About", layout = MainLayout.class)
@PageTitle("About")
public class About extends VerticalLayout {


	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "About";
	private final TextField searchField = new TextField("", "Search categories");
	
	@Inject
	LoggedInUser loggedInUser;


	public About() {
		initView();
		addSearchBar();
		Label label = new Label("About, das ist der Text zum anzeigen, danach kommt ein Logo");
		add(label);
		add(VaadinIcon.INFO_CIRCLE.create());
	}

	private void addSearchBar() {
		Div viewToolbar = new Div();
		viewToolbar.addClassName("view-toolbar");

		searchField.setPrefixComponent(new Icon("lumo", "search"));
		searchField.addClassName("view-toolbar__search-field");

		Button newButton = new Button("New category", new Icon("lumo", "plus"));
		newButton.getElement().setAttribute("theme", "primary");
		newButton.addClassName("view-toolbar__button");
		viewToolbar.add(searchField, newButton);
		add(viewToolbar);
	}

	private void initView() {
		setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
	}
	
	@PostConstruct
	void init() {
		Label logInUser = new Label("Logged-In-User: " + loggedInUser.getElytronUser().getUsername());
		Label logInUserGroup = new Label("Logged-In-User-Group: " + loggedInUser.getElytronUser().getElytronRole().getRolename());
		add(logInUser);
		add(logInUserGroup);
		System.out.println("%%% Logged-InUser %%%: " + loggedInUser.getElytronUser().getUsername());
		
	}
}
