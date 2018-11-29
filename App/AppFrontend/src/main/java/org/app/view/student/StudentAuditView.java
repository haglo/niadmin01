package org.app.view.student;

import java.util.List;
import javax.inject.Inject;
import org.app.controler.StudentService;
import org.app.model.entity.Student;
import org.app.model.entity.Student_AUD;
import org.app.view.V18Cdi;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class StudentAuditView extends Dialog {

	@Inject
	V18Cdi v18;

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("static-access")
	public StudentAuditView(Student selectedEntry, StudentService service) {

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		Grid<Student_AUD> grid = new Grid<Student_AUD>();
		List<Student_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		DataProvider<Student_AUD, ?> dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);


		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getFirstName();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getLastName();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getAccountName();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getSgiGroup();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getVisor();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getMailaddress();
			return result;
		}).setHeader(v18.getTranslation("title.value"));
		
		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getStartDate();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getEndDate();
			return result;
		}).setHeader(v18.getTranslation("title.value"));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevision().getRevisionDate();
			return result;
		}).setHeader(v18.getTranslation("basic.date"));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevision().getElytronUser().getUsername();
			return result;
		}).setHeader(v18.getTranslation("account.username"));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevType();
			return result;
		}).setHeader(v18.getTranslation("basic.type"));

		subContent.add(grid);
		this.setWidth("80%");

	}

}
