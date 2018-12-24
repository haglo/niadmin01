package org.app.view.person;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.app.model.entity.ElytronUser;
import org.app.model.entity.Person;
import org.app.service.ElytronUserService;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "PersonView", layout = MainLayout.class)
@PageTitle("PersonView")
public class PersonView extends VerticalLayout {

	@Inject
	V18Cdi v18;
	
	@Inject
	ElytronUserService elytronUserService;
	

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "PersonView";

	private TextField txfFirstname;
	private TextField txfLastname;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;

	private Person person;
	private ElytronUser currentElytronUser;

	public PersonView() {
		Label label = new Label("Settings");
		add(label);
	}

	@PostConstruct
	void init() {
		currentElytronUser = (ElytronUser) UI.getCurrent().getSession().getAttribute("currentElytronUser");
		person = currentElytronUser.getPerson();
		saveButton = new Button(v18.getTranslation("basic.save"));

		VerticalLayout subContent = new VerticalLayout();
		add(subContent);

		try {
			TextField txfID = new TextField("ID", person.getId() != null ? "" + person.getId() : "1");
			subContent.add(txfID);

			txfFirstname = new TextField(v18.getTranslation("person.firstname"),
					person.getFirstName() != null ? person.getFirstName() : "");
			subContent.add(txfFirstname);

			txfLastname = new TextField(v18.getTranslation("person.lastname"),
					person.getLastName() != null ? person.getLastName() : "");
			subContent.add(txfLastname);

			txaComment = new TextArea(v18.getTranslation("basic.comment"),
					person.getComment() != null ? person.getComment() : "");
			subContent.add(txaComment);

			subContent.add(saveButton);
//
			saveButton.addClickListener(event -> {
				person.setFirstName(txfFirstname.getValue());
				person.setLastName(txfLastname.getValue());
				person.setComment(txaComment.getValue());
				currentElytronUser.setPerson(person);
				currentElytronUser = elytronUserService.getDAO().update(currentElytronUser);
				
			});

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
