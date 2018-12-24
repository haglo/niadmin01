package org.app.view.student;

import java.util.List;

import org.app.model.entity.Student;
import org.app.model.entity.Student_AUD;
import org.app.service.StudentService;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class StudentAuditView extends Dialog {
	private static final long serialVersionUID = 1L;
	private V18 v18;
	private StudentService service;
	private Button cancelButton;

	public StudentAuditView(StudentView parentView, Student selectedEntry) {
		v18 = new V18();
		service = parentView.getService();

		/**
		 * Text & Cancel
		 */
		Div captionText = new Div();
		captionText.setText("Auditing");

		cancelButton = new Button(" X ");
		cancelButton.addClickListener(event -> {
			close();
		});
		cancelButton.setClassName("alignright");
		
		HorizontalLayout topBar = new HorizontalLayout(captionText, cancelButton);
		topBar.setHeight("10%");
		topBar.setWidth("100%");
		
		
		Grid<Student_AUD> grid = new Grid<>();
		grid.setHeight("90%");
		grid.setWidth("100%");
		List<Student_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		DataProvider<Student_AUD, ?> dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);
		grid.setSelectionMode(SelectionMode.MULTI);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getFirstName();
		}).setHeader(v18.getTranslation("person.firstname")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getLastName();
		}).setHeader(v18.getTranslation("person.lastname")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getAccountName();
		}).setHeader(v18.getTranslation("account.username")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getMailaddress();
		}).setHeader(v18.getTranslation("basic.mail")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getStartDate();
		}).setHeader(v18.getTranslation("basic.begin")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getEndDate();
		}).setHeader(v18.getTranslation("basic.end")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevision().getRevisionDate();
		}).setHeader(v18.getTranslation("basic.date")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevision().getElytronUser().getUsername();
		}).setHeader(v18.getTranslation("account.loggedinuser")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevType();
		}).setHeader(v18.getTranslation("basic.type")).setResizable(true);

		setHeight("800px");
		setWidth("1800px");
		add(topBar);
		add(grid);
//		setSizeFull();
	}

}
